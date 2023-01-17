package adp.test.exchange.service.impl;


import adp.test.exchange.constants.ExchangeConstants;
import adp.test.exchange.exception.ChangeNotAvailableException;
import adp.test.exchange.model.CoinsHolder;
import adp.test.exchange.service.BillExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class BillExchangeServiceImpl implements BillExchangeService {

    @Autowired
    private Map<Double, Integer> coinFrequencyMap;

    @Override
    public CoinsHolder getChangeInCoins(Double bill, boolean getMaximumCoins) {
        Double[] coinTypes = ExchangeConstants.COIN_DENOMITIONS;
        Double[] coinTypesDesc = new Double[coinTypes.length];
        if (getMaximumCoins) {
            //create a new array with denominator in desc order
            System.arraycopy(coinTypes, 0, coinTypesDesc, 0, coinTypes.length);
            Arrays.sort(coinTypesDesc, Collections.reverseOrder());
        }
        Map<Double, Integer> noOfCoinTypeUsed = (!getMaximumCoins) ? getMapOfCoinTypeUsed(coinTypes, bill) :
                getMapOfCoinTypeUsed(coinTypesDesc, bill);
        return CoinsHolder.builder().CoinTypesUsed(noOfCoinTypeUsed).build();

    }

    private Map<Double, Integer> getMapOfCoinTypeUsed(Double[] coinTypes, Double billAmount) {
        Map<Double, Integer> coinFrequencyMapCopy = new HashMap<>(coinFrequencyMap);
        Map<Double, Integer> noOfCoinTypeUsed = new HashMap<>();


        for (int i = coinTypes.length - 1; i >= 0; i--) {
            while (billAmount >= coinTypes[i] && coinFrequencyMap.get(coinTypes[i]) > 0) {
                billAmount -= coinTypes[i];
                //added this line to restrict preciseness issuen while subtracting
                BigDecimal billInBigDecimel = new BigDecimal(billAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
                billAmount = billInBigDecimel.doubleValue();
                if (noOfCoinTypeUsed.containsKey(coinTypes[i])) {
                    noOfCoinTypeUsed.put(coinTypes[i], noOfCoinTypeUsed.get(coinTypes[i]) + 1);
                } else {
                    noOfCoinTypeUsed.put(coinTypes[i], 1);
                }
                coinFrequencyMap.put(coinTypes[i], coinFrequencyMap.get(coinTypes[i]) - 1);
            }
        }

        if (billAmount > 0) {
            coinFrequencyMap = coinFrequencyMapCopy;
            throw new ChangeNotAvailableException("Not Enough Coins to Provide Change !");
        }
        return noOfCoinTypeUsed;
    }
}
