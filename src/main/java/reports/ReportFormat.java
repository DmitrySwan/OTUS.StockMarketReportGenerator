package reports;

import assets.InputAsset;
import assets.InputDefaultAsset;

import java.util.List;

public interface ReportFormat {

    void report(List<InputAsset> assets);

    Class<? extends InputDefaultAsset> getAssetClass();
}