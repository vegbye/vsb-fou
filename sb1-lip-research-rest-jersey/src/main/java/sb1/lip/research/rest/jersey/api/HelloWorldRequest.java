package sb1.lip.research.rest.jersey.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Vegard S. Bye
 */
@XmlRootElement
public class HelloWorldRequest {

    @XmlElement
    public Metadata metadata;
    @XmlElement
    public String msg;

    @Override
    public String toString() {
        return "HelloWorldRequest{" +
                "metadata=" + metadata +
                ", msg='" + msg + '\'' +
                '}';
    }
}
