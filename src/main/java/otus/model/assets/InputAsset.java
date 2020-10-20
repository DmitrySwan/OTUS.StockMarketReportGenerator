package otus.model.assets;

public class InputAsset implements Asset {
    public String ticker;

    public InputAsset() {
    }

    public InputAsset(String ticker) {
        this.ticker = ticker;
    }

    public String getTicker() {
        return this.ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "ticker='" + ticker + '\'' + '}';
    }
}