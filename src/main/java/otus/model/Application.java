package otus.model;

import otus.model.assets.Asset;
import otus.model.reports.ReportFactory;
import otus.model.reports.ReportFormat;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Application {

    private ReportFormat reportFormat;

    public Application(ReportFactory factory) {
        this.reportFormat = factory.createReportFormat();
    }

    public List<Asset> report(List<Asset> inputAssets) {
        try {
            return reportFormat.createReportData(inputAssets);
        } catch (IOException e) {
            return new LinkedList<>(); // если из API ничего не получили
        }
    }

/*    private void writeData(List<String[]> outputArrays) {
        try (FileWriter output = new FileWriter(outputFile);
             CSVWriter write = new CSVWriter(output)) {
            outputArrays.stream().forEach(write::writeNext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
