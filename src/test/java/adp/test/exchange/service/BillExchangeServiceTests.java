package adp.test.exchange.service;

import adp.test.exchange.exception.ChangeNotAvailableException;
import adp.test.exchange.model.CoinsHolder;
import adp.test.exchange.service.impl.BillExchangeServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class BillExchangeServiceTests {

    @InjectMocks
    BillExchangeServiceImpl billExchangeService;

    @Before
    public void setupTests() {
        List<Double> allowedCoins = Arrays.asList(.01, 0.05, 0.10, 0.25);
        Map<Double, Integer> frequencyMap = new HashMap();
        allowedCoins.forEach(coinType -> {
            frequencyMap.put(coinType, 10);
        });
        ReflectionTestUtils.setField(billExchangeService, "coinFrequencyMap", frequencyMap);
    }

    @Test
    public void test_getChangeInCoins() {
        CoinsHolder response = billExchangeService.getChangeInCoins(2.0, false);
        Assert.assertNotNull(response);
        Assert.assertEquals(Long.valueOf(8), Long.valueOf(response.getCoinTypesUsed().get(.25)));
    }

    @Test(expected = ChangeNotAvailableException.class)
    public void test_getChangeInCoins_exception() {
        CoinsHolder response = billExchangeService.getChangeInCoins(100.0, false);
    }

}
