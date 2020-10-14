package reports;

import assets.InputAsset;
import assets.InputDefaultAsset;
import assets.InputShare;

import java.util.List;

public class GeneralReport extends AbstractReportFormat implements ReportFormat {
    @Override
    public void report(List<InputAsset> assets) {

    }

    @Override
    public Class<? extends InputDefaultAsset> getAssetClass() {
        return InputShare.class;
    }
}
