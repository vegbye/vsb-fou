package vsb.fou.batch.spring.job.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.incrementer.AbstractSequenceMaxValueIncrementer;
import org.springframework.stereotype.Repository;
import vsb.fou.batch.spring.job.domain.Product;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vegard S. Bye
 */
@Repository
public class ProductDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDao.class);
    private static final String SELECT_PRODUCT = "SELECT * FROM VSB.PRODUCT WHERE ID=?";
    private static final String DELETE_PRODUCT = "DELETE FROM VSB.PRODUCT WHERE ID=?";
    @Autowired
    public AbstractSequenceMaxValueIncrementer productSequence;
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertProduct;
    private UpdateProductSql updateProduct;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        insertProduct = new SimpleJdbcInsert(dataSource)
                .withSchemaName("VSB")
                .withTableName("PRODUCT");
        updateProduct = new UpdateProductSql(dataSource);
    }

    public int insertOrUpdateProduct(Product product) {
        if (product.getId() == null) {
            return insertProduct(product);
        } else {
            return updateProduct(product);
        }
    }

    private int updateProduct(Product product) {
        updateProduct.execute(product.getId(), product.getName(), product.getDescription(), product.getPrice());
        return product.getId();
    }

    private int insertProduct(Product product) {
        Map<String, Object> parameters = new HashMap<>();
        int id = productSequence.nextIntValue();
        parameters.put("ID", id);
        parameters.put("NAME", product.getName());
        parameters.put("DESCRIPTION", product.getDescription());
        parameters.put("PRICE", product.getPrice());

        insertProduct.execute(parameters);
        LOGGER.info("Oppdatert database tabell PRODUCT for id:" + id);
        return id;
    }

    public Product getProduct(int id) {
        List<Product> products = jdbcTemplate.query(SELECT_PRODUCT, new String[]{Integer.toString(id)}, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();
                product.setId(rs.getInt("ID"));
                product.setName(rs.getString("NAME"));
                product.setDescription(rs.getString("DESCRIPTION"));
                product.setPrice(rs.getBigDecimal("PRICE"));
                return product;
            }
        });
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
        jdbcTemplate.update(DELETE_PRODUCT, new String[]{Integer.toString(id)});
        LOGGER.info("Slettet produkt fra tabell med id:" + id);
    }
}
