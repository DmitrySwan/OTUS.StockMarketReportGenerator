package otus.model.assets;

public class InputStock extends InputAsset {
    String groupId;

    public InputStock(String ticker, String groupId) {
        super(ticker);
        this.groupId = groupId;
    }
}
