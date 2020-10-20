package otus.model.assets;

import java.util.Date;

public class InputStock extends InputAsset {
    String groupId;
    Date dateFrom;
    Date dateTo;

    public InputStock(String ticker) {
        super(ticker);
    }

    public InputStock(String ticker, String groupId, Date dateFrom, Date dateTo) {
        super(ticker);
        this.groupId = groupId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "ticker='" + ticker + '\'' +
                ", groupId='" + groupId + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}
