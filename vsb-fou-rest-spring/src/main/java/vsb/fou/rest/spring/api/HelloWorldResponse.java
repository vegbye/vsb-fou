package vsb.fou.rest.spring.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HelloWorldResponse {

    @XmlElement
    public Metadata metadata;

    @XmlElement
    public String result;
}
