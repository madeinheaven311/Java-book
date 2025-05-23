package jdbc;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectTest {

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://192.168.216.4:3306/jdbc?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true";
    private String username = "root";
    private String password = "root";


    @Test
    public void testConnect() throws ClassNotFoundException, SQLException {
        //1. 驱动数据库
        Class.forName(driver);

        //2. 获取连接
        Connection connection = DriverManager.getConnection(url,username,password);

        //3. 测试连接
        System.out.println(connection.isClosed() ? "连接失败" : "连接成功");

        //4. 创建媒介
        String sql = "select u.user_id, u.real_name from jdbc.user u";

        //5. 准备SQL
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //6. 发送SQL
        ResultSet resultSet = preparedStatement.executeQuery();
            //metaData是该列的元数据，即每一列的列名的数据
        ResultSetMetaData metaData = resultSet.getMetaData();

        //7. 解析结果
        List<Map<String,Object>> resultMap = new ArrayList<>();
        while (resultSet.next()){
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String columnName=metaData.getColumnName(i);
                Object value=resultSet.getObject(columnName);
                System.out.println(columnName+": "+value);

                Map<String, Object> map = new HashMap<>();
                map.put(columnName,value);
                resultMap.add(map);
            }
        }
        System.out.println("列表形式返回\n"+resultMap);




        //8. 关闭连接
        connection.close();


    }

}
