package vsb.kladd.beanlib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import net.sf.beanlib.CollectionPropertyName;
import net.sf.beanlib.hibernate.HibernateBeanReplicator;
import net.sf.beanlib.hibernate.HibernatePropertyFilter;
import net.sf.beanlib.hibernate3.Hibernate3BeanReplicator;

import org.apache.log4j.Logger;
import org.junit.Test;

public class VsbBeanLibTest {

    private static final Logger log = Logger.getLogger(VsbBeanLibTest.class);

    @SuppressWarnings("unchecked")
    @Test
    public void testBeanLibAntagelser() {
        Set<CollectionPropertyName<?>> collectionPropertyNameSet = new HashSet<CollectionPropertyName<?>>();
        collectionPropertyNameSet.add(new CollectionPropertyName(VsbBeanLibDataA.class, "CList"));
        collectionPropertyNameSet.add(new CollectionPropertyName(VsbBeanLibDataA.class, "EList"));

        Set<Class<?>> entityBeanClassSet = createSet(VsbBeanLibDataB.class, VsbBeanLibDataC.class, VsbBeanLibDataD.class, VsbBeanLibDataE.class, VsbBeanLibDataF.class);
        HibernatePropertyFilter filter = new HibernatePropertyFilter();
        filter.withEntityBeanClassSet(entityBeanClassSet);
        filter.withCollectionPropertyNameSet(collectionPropertyNameSet);

        HibernateBeanReplicator r = new Hibernate3BeanReplicator();
        r.initPropertyFilter(filter);

        VsbBeanLibDataA fromBean = new VsbBeanLibDataA();
        fromBean.setEnumA(VsbBeanLibEnumA.HvitVin);
        fromBean.setK(56);
        fromBean.setS("SPK Perform");
        fromBean.setC(VsbBeanLibDataC.newInstance("INDIGO", VsbBeanLibDataD.newInstance("RoseVin")));

        VsbBeanLibDataC c = VsbBeanLibDataC.newInstance("BLÅ", VsbBeanLibDataD.newInstance("HvitVin"));
        fromBean.getCList().add(c);
        fromBean.getCList().add(c);
        fromBean.getCList().add(c);

        fromBean.getEList().add(VsbBeanLibDataE.newInstance("Majorstua"));
        fromBean.setB(VsbBeanLibDataB.newInstance("Fagerborg"));

        VsbBeanLibDataA toBean = r.copy(fromBean, VsbBeanLibDataA.class);
        log.info("fromBean: " + fromBean);
        log.info("toBean:   " + toBean);

        assertEquals(fromBean.getB(), toBean.getB());

        assertEquals(3, fromBean.getCList().size());
        assertTrue(fromBean.getCList().get(0) == fromBean.getCList().get(0));
        assertTrue(fromBean.getCList().get(0) == fromBean.getCList().get(1));
        assertTrue(fromBean.getCList().get(0) == fromBean.getCList().get(2));

        assertEquals(3, toBean.getCList().size());
        assertTrue(toBean.getCList().get(0) == toBean.getCList().get(0));
        assertTrue(toBean.getCList().get(0) == toBean.getCList().get(1));
        assertTrue(toBean.getCList().get(0) == toBean.getCList().get(2));

        assertEquals(1, fromBean.getEList().size());
        assertEquals(1, toBean.getEList().size());
        assertEquals(fromBean.getEList().get(0), toBean.getEList().get(0));
        assertTrue(fromBean.getEList().get(0) == fromBean.getEList().get(0));
        assertTrue(toBean.getEList().get(0) == toBean.getEList().get(0));
        assertTrue(fromBean.getEList().get(0) != toBean.getEList().get(0));

        assertEquals(fromBean, fromBean);
        assertEquals(toBean, toBean);
        assertEquals(fromBean, toBean);
    }

    private static Set<Class<?>> createSet(Class<?>... classes) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (int i = 0; i < classes.length; i++) {
            classSet.add(classes[i]);
        }
        return classSet;
    }
}
