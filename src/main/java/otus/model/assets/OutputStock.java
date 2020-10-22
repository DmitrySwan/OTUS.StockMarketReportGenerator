package otus.model.assets;

import java.math.BigDecimal;

public class OutputStock extends InputStock {
    String name;
    BigDecimal price;
    BigDecimal change;
    BigDecimal changeInPercent;

    public OutputStock(String ticker, String sectorId, String name, BigDecimal price) {
        super(ticker, sectorId);
        this.name = name;
        this.price = price;
    }

    public OutputStock(String ticker, String sectorId) {
        super(ticker, sectorId);
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public BigDecimal getChangeInPercent() {
        return changeInPercent;
    }

    public void setChangeInPercent(BigDecimal changeInPercent) {
        this.changeInPercent = changeInPercent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
