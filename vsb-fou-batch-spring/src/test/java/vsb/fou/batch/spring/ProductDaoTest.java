package vsb.fou.batch.spring;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Vegard S. Bye
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MainCtxSpringBatch.class, TestCtxSpringBatch.class})
public class ProductDaoTest {

    @Resource
    private ProductDao productDao;

    @Test
    public void test_insert() throws Exception {
        Product product = new Product();
        product.setName("produkt navnet");
        product.setDescription("beskrivelsen");
        product.setPrice(new BigDecimal("123456.789"));
        int id = productDao.updateProduct(product);
        Product insertedProduct = productDao.getProduct(id);
        assertThat(insertedProduct.getId(), is(id));
        assertThat(insertedProduct.getName(), is(product.getName()));
        assertThat(insertedProduct.getDescription(), is(product.getDescription()));
        assertThat(insertedProduct.getPrice(), is(product.getPrice()));
    }

    @Test
    public void test_delete() throws Exception {
        Product product = new Product();
        product.setName("produkt navnet");
        product.setDescription("beskrivelsen");
        product.setPrice(new BigDecimal("123456.789"));
        int id = productDao.updateProduct(product);

        productDao.deleteProduct(id);

        Product insertedProduct = productDao.getProduct(id);

        assertThat(insertedProduct, CoreMatchers.nullValue());
    }

}
