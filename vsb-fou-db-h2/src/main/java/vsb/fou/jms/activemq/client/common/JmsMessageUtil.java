package vsb.fou.jms.activemq.client.common;

/**
 * Author: Simen Sjetne
 */
public class JmsMessageUtil {

    public static String getCorrelationIDMessageSelector(String messageId) {
        return String.format("%s = '%s'", "JMSCorrelationID", messageId);
    }

}
