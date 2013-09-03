package vsb.kladd.beanlib;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class VsbBeanLibDataE {

    private String data;
    private Date time;
    private VsbBeanLibDataF f;

    public static VsbBeanLibDataE newInstance(String data) {
        VsbBeanLibDataE e = new VsbBeanLibDataE();
        e.setData(data);
        e.setTime(new Date());
        e.setF(VsbBeanLibDataF.newInstance(data + "F"));
        return e;
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

    public void setF(VsbBeanLibDataF f) {
        this.f = f;
    }

    public VsbBeanLibDataF getF() {
        return f;
    }

}
