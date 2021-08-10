package com.lickvalue.change;

import com.lickvalue.change.model.Coin;
import com.lickvalue.change.model.Data;
import com.lickvalue.change.model.Pair;
import com.lickvalue.change.model.VarPair;
import com.google.gson.Gson;
import lombok.SneakyThrows;
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

		// setLong=2 setShort=3 percentage=250  incOffset=2  decOffset=3

		String percentage = args[2].split("=")[1];
		String incOffset = args[3].split("=")[1];
		String decOffset = args[4].split("=")[1];

		System.out.println("========================================");
		System.out.println(" Updating LickValue Pairs more percentage plus : " + percentage + "%");
		System.out.println("Increment or Decrement value: inc ->" + incOffset + " dec -> " + decOffset);
		getVarPairJson(percentage, incOffset, decOffset);

		System.out.println("======FINISH PAIRS VALUE UPDATE========");
	}

	private static List<Pair> getDataAPI() {
		RestTemplate restTemplate = new RestTemplate();
		Data data = restTemplate.getForObject( URL_STATIC, Data.class);
		return data.getData();
	}


	private static VarPair getVarPairJson (String percentage, String incOffset, String decOffset) {
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

			List<Coin> changeCoins = changeCoins(varPair.getCoins(), convertPairsToMap, percentage, incOffset, decOffset);

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
										  String percentage, String incOffset, String decOffset) {

		return coins.stream().map( coin -> {
			Pair pair = convertPairsToMap.get(coin.getSymbol());
			if (pair != null) {
				Double addPercentage = pair.getAverage_usdt() * Integer.valueOf(percentage) / 100;
				String lickValue = String.valueOf((int)Math.round(pair.getAverage_usdt() + addPercentage ));
				System.out.println("Coin :: " + coin.getSymbol()
						+ " -> actual lickvalue (" + pair.getAverage_usdt()
						+ ")" + " -> new LickValue with increment percentage of " + percentage + "% -> (" + lickValue + ")");
				return new Coin(
						coin.getSymbol(),
						setOffset(coin.getLongoffset(), incOffset, decOffset),
						setOffset(coin.getShortoffset(), incOffset, decOffset ),
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

	private static String setOffset(final String valueOld, final String incOffset, final String decOffset) {

		if (incOffset.equals("0") && decOffset.equals("0")) {
			return valueOld;
		} else {
			if (incOffset.equals("0")) {
				return String.valueOf(Long.valueOf(valueOld) - Long.valueOf(decOffset));
			}
				return String.valueOf(Long.valueOf(valueOld) + Long.valueOf(incOffset));
		}
	}
}
