package vsb.fou.jms.activemq.common;

import javax.jms.JMSException;

/**
 * Author: Simen Sjetne
 */
public class JmsMessageUtil {

    private static final String JMS_CORRELATION_ID = "JMSCorrelationID";
    private static final String STRING_PROP = "%s = '%s'";

    public static void setMessageAndCorrelationId(javax.jms.Message message, String messageId) throws JMSException {
        message.setJMSMessageID(messageId);
        message.setJMSCorrelationID(messageId);
    }

    public static String getJMSCorrelationIDMessageSelector(String messageId) {
        return String.format(STRING_PROP, JMS_CORRELATION_ID, messageId);
    }

}
