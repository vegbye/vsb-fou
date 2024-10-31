package vsb.fou.rest.spring.api;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HelloWorldRequest {

    @XmlElement
    public Metadata metadata;

    @XmlElement
    public String msg;
}
