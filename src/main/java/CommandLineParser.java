import org.apache.commons.cli.*;

public class CommandLineParser {
    public static final String REPORT = "report";
    public static final String INPUT = "input";
    public static final String OUTPUT = "output";

    private static Options options = new Options();

    /*$> java -jar target/my-utility.jar -i asd
    Missing required option: o

    usage: utility-name
    -i,--input <arg>    input file path
    -o,--output <arg>   output file*/
    public static CommandLine parseCMDArgs(String[] args) {
        configureOption("i", INPUT, "input file path");
        configureOption("o", OUTPUT, "output file");
        configureOption("r", REPORT, "report type");

        CommandLine cmd = null;
        org.apache.commons.cli.CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }

        return cmd;
    }

    private static void configureOption(String opt, String longOpt, String description) {
        Option option = new Option(opt, longOpt, true, description);
        option.setRequired(true);
        options.addOption(option);
    }
}
