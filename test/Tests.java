import com.codecool.shop.dao.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by mz on 2016.12.02..
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
        ProductCategoryDaoJdbcTest.class,
        ProductCategoryDaoTest.class,
        SupplierDaoJdbcTest.class,
        SupplierDaoTest.class,
        ProductDaoTest.class,
        ProductDaoJdbcTest.class
})

public class Tests {
    // rebuild the DataBase before running the tests!
}
