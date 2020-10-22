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
        assets.stream().forEach(asset -> outputAssets.add(getOutputShare(asset, stocks))); //todo better
        return outputAssets;
    }

    private OutputShare getOutputShare(Asset asset, Map<String, Stock> stocks) {
        Stock yahooStock = stocks.get(asset.getTicker());
        StockQuote stockQuote = yahooStock.getQuote();
        BigDecimal dividendPerShare = yahooStock.getDividend().getAnnualYield();

        InputShare inputShare = (InputShare) asset;

        OutputShare outputShare = createOutputStock(inputShare, yahooStock.getName(), stockQuote, dividendPerShare);

        BigDecimal purchasePrice = inputShare.getPurchasePrice();
        BigDecimal pricePerShareChange = stockQuote.getPrice().subtract(purchasePrice);
        BigDecimal countBigDecimal = BigDecimal.valueOf(inputShare.getCount());

        outputShare.setPriceChange(pricePerShareChange.multiply(countBigDecimal));
        outputShare.setPriceChangeInPercent(
                pricePerShareChange.multiply(ONE_HUNDRED).divide(purchasePrice, 2, RoundingMode.HALF_UP));
        outputShare.setDividends(
                dividendPerShare == null ? BigDecimal.ZERO : dividendPerShare.multiply(countBigDecimal));
        return outputShare;
    }

    private OutputShare createOutputStock(InputShare share, String name,
                                      StockQuote stockQuote, BigDecimal dividendPerShare) {
        return new OutputShare(
                share,
                name,
                stockQuote.getPrice(),
                stockQuote.getChange(),
                stockQuote.getChangeInPercent(),
                dividendPerShare
        );
    }
}
