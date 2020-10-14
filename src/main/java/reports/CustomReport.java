package reports;

import assets.InputAsset;
import assets.InputDefaultAsset;
import assets.InputStock;

import java.util.List;

public class CustomReport extends AbstractReportFormat implements ReportFormat {

    @Override
    public void report(List<InputAsset> assets) {

    }

    @Override
    public Class<? extends InputDefaultAsset> getAssetClass() {
        return InputStock.class;
    }
}
