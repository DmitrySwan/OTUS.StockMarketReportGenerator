import assets.InputAsset;
import assets.InputDefaultAsset;
import assets.InputStock;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import org.apache.log4j.Logger;
import reports.ReportFormat;
import reports.ReportFactory;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Application {

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

    Application(ReportFactory factory) {
        this.reportFormat = factory.createReportFormat();
    }

    void report() {
        List<InputAsset> assets = parseInputFile();
        reportFormat.report(assets);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<InputAsset> parseInputFile() {
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
    }
}
