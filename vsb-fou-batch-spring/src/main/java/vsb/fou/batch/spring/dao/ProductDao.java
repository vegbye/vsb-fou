package vsb.fou.batch.spring.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.incrementer.AbstractSequenceMaxValueIncrementer;
import org.springframework.stereotype.Repository;
import vsb.fou.batch.spring.domain.Product;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Vegard S. Bye
 */
@Repository
public class ProductDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDao.class);
    private static final String INSERT_PRODUCT = "INSERT INTO VSB.PRODUCT " + "(ID,NAME,DESCRIPTION,PRICE) values(?,?,?,?)";
    private static final String UPDATE_PRODUCT = "UPDATE VSB.PRODUCT set " + "NAME=?, DESCRIPTION=?, PRICE=? WHERE ID=?";
    private static final String SELECT_PRODUCT = "SELECT * FROM VSB.PRODUCT WHERE ID=?";
    private static final String DELETE_PRODUCT = "DELETE FROM VSB.PRODUCT WHERE ID=?";
    @Resource
    public AbstractSequenceMaxValueIncrementer productSequence;
    private JdbcTemplate jdbcTemplate;

    @Resource
    public void setDataSource(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
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
        LOGGER.info(products.toString());
        if (products.size() > 1) {
            throw new IllegalStateException("Fant flere enn 1 produkt med primary-key:" + id + " " + products);
        }
        if (products.isEmpty()) {
            return null;
        }
        return products.get(0);
    }

    public void deleteProduct(int id) {
        jdbcTemplate.update(DELETE_PRODUCT, new String[]{Integer.toString(id)});
    }

    public int updateProduct(Product product) {
        if (product.getId() != null) {
            jdbcTemplate.update(
                    UPDATE_PRODUCT,
                    product.getName(), product.getDescription(),
                    product.getPrice(), product.getId()
            );
            return product.getId();
        } else {
            int id = productSequence.nextIntValue();
            jdbcTemplate.update(
                    INSERT_PRODUCT,
                    id, product.getName(),
                    product.getDescription(), product.getPrice()
            );
            return id;
        }
    }
}
