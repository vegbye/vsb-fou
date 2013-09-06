package vsb.fou.rest.jersey.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Vegard S. Bye
 */
@XmlRootElement
public class HelloWorldResponse {

    @XmlElement
    public Metadata metadata;
    @XmlElement
    public List<ResultData> resultDataList;

    @Override
    public String toString() {
        return "HelloWorldResponse{" +
                "metadata=" + metadata +
                ", resultData='" + resultDataList + '\'' +
                '}';
    }
}
