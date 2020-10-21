package otus.model.reports;

import otus.model.assets.Asset;

import java.io.IOException;
import java.util.List;

public interface ReportFormat {
    List<Asset> createReportData(List<Asset> assets) throws IOException;
}