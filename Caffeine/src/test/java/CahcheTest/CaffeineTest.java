package CahcheTest;


import com.d4c.www.SingleDatabaseApplication;
import com.d4c.www.pojo.dto.UserInfo;
import com.d4c.www.service.CaffeineManagerService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SingleDatabaseApplication.class)
public class CaffeineTest {

    private static final String KEY = "key";

    private CaffeineManagerService caffeineManagerService = new CaffeineManagerService();

    private UserInfo user = new UserInfo("key", "value");

    @Test
    public void test() throws InterruptedException {
        caffeineManagerService.addUserInfo(user);

        Thread.sleep(10000);
        System.out.println(caffeineManagerService.getByName(user.getId()));;
    }


}
