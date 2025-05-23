//package servcie;
//
//import com.d4c.ShardingJdbcSimpleBootstrap;
//import com.d4c.service.OrderService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {ShardingJdbcSimpleBootstrap.class})
//public class OrderServiceTest {
//
//    @Autowired
//    OrderService orderService;
//
//
//    @Test
//    public void testInsertOrder() {
//        for (int i = 1; i < 20; i++) {
//            orderService.insertOrder(new BigDecimal(i), 1L, "SUCCESS");
//        }
//    }
//
//
//    @Test
//    public void testSelectOrderbyIds() {
//        List<Long> ids = new ArrayList<>();
//        ids.add(373897739357913088L);
//        ids.add(373897037306920961L);
//
//        List<Map> maps = orderService.selectOrderbyIds(ids);
//        System.out.println(maps);
//    }
//
//    @Test
//    public void testInsertOrderDB(){
//        for (int i = 0 ; i<10; i++){
//            orderService.insertOrder(new BigDecimal((i+1)*5),1L,"WAIT_PAY");
//        }
//        for (int i = 0 ; i<10; i++){
//            orderService.insertOrder(new BigDecimal((i+1)*10),2L,"WAIT_PAY");
//        }
//    }
//
//    @Test
//    public void testBewteen(){
//        List<Map> maps = orderService.selectAllOrder(1,2);
//        maps.stream().forEach(System.out::println);
//    }
//
//
//    @Test
//    public void testSelectByUserId(){
//        List<Map> map = orderService.selectOrderByUserId(1L);
//        System.out.println(map);
//    }
//
//    @Test
//    public void testSelectById(){
//        Map map = orderService.selectOrderById(1067401951097913344L);
//        System.out.println(map);
//    }
//}