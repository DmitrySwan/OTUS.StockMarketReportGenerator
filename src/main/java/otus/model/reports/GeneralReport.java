package otus.model.reports;

import otus.model.assets.*;
import yahoofinance.Stock;
import yahoofinance.quotes.stock.StockDividend;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GeneralReport extends AbstractReportFormat implements ReportFormat {
    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    @Override
    public List<Asset> createReportData(List<Asset> assets) throws IOException {
        Map<String, Stock> stocks = getStocks(assets);
        List<Asset> outputAssets = new LinkedList<>();
        assets.stream().forEach(asset -> {
            String ticker = asset.getTicker();
            Stock yahooStock = stocks.get(ticker);
            StockQuote stockQuote = yahooStock.getQuote();
            InputShare inputShare = (InputShare) asset;
            BigDecimal count = BigDecimal.valueOf(inputShare.getCount());
            StockDividend stockDividend = yahooStock.getDividend();
            BigDecimal dividendPerShare = stockDividend.getAnnualYield();
            BigDecimal purchasePrice = inputShare.getPurchasePrice();
            BigDecimal price = stockQuote.getPrice();
            OutputShare outputShare = new OutputShare(
                    ticker,
                    purchasePrice,
                    inputShare.getCount(),
                    yahooStock.getName(),
                    price,
                    stockQuote.getChange(),
                    stockQuote.getChangeInPercent(),
                    dividendPerShare
            );
            outputShare.setDividends(dividendPerShare == null ? BigDecimal.ZERO : dividendPerShare.multiply(count));
            BigDecimal pricePerShareChange = price.subtract(purchasePrice);
            BigDecimal priceChange = pricePerShareChange.multiply(count);
            outputShare.setPriceChange(priceChange);
            outputShare.setPriceChangeInPercent(
                    pricePerShareChange.multiply(ONE_HUNDRED).divide(purchasePrice, 2, RoundingMode.HALF_UP));

            outputAssets.add(outputShare);
        });
        return outputAssets;
    }
}
