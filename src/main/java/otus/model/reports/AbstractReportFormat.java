package otus.model.reports;

import org.apache.log4j.Logger;
import otus.model.assets.Asset;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AbstractReportFormat {

    private static Logger log = Logger.getLogger(AbstractReportFormat.class);


    Map<String, Stock> getStocks(List<Asset> assets) throws IOException {
        String[] tickers = assets.stream().map(Asset::getTicker).toArray(String[]::new);
        Map<String, Stock> stockMap = YahooFinance.get(tickers, true);
        log.info("stocks " + stockMap);
        return YahooFinance.get(tickers, true);
    }
}
