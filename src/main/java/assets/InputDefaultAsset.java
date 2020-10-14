package assets;

public class InputDefaultAsset implements InputAsset {
    public String ticker;

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