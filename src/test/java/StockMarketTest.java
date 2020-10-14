import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.contentEqualsIgnoreEOL;

public class StockMarketTest {
        private static final String TARGET_TEST_RESOURCES_PATH = "target/test-classes/";

        @DataProvider
        public Object[][] sortTestData() {
            return new Object[][]{
                    {Main.SIMPLE, "testOutputInsertion.txt", "testOutputFileInsertionExpected.txt"},
                  //  {Main.MERGE, "testOutputMerge.txt", "testOutputFileMergeExpected.txt"},
                  //  {Main.SELECTION, "testOutputSelection.txt", "testOutputFileSelectionExpected.txt"},
            };
        }

        @Test(description = "", dataProvider = "sortTestData")
        public void sortTest(String reportType, String outputFilePath, String outputFileExpectedPath) throws IOException {
            String targetOutputFilePath = TARGET_TEST_RESOURCES_PATH + outputFilePath;
            Main.main(
                    new String[]{
                            "-i",  TARGET_TEST_RESOURCES_PATH + "csvFile.csv",
                            "-o", targetOutputFilePath,
                            "-r", reportType
                    });
            Assert.assertTrue(contentEqualsIgnoreEOL(
                    new File(targetOutputFilePath),
                    new File(TARGET_TEST_RESOURCES_PATH + outputFileExpectedPath),
                    "UTF-8"
            ));
        }
}
