package vsb.fou.jms.activemq.common;

/**
 * Author: Simen Sjetne
 */
public class JmsMessageUtil {

    public static String getCorrelationIDMessageSelector(String messageId) {
        return String.format("%s = '%s'", "JMSCorrelationID", messageId);
    }

}
