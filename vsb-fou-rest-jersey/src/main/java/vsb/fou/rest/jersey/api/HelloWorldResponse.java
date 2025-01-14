package vsb.fou.rest.jersey.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.xml.bind.annotation.XmlRootElement;

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
        return String.format("HelloWorldResponse[%s, resultDataList:%s]", metadata, resultDataList);
    }
}
