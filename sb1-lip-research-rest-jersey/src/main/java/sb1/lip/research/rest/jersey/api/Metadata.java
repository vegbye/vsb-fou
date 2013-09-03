package sb1.lip.research.rest.jersey.api;

/**
 * @author Vegard S. Bye
 */
public class Metadata {

    private String messageId;
    private String senderId;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "messageId='" + messageId + '\'' +
                ", senderId='" + senderId + '\'' +
                '}';
    }
}
