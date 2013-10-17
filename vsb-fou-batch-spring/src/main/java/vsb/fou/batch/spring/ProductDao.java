package vsb.fou.batch.spring;

import org.springframework.jdbc.core.JdbcTemplate;
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
    private JdbcTemplate jdbcTemplate;

    @Resource
    public void setDataSource(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    public void updateProduct(Product product) {

    }
}
