package vsb.fou.rest.jersey.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Vegard S. Bye
 */
@XmlRootElement
public class HelloWorldResponse {

    @XmlElement
    public Metadata metadata;
    @XmlElement
    public String result;

    @Override
    public String toString() {
        return "HelloWorldResponse{" +
                "metadata=" + metadata +
                ", result='" + result + '\'' +
                '}';
    }
}
