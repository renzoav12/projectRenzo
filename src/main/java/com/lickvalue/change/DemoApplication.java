package com.lickvalue.change;

import com.lickvalue.change.model.*;
import com.google.gson.Gson;
import com.lickvalue.change.service.VWapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.example", "com.controller", "com.repository", "com.service", "com.model"})
@EntityScan(basePackages = {"com.model"})
@EnableJpaRepositories(basePackages = {"com.repository"})
@EnableTransactionManagement
public class DemoApplication {

	private static final String URL_STATIC = "https://liquidation.wtf/api/v0/liquidations/by_coin";

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
		VWapService vWapService = applicationContext.getBean(VWapService.class);

		String userValid = "renzo";
		if ( userValid.equals("renzo")) {
			String percentage = args[0]; // Add params to increment value percentage
			System.out.println("==============================================================");
			System.out.println(" !!Welcome User :" +  userValid);
			System.out.println(" Updating LickValue Pairs more percentage plus : " + percentage);
			getVarPairJson(percentage, vWapService);
			System.out.println("======FINISH PAIRS VALUE UPDATE========");
			System.out.println("========================!! Thanks !! ===========================");
		}else {
			System.out.println("======================USER NO EXIST============================");
		}
	}

	private static List<Pair> getDataAPI() {
		RestTemplate restTemplate = new RestTemplate();
		Data data = restTemplate.getForObject( URL_STATIC, Data.class);
		return data.getData();
	}


	private static VarPair getVarPairJson (String percentage, VWapService vWapService) {
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

			createCoins(varPair.getCoins(), vWapService);

			List<Coin> changeCoins = changeCoins(varPair.getCoins(), convertPairsToMap,
					percentage);

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

	private static void createCoins(List<Coin> coins, VWapService vWapService) {

		coins.stream().forEach(coin -> {
			VWapRequest request = new VWapRequest();
			request.setSymbol(coin.getSymbol());
			request.setEmail("");
			request.setLongSet(coin.getLongoffset());
			request.setShortSet(coin.getShortoffset());
			vWapService.createVWap(request);
		});
	}


	private static void writeVarPairsJson(byte[] write, File file) throws IOException {
		FileOutputStream outputStream = new FileOutputStream(file);
		outputStream.write(write);
		outputStream.close();
	}


	private static List<Coin> changeCoins(List<Coin> coins, Map<String, Pair> convertPairsToMap,
										  String percentage) {
		return coins.stream().map( coin -> {
			Pair pair = convertPairsToMap.get(coin.getSymbol());

			if (pair != null) {
				Double addPercentage = pair.getAverage_usdt() * Integer.valueOf(percentage) / 100;
				String lickValue = String.valueOf((int)Math.round(pair.getAverage_usdt() + addPercentage ));
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
