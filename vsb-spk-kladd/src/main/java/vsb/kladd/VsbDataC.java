package vsb.kladd;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class VsbDataC implements Comparable<VsbDataC> {

    private String data;

    @Override
    public boolean equals(Object annen) {
        return EqualsBuilder.reflectionEquals(this, annen);
    }

    public int compareTo(VsbDataC that) {
        return CompareToBuilder.reflectionCompare(this, that);
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

}
