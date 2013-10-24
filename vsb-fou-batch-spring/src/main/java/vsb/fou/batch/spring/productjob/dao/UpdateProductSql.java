package vsb.fou.batch.spring.productjob.dao;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Types;

/**
 * @author Vegard S. Bye
 */
public class UpdateProductSql extends SqlUpdate {

    public UpdateProductSql(DataSource dataSource) {
        setDataSource(dataSource);
        setSql("UPDATE VSB.PRODUCT set NAME=?, DESCRIPTION=?, PRICE=? WHERE ID=?");
        declareParameter(new SqlParameter("ID", Types.INTEGER));
        declareParameter(new SqlParameter("NAME", Types.VARCHAR));
        declareParameter(new SqlParameter("DESCRIPTION", Types.VARCHAR));
        declareParameter(new SqlParameter("PRICE", Types.FLOAT));
        compile();
    }

    public int updateProduct(int id, String name, String description, BigDecimal price) {
        return update(id, name, description, price);
    }
}
