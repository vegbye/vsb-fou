package vsb.fou.batch.spring.job;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vsb.fou.batch.spring.dao.ProductDao;
import vsb.fou.batch.spring.domain.Product;

import java.util.List;

/**
 * @author Vegard S. Bye
 */
@Component
public class ProductWriter implements ItemWriter<Product> {

    @Autowired
    private ProductDao productDao;

    public void write(List<? extends Product> items) throws Exception {
        for (Product item : items) {
            productDao.updateProduct(item);
        }
    }
}