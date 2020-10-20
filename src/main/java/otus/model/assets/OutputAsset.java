package otus.model.assets;

import java.math.BigDecimal;

public class OutputAsset extends InputAsset {
    String name;
    BigDecimal price;
    BigDecimal changeFromAvg50InPercent;
    BigDecimal dividendAnnualYield;

    public OutputAsset(String ticker, String name, BigDecimal price, BigDecimal changeFromAvg50InPercent, BigDecimal dividendAnnualYield) {
        super(ticker);
        this.name = name;
        this.price = price;
        this.changeFromAvg50InPercent = changeFromAvg50InPercent;
        this.dividendAnnualYield = dividendAnnualYield;
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

    public BigDecimal getChangeFromAvg50InPercent() {
        return changeFromAvg50InPercent;
    }

    public void setChangeFromAvg50InPercent(BigDecimal changeFromAvg50InPercent) {
        this.changeFromAvg50InPercent = changeFromAvg50InPercent;
    }

    public BigDecimal getDividendAnnualYield() {
        return dividendAnnualYield;
    }

    public void setDividendAnnualYield(BigDecimal dividendAnnualYield) {
        this.dividendAnnualYield = dividendAnnualYield;
    }
}
