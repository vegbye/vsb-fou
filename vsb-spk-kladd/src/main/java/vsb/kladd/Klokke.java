package vsb.kladd;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Klokke {

    private final String msg;
    private final long startTid;

    private long stoppTid;
    private long bruktTid;
    private boolean stoppErKalt;

    public Klokke(String msg) {
        this.msg = msg;
        this.startTid = System.currentTimeMillis();
    }

    public void stopp() {
        if (stoppErKalt) {
            throw new IllegalStateException("stopp er allerede kalt!");
        }
        this.stoppTid = System.currentTimeMillis();
        this.bruktTid = stoppTid - startTid;
        stoppErKalt = true;
    }

    @Override
    public String toString() {
        ToStringBuilder toStringBuilder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
        toStringBuilder.append(msg);
        toStringBuilder.append("start", startTid);
        toStringBuilder.append("stopp", stoppTid);
        toStringBuilder.append("brukt", bruktTid);
        return toStringBuilder.toString();
    }

    public void print() {
        System.out.println("\n\n##################");
        System.out.println(this);
        System.out.println("\n\n##################");
    }
}
