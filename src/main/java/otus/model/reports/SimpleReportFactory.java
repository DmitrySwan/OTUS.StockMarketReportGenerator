package otus.model.reports;

public class SimpleReportFactory implements ReportFactory {

    @Override
    public ReportFormat createReportFormat() {
        return new SimpleReport();
    }
}
