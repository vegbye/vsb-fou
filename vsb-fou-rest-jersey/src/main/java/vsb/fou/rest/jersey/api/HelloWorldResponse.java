package vsb.fou.rest.jersey.api;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Vegard S. Bye
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = false)
public class HelloWorldResponse {

    private Metadata metadata;
    private List<ResultData> resultDataList;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<ResultData> getResultDataList() {
        return resultDataList;
    }

    public void setResultDataList(List<ResultData> resultDataList) {
        this.resultDataList = resultDataList;
    }

    @Override
    public String toString() {
        return "HelloWorldResponse{" +
                "metadata=" + metadata +
                ", resultData='" + resultDataList + '\'' +
                '}';
    }
}
