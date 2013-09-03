package vsb.kladd.beanlib;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class VsbBeanLibDataA {

    private String s;
    private int k;

    private VsbBeanLibEnumA enumA;
    private VsbBeanLibDataB b;
    private VsbBeanLibDataC c;
    private List<VsbBeanLibDataC> cList;
    private List<VsbBeanLibDataE> eList;

    public VsbBeanLibDataA() {
        this.cList = new ArrayList<VsbBeanLibDataC>();
        this.eList = new ArrayList<VsbBeanLibDataE>();
    }

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

    public void setC(VsbBeanLibDataC vsbBeanLibDataC) {
        this.c = vsbBeanLibDataC;
    }

    public VsbBeanLibDataC getC() {
        return c;
    }

    public void setCList(List<VsbBeanLibDataC> cList) {
        this.cList = cList;
    }

    public List<VsbBeanLibDataC> getCList() {
        return cList;
    }

    public void setEnumA(VsbBeanLibEnumA enumA) {
        this.enumA = enumA;
    }

    public VsbBeanLibEnumA getEnumA() {
        return enumA;
    }

    public void setEList(List<VsbBeanLibDataE> eList) {
        this.eList = eList;
    }

    public List<VsbBeanLibDataE> getEList() {
        return eList;
    }

    public void setB(VsbBeanLibDataB b) {
        this.b = b;
    }

    public VsbBeanLibDataB getB() {
        return b;
    }
}
