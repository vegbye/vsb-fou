package vsb.fou.db.hsqldb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloWorldHsqldb {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldHsqldb.class);

    /**
     * Called when ran from command line.
     *
     * @param args ignored
     */
    public static void main(String... args) throws Exception {

        Class.forName("org.hsqldb.jdbcDriver");
        File databaseFile = new File(System.getProperty("java.io.tmpdir"), "HelloWorldHsqldb-" + System.currentTimeMillis());
        Connection conn = DriverManager.getConnection("jdbc:hsqldb:" + databaseFile.getAbsolutePath());
        Statement stat = conn.createStatement();

        // this line would initialize the database
        // from the SQL script file 'init.sql'
        // stat.execute("runscript from 'init.sql'");

        stat.execute("create table test(id int primary key, name varchar(255))");
        stat.execute("insert into test values(1, 'Hello HelloWorldHsqldb')");
        ResultSet rs;
        rs = stat.executeQuery("select * from test");
        while (rs.next()) {
            LOGGER.info(rs.getString("name"));
        }
        stat.close();
        conn.close();
    }

}
