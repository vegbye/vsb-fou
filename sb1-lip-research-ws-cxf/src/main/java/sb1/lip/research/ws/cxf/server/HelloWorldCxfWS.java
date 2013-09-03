package sb1.lip.research.ws.cxf.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sb1.lip.research.service._2013._08.PingRequest;
import sb1.lip.research.service._2013._08.PingResponse;
import sb1.lip.research.service._2013._08.ResearchPortType;

import javax.jws.WebService;
import java.util.Date;

/**
 * @author Vegard S. Bye
 */
@WebService
@Service
public class HelloWorldCxfWS implements ResearchPortType {

    private static final Logger REQUEST_LOGGER = LoggerFactory.getLogger("REQUEST." + HelloWorldCxfWS.class.getSimpleName());
    private static final Logger RESPONSE_LOGGER = LoggerFactory.getLogger("RESPONSE." + HelloWorldCxfWS.class.getSimpleName());

    @Override
    public PingResponse ping(PingRequest request) {
        REQUEST_LOGGER.info("request.msg:" + request.getMsg());
        String msg = request.getMsg();
        String svar = msg + " " + new Date();
        PingResponse response = new PingResponse();
        response.setSvar(svar);
        RESPONSE_LOGGER.info("response.svar:" + response.getSvar());
        return response;
    }
}
