package vsb.fou.kladd.argsparsing;

import com.martiansoftware.jsap.*;

public class VsbJsap {

    public static void main(String[] args) throws Exception {

        JSAPResult result = setupArgumentParsing(args);

        String fomdato = result.getString("fomdato");
        System.out.println(fomdato);
        String tildato = result.getString("tildato");
        System.out.println(tildato);
        boolean kallTjeneste = result.getBoolean("kallTjeneste");
        System.out.println(kallTjeneste);
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
