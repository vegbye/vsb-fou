package vsb.fou.ws.cxf.server;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Vegard S. Bye
 */
@Service
public class HelloWorldCxfService {

    public String hallo(String msg) {
        return "HelloWorldCxfService: " + msg + " " + new Date();
    }
}
