package reports;

import assets.InputAsset;

import java.util.List;

public class SimpleReportFactory implements ReportFactory {

    @Override
    public ReportFormat createReportFormat() {
        return new SimpleReport();
    }
}
