package vsb.fou.batch.spring.job.dao;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import vsb.fou.batch.spring.job.domain.Product;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * @author Vegard S. Bye
 */
public class SelectProductSql extends MappingSqlQuery<Product> {

    public SelectProductSql(DataSource dataSource) {
        setDataSource(dataSource);
        setSql("SELECT * FROM VSB.PRODUCT WHERE ID=?");
        declareParameter(new SqlParameter("ID", Types.INTEGER));
        compile();
    }

    @Override
    protected Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("ID"));
        product.setName(rs.getString("NAME"));
        product.setDescription(rs.getString("DESCRIPTION"));
        product.setPrice(rs.getBigDecimal("PRICE"));
        return product;
    }
}
