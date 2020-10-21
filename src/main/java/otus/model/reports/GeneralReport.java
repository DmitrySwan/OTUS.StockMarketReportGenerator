package otus.model.reports;

import otus.model.assets.Asset;
import otus.model.assets.InputShare;
import otus.model.assets.OutputShare;
import yahoofinance.Stock;
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
            BigDecimal dividendPerShare = yahooStock.getDividend().getAnnualYield();

            BigDecimal price = stockQuote.getPrice();

            InputShare inputShare = (InputShare) asset;
            BigDecimal purchasePrice = inputShare.getPurchasePrice();
            int count = inputShare.getCount();

            OutputShare outputShare = new OutputShare(
                    ticker,
                    purchasePrice,
                    count,
                    yahooStock.getName(),
                    price,
                    stockQuote.getChange(),
                    stockQuote.getChangeInPercent(),
                    dividendPerShare
            );

            BigDecimal pricePerShareChange = price.subtract(purchasePrice);
            BigDecimal countBigDecimal = BigDecimal.valueOf(count);

            outputShare.setPriceChange(pricePerShareChange.multiply(countBigDecimal));
            outputShare.setPriceChangeInPercent(
                    pricePerShareChange.multiply(ONE_HUNDRED).divide(purchasePrice, 2, RoundingMode.HALF_UP));
            outputShare.setDividends(
                    dividendPerShare == null ? BigDecimal.ZERO : dividendPerShare.multiply(countBigDecimal));

            outputAssets.add(outputShare);
        });
        return outputAssets;
    }
}
