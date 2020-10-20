package otus.model.assets;

import yahoofinance.Stock;

public interface GeneralStock {
    public String getTicker();

    public Asset getAsset();

    public Stock getStock();
}
