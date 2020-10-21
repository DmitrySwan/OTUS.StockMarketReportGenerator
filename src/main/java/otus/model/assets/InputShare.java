package otus.model.assets;

import java.math.BigDecimal;

public class InputShare extends InputAsset {
    public BigDecimal purchasePrice;
    public int count;

    public InputShare(String ticker) {
        super(ticker);
    }

    public InputShare(InputShare share) {
        super(share.getTicker());
        this.purchasePrice = share.getPurchasePrice();
        this.count = share.getCount();
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
