package sb1.lip.research.rest.spring.api;

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
}