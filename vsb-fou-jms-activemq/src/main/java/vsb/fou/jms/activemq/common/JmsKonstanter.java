package vsb.fou.jms.activemq.common;

/**
 * @author Vegard S. Bye
 */
public final class JmsKonstanter {

    public static final String ASYNC_REQUEST_QUEUE = "asyncRequestQueue";
    public static final String SYNC_REQUEST_QUEUE = "syncRequestQueue";
    public static final String SYNC_REPLY_QUEUE = "syncReplyQueue";
    public static final String BROKER_URL = "tcp://localhost:61616";
    public static final String BROKER_NAME = "vsb-fou-broker";

    private JmsKonstanter() {
    }

}
