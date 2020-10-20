package otus.model;

import otus.model.assets.Asset;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import org.apache.log4j.Logger;
import otus.model.reports.ReportFactory;
import otus.model.reports.ReportFormat;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Application {

    private static Logger log = Logger.getLogger(Application.class);

    private ReportFormat reportFormat;
    private File inputFile;
    private File outputFile;

    void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public  Application(ReportFactory factory) {
        this.inputFile = new File("csvFile.csv");
        this.reportFormat = factory.createReportFormat();
    }

    public List<Asset> report(List<Asset> inputAssets) {
        try {
            return reportFormat.createReportData(inputAssets);
        } catch (IOException e) {
            return new LinkedList<>(); // если из API ничего не получили
        }
        //writeData(outputArrays);
    }

    private void writeData(List<String[]> outputArrays) {
        try(FileWriter output = new FileWriter(outputFile);
            CSVWriter write = new CSVWriter(output)) {
            outputArrays.stream().forEach(write::writeNext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/*    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<Asset> parseInputFile() {
        try (FileReader fileReader = new FileReader(inputFile)) {
            CSVReader csvReader = new CSVReader(fileReader);
            CsvToBean bean = new CsvToBean();
            bean.setCsvReader(csvReader);
            bean.setMappingStrategy(setColumnMapping());
            return bean.parse();
        } catch (Exception e) {
            log.error("File " + inputFile + " can't be parsed in CSV format.");
        }
        return null;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private ColumnPositionMappingStrategy setColumnMapping() {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(reportFormat.getAssetClass());
        String[] columns = getFields(reportFormat.getAssetClass());
        strategy.setColumnMapping(columns);
        return strategy;
    }

    private String[] getFields(Class clazz) {
        List<Field> fields = new ArrayList<>();
        while (clazz != Object.class) {
            fields.addAll(0, Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields.stream().map(Field::getName).toArray(String[]::new);
    }*/
}
