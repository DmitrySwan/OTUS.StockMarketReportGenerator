package otus.model.reports;

public class GeneralReportFactory implements ReportFactory {
    @Override
    public ReportFormat createReportFormat() {
        return new GeneralReport();
    }
}
