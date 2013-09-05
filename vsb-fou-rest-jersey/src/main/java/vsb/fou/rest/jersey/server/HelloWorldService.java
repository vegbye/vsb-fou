package vsb.fou.rest.jersey.server;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Vegard S. Bye
 */
@Service
public class HelloWorldService {

    public String sayHello(String id) {
        return "Hello '" + id + "'" + new Date();
    }
}
