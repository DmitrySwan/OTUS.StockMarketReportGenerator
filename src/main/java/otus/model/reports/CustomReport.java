package otus.model.reports;

import org.apache.commons.lang3.StringUtils;
import otus.model.assets.Asset;
import otus.model.assets.InputStock;
import otus.model.assets.OutputStock;
import yahoofinance.Stock;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomReport extends AbstractReportFormat implements ReportFormat {

    private static final String SECTOR = "sector";
    private ChangeAverage average;

    public CustomReport(ChangeAverage average) {
        super();
        this.average = average;
    }

    @Override
    public List<Asset> createReportData(List<Asset> assets) throws IOException {
        Map<String, Stock> stocks = getStocks(assets);
        List<OutputStock> outputStocks = createOutputStocks(assets, stocks);
        return getListWithSectorResult(outputStocks);
    }

    private List<OutputStock> createOutputStocks(List<Asset> assets, Map<String, Stock> stocks) {
        List<OutputStock> outputStocks = new LinkedList<>();
        assets.stream().forEach(asset -> {
            Stock yahooStock = stocks.get(asset.getTicker());
            InputStock inputStock = (InputStock) asset;
            StockQuote quote = yahooStock.getQuote();
            OutputStock outputStock =
                    new OutputStock(asset.getTicker(), inputStock.getSectorId(),  yahooStock.getName(), quote.getPrice());
            outputStock = setChangeAndChangeInPercent(outputStock, yahooStock.getQuote());
            outputStocks.add(outputStock);
        });
        return outputStocks;
    }

    private OutputStock setChangeAndChangeInPercent(OutputStock stock, StockQuote quote) {
        BigDecimal change;
        BigDecimal changeInPercent;
        if (ChangeAverage.LAST_FIFTY_DAYS.equals(average)) {
            change = quote.getChangeFromAvg50();
            changeInPercent = quote.getChangeFromAvg50InPercent();
        } else if (ChangeAverage.LAST_TWO_HUNDRED_DAYS.equals(average)) {
            change = quote.getChangeFromAvg200();
            changeInPercent = quote.getChangeFromAvg200InPercent();
        } else {
            change = quote.getChange();
            changeInPercent = quote.getChangeInPercent();
        }
        stock.setChange(change);
        stock.setChangeInPercent(changeInPercent);
        return stock;
    }

    private List<Asset> getListWithSectorResult(List<OutputStock> outputStocks) {
        Map<String, List<OutputStock>> map = getMapBySectorId(outputStocks);
        map.entrySet().stream().forEach(entry -> addToListSectorResult(entry.getKey(), entry.getValue()));
        return map.values().stream().flatMap(List::stream).collect(Collectors.toList());
    }

    private static Map<String, List<OutputStock>> getMapBySectorId(List<OutputStock> outputStocks) {
        Map<String, List<OutputStock>> averageToAssetsMap = new HashMap<>();
        outputStocks.stream().forEach(stock -> {
                    String sectorId = stock.getSectorId();
                    averageToAssetsMap.putIfAbsent(sectorId, new LinkedList<>());
                    averageToAssetsMap.get(sectorId).add(stock);
                }
        );
        return averageToAssetsMap;
    }

    private void addToListSectorResult(String sectorId, List<OutputStock> stocks) {
        OutputStock sectorResultStock = new OutputStock(StringUtils.EMPTY, StringUtils.EMPTY);
        BigDecimal sectorChangeInPercentSum = stocks.stream().map(OutputStock::getChangeInPercent)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal stocksCount = BigDecimal.valueOf(stocks.size());
        BigDecimal mediumChangeInPercent = sectorChangeInPercentSum.divide(stocksCount, 2, RoundingMode.HALF_UP);
        sectorResultStock.setName(sectorId + StringUtils.SPACE + SECTOR);
        sectorResultStock.setChangeInPercent(mediumChangeInPercent);
        stocks.add(sectorResultStock);
    }

    public enum ChangeAverage {
        LAST_DAY("last day"),
        LAST_FIFTY_DAYS("last 50 days"),
        LAST_TWO_HUNDRED_DAYS("last 200 days");

        private final String description;

        ChangeAverage(String description) {
            this.description = description;
        }

        public String getDescription() {
            return this.description;
        }
    }
}

