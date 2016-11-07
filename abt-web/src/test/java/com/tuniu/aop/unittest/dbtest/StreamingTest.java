package com.tuniu.aop.unittest.dbtest;

import com.tuniu.aop.unittest.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by chengyao on 2016/4/8.
 */
public class StreamingTest extends BaseTest {

    @Resource
    private DataSource readWriteDbRoute;

    @Test
    public void s0() throws SQLException {
        Connection connection = readWriteDbRoute.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement(
                    java.sql.ResultSet.TYPE_FORWARD_ONLY,
                    java.sql.ResultSet.CONCUR_READ_ONLY);
            statement.setFetchSize(Integer.MIN_VALUE);

            resultSet = statement.executeQuery("select * from abt_tracer_log");
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                System.out.println(id);
            }
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (Exception e) {};

            if (statement != null) try { statement.close(); } catch (Exception e) {};

            if (connection != null) try { connection.close(); } catch (Exception e) {};

        }
    }


}
