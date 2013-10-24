package vsb.fou.batch.spring.productjob.dao;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * @author Vegard S. Bye
 */
public class DeleteProductSql extends SqlUpdate {

    public DeleteProductSql(DataSource dataSource) {
        setDataSource(dataSource);
        setSql("DELETE FROM VSB.PRODUCT WHERE ID=?");
        declareParameter(new SqlParameter("ID", Types.INTEGER));
        compile();
    }

    public int delete(int id) {
        return update(id);
    }
}
