package adp.test.exchange.controller;


import adp.test.exchange.model.CoinsHolder;
import adp.test.exchange.service.BillExchangeService;
import adp.test.exchange.utils.RequestValidationUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.doReturn;


@RunWith(MockitoJUnitRunner.class)
public class BillExchangeControllerTests {


    @InjectMocks
    BillExchangeController exchangeController;

    @Mock
    BillExchangeService exchangeService;

    @Mock
    RequestValidationUtil requestValidationUtil;

    @Test
    public void test_getChangeInCoins() {
        CoinsHolder dummyObject = getCoinHolderDummy();
        doReturn(dummyObject).when(exchangeService).getChangeInCoins(Mockito.any(), Mockito.anyBoolean());
        CoinsHolder response = exchangeController.getChangeInCoins(Mockito.anyInt(), false);

        Assert.assertNotNull(response);
        Assert.assertEquals(dummyObject, response);
    }

    private CoinsHolder getCoinHolderDummy() {
        Map<Double, Integer> map = new HashMap<>();
        map.put(.25, 4);
        return CoinsHolder.builder().CoinTypesUsed(map).build();
    }


}
