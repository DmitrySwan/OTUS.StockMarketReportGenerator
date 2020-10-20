package otus.form;

import java.math.BigDecimal;

public class InputShareForm extends InputAssetForm {
    public String ticker;
    BigDecimal price;
    int count;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}