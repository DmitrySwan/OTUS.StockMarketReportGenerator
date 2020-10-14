package assets;

import yahoofinance.Stock;

public class GeneralDefaultStock implements GeneralStock {
    String ticker;
    InputAsset asset;
    Stock stock;

    public GeneralDefaultStock(String ticker, InputAsset asset, Stock stock) {
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

    public InputAsset getAsset() {
        return asset;
    }

    public void setAsset(InputAsset asset) {
        this.asset = asset;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
