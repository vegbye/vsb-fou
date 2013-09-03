package vsb.kladd.beanlib;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class VsbBeanLibDataC {

    private String data;
    private Date time;
    private VsbBeanLibDataD d;

    public static VsbBeanLibDataC newInstance(String data, VsbBeanLibDataD d) {
        VsbBeanLibDataC c = new VsbBeanLibDataC();
        c.setData(data);
        c.setTime(new Date());
        c.setD(d);
        return c;
    }

    @Override
    public boolean equals(Object annen) {
        return EqualsBuilder.reflectionEquals(this, annen);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setD(VsbBeanLibDataD d) {
        this.d = d;
    }

    public VsbBeanLibDataD getD() {
        return d;
    }

}
