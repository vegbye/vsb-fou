package vsb.fou.rest.jersey.api;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Vegard S. Bye
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = false)
public class HelloWorldRequest {

    private Metadata metadata;
    private String msg;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "HelloWorldRequest{" +
                "metadata=" + metadata +
                ", msg='" + msg + '\'' +
                '}';
    }
}
