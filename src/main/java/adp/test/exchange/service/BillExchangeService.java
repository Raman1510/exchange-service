package adp.test.exchange.service;


import adp.test.exchange.model.CoinsHolder;

public interface BillExchangeService {

    CoinsHolder getChangeInCoins(Double bill, boolean getMaximumCoins);

}
