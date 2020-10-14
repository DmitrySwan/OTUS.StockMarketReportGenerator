package assets;

import yahoofinance.Stock;

public interface GeneralStock {
    public String getTicker();

    public InputAsset getAsset();

    public Stock getStock();
}
