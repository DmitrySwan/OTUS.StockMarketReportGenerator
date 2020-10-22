package otus.model.reports;

public class CustomReportFactory implements ReportFactory {
    CustomReport.ChangeAverage average;
    public CustomReportFactory(CustomReport.ChangeAverage average) {
        this.average = average;
    }

    @Override
    public ReportFormat createReportFormat() {
        return new CustomReport(average);
    }
}
