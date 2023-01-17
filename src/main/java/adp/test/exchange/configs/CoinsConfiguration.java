package adp.test.exchange.configs;

import adp.test.exchange.constants.ExchangeConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class CoinsConfiguration {

    @Value("${coins-initial-value:100}")
    Integer initialCoinValue;

    @Bean
    public Map<Double, Integer> createCoinTypeFrequencyMap() {
        //create a coin map with default map
        Double[] allowedCoins = ExchangeConstants.COIN_DENOMITIONS;
        Map<Double, Integer> coinFrequencyMap = new HashMap();
        Arrays.asList(allowedCoins).forEach(coinType -> {
            coinFrequencyMap.put(coinType, initialCoinValue);
        });
        return coinFrequencyMap;
    }
}
