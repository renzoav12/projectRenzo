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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class DemoApplication {

	private static final String URL_STATIC = "https://liquidation.wtf/api/v0/liquidations/by_coin";


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("========================================");
		System.out.println("Init Updating LickValue Pairs ");

		getVarPairJson();

		System.out.println("======FINISH PAIRS VALUE UPDATE========");
	}

	private static List<Pair> getDataAPI() {
		RestTemplate restTemplate = new RestTemplate();
		Data data = restTemplate.getForObject( URL_STATIC, Data.class);
		return data.getData();
	}


	private static VarPair getVarPairJson () {
		Gson g = new Gson();
		VarPair varPair = null;
		try {
			File file = new File("varPairs2.json");

			List<Pair> pairs = getDataAPI();

			Map<String, Pair> convertPairsToMap = pairs
					.stream()
					.collect(Collectors
							.toMap(Pair::getSymbol, pair -> pair));

			FileReader reader = new FileReader(file);

			varPair = g.fromJson(reader , VarPair.class);

			List<Coin> changeCoins = changeCoins(varPair.getCoins(), convertPairsToMap);

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


	private static List<Coin> changeCoins(List<Coin> coins, Map<String, Pair> convertPairsToMap) {
		return coins.stream().map( coin -> {
			Pair pair = convertPairsToMap.get(coin.getSymbol());
			if (pair != null) {
				String lickValue = String.valueOf((int)Math.round(pair.getAverage_usdt()));
				return new Coin(
						coin.getSymbol(),
						coin.getLongoffset(),
						coin.getShortoffset(),
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
}
