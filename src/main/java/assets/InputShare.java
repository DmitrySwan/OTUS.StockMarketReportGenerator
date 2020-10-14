package assets;

import java.math.BigDecimal;

public class InputShare extends InputDefaultAsset {
    BigDecimal purchasePrice;
    int count;

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
