package vsb.fou.rest.spring.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vsb.fou.rest.spring.api.HelloWorldRequest;
import vsb.fou.rest.spring.api.HelloWorldResponse;

@Service
public class HelloWorldClient {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${vsb.fou.rest.baseurl}")
    private String restUrl;

    public String doExeute(String id) {
        return restTemplate.getForObject(restUrl + "/rest/research/" + id, String.class);
    }

    public ResponseEntity<HelloWorldResponse> sayHello(HelloWorldRequest request) {
        return restTemplate.postForEntity(restUrl + "/rest/research/", request, HelloWorldResponse.class);
    }
}
