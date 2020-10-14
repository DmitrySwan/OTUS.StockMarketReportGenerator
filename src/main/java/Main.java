import org.apache.commons.cli.CommandLine;
import reports.CustomReportFactory;
import reports.GeneralReportFactory;
import reports.SimpleReportFactory;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.File;
import java.io.IOException;

public class Main {
    static final String SIMPLE = "simple";
    static final String GENERAL = "general";
    static final String CUSTOM = "custom";
    /**
     * Приложение выбирает тип и создаёт конкретные фабрики динамически исходя
     * из конфигурации или окружения.
     */
    private static Application configureApplication(CommandLine cl) {
        String reportType = cl.getOptionValue(CommandLineParser.REPORT);
        Application app;
        if (SIMPLE.equalsIgnoreCase(reportType)) {
            app = new Application(new SimpleReportFactory());
        } else if (GENERAL.equalsIgnoreCase(reportType)) {
            app = new Application(new GeneralReportFactory());
        } else {
            app = new Application(new CustomReportFactory());
        }
        app.setInputFile(new File(cl.getOptionValue(CommandLineParser.INPUT)));
        app.setOutputFile(new File(cl.getOptionValue(CommandLineParser.OUTPUT)));
        return app;
    }

    public static void main(String[] args) {
        Application app = configureApplication(CommandLineParser.parseCMDArgs(args));
        app.report();
       // Stock stock = YahooFinance.get("INTC", true);

       /* FxQuote fxQuote = YahooFinance.getFx("USDRUB=X");
        System.out.reportln(fxQuote.getPrice());
        BigDecimal price = stock.getQuote().getPrice();
        BigDecimal change = stock.getQuote().getChangeInPercent();
        BigDecimal peg = stock.getStats().getPeg();*/
        //BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
    }
}
