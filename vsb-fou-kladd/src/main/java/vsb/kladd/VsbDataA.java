package vsb.kladd;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;


public class VsbDataA implements Comparable<VsbDataA>, Cloneable {

    private String s;
    private int k;

    @Override
    public VsbDataA clone() {
        try {
            return (VsbDataA) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Skal ikke skje!!!", e);
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toStringX() {



        ToStringBuilder toStringBuilder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
        toStringBuilder.append("s", s);
        toStringBuilder.append("k", k);
        return toStringBuilder.toString();
    }

    @Override
    public boolean equals(Object annen) {
        return EqualsBuilder.reflectionEquals(this, annen);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public int compareTo(VsbDataA that) {
        return CompareToBuilder.reflectionCompare(this, that);
    }

    public void setS(String a) {
        this.s = a;
    }

    public String getS() {
        return s;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getK() {
        return k;
    }

}
