package reports;

import assets.GeneralStock;
import assets.InputAsset;
import assets.GeneralDefaultStock;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AbstractReportFormat {
    private List<GeneralStock> generalStocks;

    List<GeneralStock> getGeneralStocks(List<InputAsset> assets) {
        if(generalStocks == null) {
            try {
                fillGeneralStocks(assets);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.generalStocks;
    }

    private void fillGeneralStocks(List<InputAsset> assets) throws IOException {
        String[] tickers = assets.stream().map(InputAsset::getTicker).toArray(String[]::new);
        Map<String, Stock> stocks = YahooFinance.get(tickers, true);
        generalStocks = new LinkedList<>();
        assets.stream().forEach(asset -> {
            String ticker = asset.getTicker();
            generalStocks.add(new GeneralDefaultStock(ticker, asset, stocks.get(ticker)));
        });
    }


}
