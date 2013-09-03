package vsb.kladd;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class VsbDataB implements Comparable<VsbDataB>, Cloneable {

    private String ss;
    private int kk;

    @Override
    public VsbDataB clone() {
        try {
            return (VsbDataB) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Skal ikke skje!!!", e);
        }
    }

    @Override
    public String toString() {
        ToStringBuilder toStringBuilder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
        toStringBuilder.appendSuper(super.toString());
        toStringBuilder.append("ss", ss);
        toStringBuilder.append("kk", kk);
        return toStringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof VsbDataB) {
            VsbDataB that = (VsbDataB) obj;
            EqualsBuilder equalsBuilder = new EqualsBuilder();
            equalsBuilder.append(this.ss, that.ss);
            equalsBuilder.append(this.kk, that.kk);
            return equalsBuilder.isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        // you pick hard-coded, randomly chosen, non-zero, odd number
        // ideally different for each class
        return new HashCodeBuilder(17, 37).append(ss).append(kk).toHashCode();
    }

    public int compareTo(VsbDataB that) {
        return new CompareToBuilder().append(this.ss, that.ss).append(this.kk, that.kk).toComparison();
    }

    public void setSS(String a) {
        this.ss = a;
    }

    public String getSS() {
        return ss;
    }

    public void setKK(int k) {
        this.kk = k;
    }

    public int getKK() {
        return kk;
    }
}
