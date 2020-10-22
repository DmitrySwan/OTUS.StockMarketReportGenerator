package otus.model.reports;

import otus.model.assets.Asset;
import yahoofinance.Stock;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CustomReport extends AbstractReportFormat implements ReportFormat {

    private ChangeAverage average;

    public CustomReport(ChangeAverage average) {
        super();
        this.average = average;
    }

    @Override
    public List<Asset> createReportData(List<Asset> assets) throws IOException {
        Map<String, Stock> stocks = getStocks(assets);
        List<Asset> outputAssets = new LinkedList<>();
        assets.stream().forEach(asset -> {
            Stock yahooStock = stocks.get(asset.getTicker());
            //StockQuote stockQuote = yahooStock.getQuote().getChangeFromAvg50();
            //InputStock inputStock = (InputStock) asset;

        });
        return null;
    }

    public enum ChangeAverage {
        LAST_DAY ("last day"),
        LAST_50_DAYS ("last 50 days"),
        LAST_200_DAYS ("last 200 days");

        private final String description;

        ChangeAverage(String description) {
            this.description = description;
        }

        public String getDescription() {
            return this.description;
        }
    }
}
