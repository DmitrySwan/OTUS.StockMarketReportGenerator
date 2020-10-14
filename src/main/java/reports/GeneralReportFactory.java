package reports;

import assets.InputAsset;

import java.util.List;

public class GeneralReportFactory implements ReportFactory {
    @Override
    public ReportFormat createReportFormat() {
        return new GeneralReport();
    }
}
