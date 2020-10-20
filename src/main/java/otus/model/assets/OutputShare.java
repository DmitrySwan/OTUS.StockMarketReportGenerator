package otus.model.assets;

import java.math.BigDecimal;
import java.util.Date;

public class OutputShare extends InputShare {
    String name;
    BigDecimal currentPrice;
    BigDecimal changeToday;
    BigDecimal changeTodayInPercent;
    BigDecimal dividendPerShare;
    BigDecimal priceChange;
    BigDecimal priceChangeInPercent;
    BigDecimal dividends;

    public BigDecimal getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(BigDecimal priceChange) {
        this.priceChange = priceChange;
    }

    public BigDecimal getPriceChangeInPercent() {
        return priceChangeInPercent;
    }

    public void setPriceChangeInPercent(BigDecimal priceChangeInPercent) {
        this.priceChangeInPercent = priceChangeInPercent;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public BigDecimal getChangeToday() {
        return changeToday;
    }

    public void setChangeToday(BigDecimal changeToday) {
        this.changeToday = changeToday;
    }

    public BigDecimal getChangeTodayInPercent() {
        return changeTodayInPercent;
    }

    public void setChangeTodayInPercent(BigDecimal changeTodayInPercent) {
        this.changeTodayInPercent = changeTodayInPercent;
    }

    public BigDecimal getDividendPerShare() {
        return dividendPerShare;
    }

    public void setDividendPerShare(BigDecimal dividendPerShare) {
        this.dividendPerShare = dividendPerShare;
    }

    public BigDecimal getDividends() {
        return dividends;
    }

    public void setDividends(BigDecimal dividends) {
        this.dividends = dividends;
    }

    public OutputShare(String ticker, BigDecimal purchasePrice, int count,
                       String name, BigDecimal currentPrice, BigDecimal changeToday,
                       BigDecimal changeTodayInPercent, BigDecimal dividendPerShare) {
        super(ticker, purchasePrice, count);
        this.name = name;
        this.currentPrice = currentPrice;
        this.changeToday = changeToday;
        this.changeTodayInPercent = changeTodayInPercent;
        this.dividendPerShare = dividendPerShare;
    }

    public OutputShare(String ticker) {
        super(ticker);
    }
}
