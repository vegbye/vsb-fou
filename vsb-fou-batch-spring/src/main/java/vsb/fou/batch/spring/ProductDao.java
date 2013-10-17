package vsb.fou.batch.spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.incrementer.AbstractSequenceMaxValueIncrementer;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author Vegard S. Bye
 */
@Repository
public class ProductDao {

    private static final String INSERT_PRODUCT = "insert into VSB.PRODUCT " + "(ID,NAME,DESCRIPTION,PRICE) values(?,?,?,?)";
    private static final String UPDATE_PRODUCT = "update VSB.PRODUCT set " + "name=?, description=?, price=? where id=?";
    @Resource
    public AbstractSequenceMaxValueIncrementer productSequence;
    private JdbcTemplate jdbcTemplate;

    @Resource
    public void setDataSource(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    public void updateProduct(Product product) {
        if (product.getId() != null) {
            jdbcTemplate.update(
                    UPDATE_PRODUCT,
                    product.getName(), product.getDescription(),
                    product.getPrice(), product.getId()
            );
        } else {
            int id = productSequence.nextIntValue();
            jdbcTemplate.update(
                    INSERT_PRODUCT,
                    id, product.getName(),
                    product.getDescription(), product.getPrice()
            );
        }
    }
}
