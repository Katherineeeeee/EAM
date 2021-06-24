import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import javax.sql.DataSource;
import java.sql.SQLException;

public class testDB {

    private ApplicationContext ctx = null;

    {
        ctx = new ClassPathXmlApplicationContext("spring-dao.xml");
    }

    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = ctx.getBean(DataSource.class);
//        System.out.println(dataSource.getConnection());
    }
}
