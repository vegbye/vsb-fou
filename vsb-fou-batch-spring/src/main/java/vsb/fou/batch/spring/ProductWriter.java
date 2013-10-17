package vsb.fou.batch.spring;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Vegard S. Bye
 */
@Component
public class ProductWriter implements ItemWriter<Product> {

    @Resource
    private ProductDao productDao;

    public void write(List<? extends Product> items) throws Exception {
        for (Product item : items) {
            productDao.updateProduct(item);
        }
    }
}