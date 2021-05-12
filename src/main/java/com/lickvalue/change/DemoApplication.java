package com.lickvalue.change;

import com.lickvalue.change.model.Coin;
import com.lickvalue.change.model.Data;
import com.lickvalue.change.model.Pair;
import com.lickvalue.change.model.VarPair;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class DemoApplication {

	private static final String URL_STATIC = "https://liquidation.wtf/api/v0/liquidations/by_coin";


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		String setLong = args[0];
		String setShort = args[1];
		String percentage = args[2]; // Add params to increment value percentage
		String exceptCoins = args[3];
		String[] splitCoins = exceptCoins.split(",");
		List<String> except = Arrays.stream(splitCoins).collect(Collectors.toList());

		System.out.println("========================================");
		System.out.println(" Updating LickValue Pairs more percentage plus : " + percentage);
		System.out.println(" Set Short & Long values with this values, long -> "
				+ setLong + " and short -> " + setShort);
		System.out.println(" set vwap Except this coins: " );
		except.forEach(System.out::println);

		getVarPairJson(percentage, setLong, setShort, except);

		System.out.println("======FINISH PAIRS VALUE UPDATE========");
	}

	private static List<Pair> getDataAPI() {
		RestTemplate restTemplate = new RestTemplate();
		Data data = restTemplate.getForObject( URL_STATIC, Data.class);
		return data.getData();
	}


	private static VarPair getVarPairJson (String percentage, String setLong,
										   String setShort, List<String> except) {
		Gson g = new Gson();
		VarPair varPair = null;
		try {
			File file = new File("varPairs.json");

			List<Pair> pairs = getDataAPI();

			Map<String, Pair> convertPairsToMap = pairs
					.stream()
					.collect(Collectors
							.toMap(Pair::getSymbol, pair -> pair));

			FileReader reader = new FileReader(file);

			varPair = g.fromJson(reader , VarPair.class);

			List<Coin> changeCoins = changeCoins(varPair.getCoins(), convertPairsToMap,
					percentage, setLong, setShort, except);

			varPair.setCoins(changeCoins);

			ObjectMapper mapper = new ObjectMapper();

			byte[] write =  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(varPair).getBytes();

			writeVarPairsJson(write, file);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return varPair;
	}


	private static void writeVarPairsJson(byte[] write, File file) throws IOException {
		FileOutputStream outputStream = new FileOutputStream(file);
		outputStream.write(write);
		outputStream.close();
	}


	private static List<Coin> changeCoins(List<Coin> coins, Map<String, Pair> convertPairsToMap,
										  String percentage, String setLong, String setShort,
										  List<String> except) {
		return coins.stream().map( coin -> {
			Pair pair = convertPairsToMap.get(coin.getSymbol());
			if (pair != null) {
				Double addPercentage = pair.getAverage_usdt() * Integer.valueOf(percentage) / 100;
				String lickValue = String.valueOf((int)Math.round(pair.getAverage_usdt() + addPercentage ));
				return new Coin(
						coin.getSymbol(),
						setLongMethod(coin.getSymbol(), except, coin.getLongoffset(), setLong),
						setShortMethod(coin.getSymbol(), except, coin.getShortoffset(), setShort),
						lickValue,
						coin.getVar_enabled(),
						coin.getVar_staticList(),
						coin.getVar_whiteList(),
						coin.getVar_blackList(),
						coin.getTmp_kline_age(),
						coin.getTmp_color());
			}else{
				return null;
			}
		}).collect(Collectors.toList());
	}

	private static String setLongMethod(String symbol, List<String> except, String longOld,
						   String longNew) {
		if (except.contains(symbol)) {
			return longOld;
		}
		return longNew;
	}

	private static String setShortMethod(String symbol, List<String> except, String shortOld,
										String shortNew) {
		if (except.contains(symbol)) {
			return shortOld;
		}
		return shortNew;
	}
}
