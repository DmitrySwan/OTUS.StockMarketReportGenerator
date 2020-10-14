package reports;

import assets.GeneralStock;
import assets.InputAsset;
import assets.InputDefaultAsset;
import yahoofinance.Stock;

import java.util.List;
import java.util.StringJoiner;

public class SimpleReport extends AbstractReportFormat implements ReportFormat {

    @Override
    public void report(List<InputAsset> assets) {
        List<GeneralStock> stocks = getGeneralStocks(assets);
        System.out.println("test33 " +stocks);
        stocks.stream().forEach(stock -> {
            StringJoiner joiner = new StringJoiner(" ");
            joiner.add(stock.getTicker());
            Stock yahooStock = stock.getStock();
            joiner.add(yahooStock.getName());
            joiner.add(yahooStock.getQuote().getPrice().toString());
            joiner.add(yahooStock.getQuote().getChangeInPercent().toString());
            joiner.add(yahooStock.getDividend().getAnnualYield().toString());
            System.out.println(joiner);
        });
    }

    @Override
    public Class<? extends InputDefaultAsset> getAssetClass() {
        return InputDefaultAsset.class;
    }
}
