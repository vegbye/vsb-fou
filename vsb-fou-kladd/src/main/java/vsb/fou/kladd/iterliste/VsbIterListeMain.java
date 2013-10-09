package vsb.fou.kladd.iterliste;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class VsbIterListeMain {

    private static final Logger log = Logger.getLogger(VsbIterListeMain.class);

    public static void main(String[] args) throws Exception {

        log.info("start");

        Random rand = new Random();
        final List<IterListeData> liste = new ArrayList<IterListeData>();
        for (int i = 0; i < 100; i++) {
            IterListeData data = new IterListeData();
            data.setMyData(Integer.toString(rand.nextInt(100)));
            liste.add(data);
        }

        for (int i = 0; i < 100; i++) {
            Worker worker = new Worker(i, liste);
            Thread t = new Thread(worker);
            t.start();
        }
        log.info("end");
    }
}

class Worker implements Runnable {

    private static final Logger log = Logger.getLogger(Worker.class);

    private final int id;
    private final List<IterListeData> liste;

    public Worker(int id, List<IterListeData> liste) {
        this.id = id;
        this.liste = liste;
    }

    public void run() {
        for (IterListeData data : liste) {
            try {
                Thread.sleep(14L);
            } catch (InterruptedException e) {
                log.error("en feil?", e);
            }
            log.info(data);
        }
        // liste.get(0).setMyData("984359");
        Collections.sort(liste);
        log.info(id + " ferdig");
    }
}

class IterListeData implements Comparable<IterListeData> {

    private String myData;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public boolean equals(Object annen) {
        return EqualsBuilder.reflectionEquals(this, annen);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public int compareTo(IterListeData that) {
        return CompareToBuilder.reflectionCompare(this, that);
    }

    public void setMyData(String myData) {
        this.myData = myData;
    }

    public String getMyData() {
        return myData;
    }
}
