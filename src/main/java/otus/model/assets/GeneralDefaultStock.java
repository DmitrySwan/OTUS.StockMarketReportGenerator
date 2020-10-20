package otus.model.assets;

import yahoofinance.Stock;

public class GeneralDefaultStock implements GeneralStock {
    String ticker;
    Asset asset;
    Stock stock;

    public GeneralDefaultStock(String ticker, Asset asset, Stock stock) {
        this.ticker = ticker;
        this.asset = asset;
        this.stock = stock;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
