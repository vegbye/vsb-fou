package vsb.fou.batch.spring.productjob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;
import vsb.fou.batch.spring.productjob.domain.Product;

import java.util.Arrays;

/**
 * @author Vegard S. Bye
 */
public class ProductFieldSetMapper implements FieldSetMapper<Product> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductFieldSetMapper.class);

    public Product mapFieldSet(FieldSet fieldSet) throws BindException {
        Product product = new Product();
        product.setName(fieldSet.readString("NAME"));
        product.setDescription(fieldSet.readString("DESCRIPTION"));
        product.setPrice(fieldSet.readBigDecimal("PRICE"));
        LOGGER.info(Arrays.toString(fieldSet.getValues()) + " => " + product);
        return product;
    }
}
