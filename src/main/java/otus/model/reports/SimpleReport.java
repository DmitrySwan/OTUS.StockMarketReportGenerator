package otus.model.reports;

import otus.model.assets.Asset;
import otus.model.assets.GeneralStock;
import otus.model.assets.InputAsset;
import otus.model.assets.OutputAsset;
import yahoofinance.Stock;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SimpleReport extends AbstractReportFormat implements ReportFormat {

    @Override
    public List<Asset> createReportData(List<Asset> assets) throws IOException {
        Map<String, Stock> stocks = getStocks(assets);
        List<Asset> outputAssets = new LinkedList<>();
        assets.stream().forEach(asset -> {
            String ticker = asset.getTicker();
            Stock yahooStock = stocks.get(ticker);
            StockQuote stockQuote = yahooStock.getQuote();
            outputAssets.add(new OutputAsset(
                    ticker,
                    yahooStock.getName(),
                    stockQuote.getPrice(),
                    stockQuote.getChangeFromAvg50InPercent(),
                    yahooStock.getDividend().getAnnualYield()
            ));
        });
        return outputAssets;
    }
}
