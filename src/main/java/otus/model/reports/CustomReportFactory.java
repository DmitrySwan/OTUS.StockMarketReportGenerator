package otus.model.reports;

public class CustomReportFactory implements ReportFactory {
    @Override
    public ReportFormat createReportFormat() {
        return new CustomReport();
    }
}
