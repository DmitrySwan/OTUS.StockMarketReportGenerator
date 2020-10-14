package reports;

import assets.InputAsset;

import java.util.List;

public class CustomReportFactory implements ReportFactory {
    @Override
    public ReportFormat createReportFormat() {
        return new CustomReport();
    }
}
