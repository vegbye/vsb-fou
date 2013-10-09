package vsb.fou.kladd.argsparsing;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.BooleanOptionHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.kohsuke.args4j.ExampleMode.ALL;

@SuppressWarnings("unused")
public class VsbArsg4j {

    @Option(name = "-r", usage = "recursively run something")
    private boolean recursive;

    @Option(name = "-o", usage = "output to this file", metaVar = "OUTPUT")
    private File out = new File(".");

    @Option(name = "-str")
    // no usage
    private String str = "(default value)";

    @Option(name = "-n", required = true, usage = "repeat <n> times\nusage can have new lines in it and also it can be verrrrrrrrrrrrrrrrrry long")
    private int num = -1;

    // using 'handler=...' allows you to specify a custom OptionHandler
    // implementation class. This allows you to bind a standard Java type
    // with a non-standard option syntax
    @Option(name = "-custom", handler = BooleanOptionHandler.class, usage = "boolean value for checking the custom handler")
    private boolean data;

    // receives other command line parameters than options
    @Argument
    private List<String> arguments = new ArrayList<String>();

    public static void main(String[] args) {
        VsbArsg4j vsbArsg4j = new VsbArsg4j();
        vsbArsg4j.doMain(args);
        System.out.println(vsbArsg4j);
    }

    public void doMain(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        // if you have a wider console, you could increase the value;
        // here 80 is also the default
        parser.setUsageWidth(80);

        try {
            // parse the arguments.
            parser.parseArgument(args);

        } catch (CmdLineException e) {
            // print the list of available options
            parser.printUsage(System.err);
            System.err.println();
            // print option sample. This is useful some time
            System.err.println("Example: java SampleMain" + parser.printExample(ALL));
            System.exit(1);
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
