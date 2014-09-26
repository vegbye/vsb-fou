package vsb.fou.kladd.argsparsing;

import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;
import com.martiansoftware.jsap.Switch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VsbJsap {

    private static final Logger LOGGER = LoggerFactory.getLogger(VsbJsap.class);

    public static void main(String[] args) throws Exception {

        JSAPResult result = setupArgumentParsing(args);

        String fomdato = result.getString("fomdato");
        LOGGER.info(fomdato);
        String tildato = result.getString("tildato");
        LOGGER.info(tildato);
        boolean kallTjeneste = result.getBoolean("kallTjeneste");
        LOGGER.info("Kall:" + kallTjeneste);
    }

    private static JSAPResult setupArgumentParsing(final String[] args) throws JSAPException {
        JSAP jsap = new JSAP(); // use Java Simple Argument Parser

        FlaggedOption fomdato = new FlaggedOption("fomdato").setStringParser(JSAP.STRING_PARSER).setRequired(false).setShortFlag('f').setLongFlag("fomdato");
        FlaggedOption tildato = new FlaggedOption("tildato").setStringParser(JSAP.STRING_PARSER).setRequired(false).setShortFlag('t').setLongFlag("tildato");

        /* create some switches */
        Switch kallTjeneste = new Switch("kallTjeneste").setLongFlag("kallTjeneste").setShortFlag('k');
        jsap.registerParameter(fomdato);
        jsap.registerParameter(tildato);
        jsap.registerParameter(kallTjeneste);
        return jsap.parse(args);
    }
}
