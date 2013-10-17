package vsb.fou.batch.spring;

import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * @author Vegard S. Bye
 */
@Component
public class ProductWriter implements ItemWriter<Product> {

    private static final String INSERT_PRODUCT = "insert into VSB.PRODUCT " + "(ID,NAME,DESCRIPTION,PRICE) values(?,?,?,?)";
    private static final String UPDATE_PRODUCT = "update VSB.PRODUCT set " + "name=?, description=?, price=? where id=?";
    private JdbcTemplate jdbcTemplate;

    @Resource
    public void setDataSource(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    public void write(List<? extends Product> items) throws Exception {
        for (Product item : items) {
            int updated = jdbcTemplate.update(
                    UPDATE_PRODUCT,
                    item.getName(), item.getDescription(),
                    item.getPrice(), item.getId()
            );
            if (updated == 0) {
                jdbcTemplate.update(
                        INSERT_PRODUCT,
                        item.getId(), item.getName(),
                        item.getDescription(), item.getPrice()
                );
            }
        }
    }
}