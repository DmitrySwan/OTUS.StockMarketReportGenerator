package otus.model.assets;

public class InputStock extends InputAsset {
    String sectorId;

    public InputStock(String ticker, String sectorId) {
        super(ticker);
        this.sectorId = sectorId;
    }

    public String getSectorId() {
        return sectorId;
    }

    public void setSectorId(String sectorId) {
        this.sectorId = sectorId;
    }
}
