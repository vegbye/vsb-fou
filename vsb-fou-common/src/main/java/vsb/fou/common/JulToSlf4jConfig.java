package vsb.fou.common;

public final class JulToSlf4jConfig {

    private JulToSlf4jConfig() {
    }

    public static void bridgeJulToSlf4j() {
        org.slf4j.bridge.SLF4JBridgeHandler.removeHandlersForRootLogger();
        org.slf4j.bridge.SLF4JBridgeHandler.install();
    }

}
