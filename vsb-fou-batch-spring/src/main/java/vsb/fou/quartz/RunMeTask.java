package vsb.fou.quartz;

import org.springframework.stereotype.Component;

@Component
public class RunMeTask {

    public void printMe() {
        System.out.println("Spring 3.2.4 + Quartz 2.2.1 ~");
    }
}
