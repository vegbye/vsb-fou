package vsb.fou.batch.spring.productjob.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.incrementer.AbstractSequenceMaxValueIncrementer;
import org.springframework.stereotype.Repository;
import vsb.fou.batch.spring.productjob.domain.Product;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author Vegard S. Bye
 */
@Repository
public class ProductDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDao.class);
    @Autowired
    public AbstractSequenceMaxValueIncrementer productSequence;
    private SimpleJdbcInsert insertProduct;
    private UpdateProductSql updateProduct;
    private DeleteProductSql deleteProduct;
    private SelectProductSql selectProduct;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        insertProduct = new SimpleJdbcInsert(dataSource)
                .withSchemaName("VSB")
                .withTableName("PRODUCT");
        updateProduct = new UpdateProductSql(dataSource);
        deleteProduct = new DeleteProductSql(dataSource);
        selectProduct = new SelectProductSql(dataSource);
    }

    public int insertOrUpdateProduct(Product product) {
        if (product.getId() == null) {
            return insertProduct(product);
        } else {
            return updateProduct(product);
        }
    }

    private int insertProduct(Product product) {
        int id = productSequence.nextIntValue();
        product.setId(id);
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("ID", id)
                .addValue("NAME", product.getName())
                .addValue("DESCRIPTION", product.getDescription())
                .addValue("PRICE", product.getPrice());

        insertProduct.execute(parameters);
        LOGGER.info("INSERT PRODUCT med id:" + id);
        return id;
    }

    private int updateProduct(Product product) {
        updateProduct.updateProduct(product.getId(), product.getName(), product.getDescription(), product.getPrice());
        LOGGER.info("UPDATE PRODUCT med id:" + product.getId());
        return product.getId();
    }

    public Product getProduct(int id) {
        List<Product> products = selectProduct.execute(id);
        if (products.size() > 1) {
            throw new IllegalStateException("Fant flere enn 1 produkt med primary-key:" + id + " " + products);
        }
        if (products.isEmpty()) {
            return null;
        }
        Product product = products.get(0);
        LOGGER.info("Hentet produkt fra tabell:" + product);
        return product;
    }

    public void deleteProduct(int id) {
        deleteProduct.delete(id);
        LOGGER.info("Slettet produkt fra tabell med id:" + id);
    }
}
