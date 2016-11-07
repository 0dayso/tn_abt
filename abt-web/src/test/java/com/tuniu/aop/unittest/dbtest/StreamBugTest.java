package com.tuniu.aop.unittest.dbtest;

import com.tuniu.aop.unittest.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;

public class StreamBugTest extends BaseTest {
    @Resource
    private DataSource readWriteDbRoute;

    @Test
    public void main() throws SQLException, ClassNotFoundException {
        while (true) {
            s();
        }
    }


    private void s() throws SQLException {
        Connection con = null;
        try {
            con = readWriteDbRoute.getConnection();
            for (int i = 0; i < 10; i++) {
                System.out.println("round " + (i + 1));
                // con.createStatement().executeQuery("SELECT 1"); // test connection is available

                PreparedStatement statement = con.prepareStatement("SELECT id FROM train_ticket_new",
                        java.sql.ResultSet.TYPE_FORWARD_ONLY,
                        java.sql.ResultSet.CONCUR_READ_ONLY);
                statement.setFetchSize(Integer.MIN_VALUE);

                //                new Thread() {
                //                    public void run() {
                //                        try {
                //                            Thread.sleep(10);
                //                            pstmt.cancel();
                //                        } catch (Exception e) {
                //                        }
                //                    }
                //                }.start();
                ResultSet rs = null;
                try {
                    rs = statement.executeQuery();
                    rs.next();
                    statement.cancel();
                } catch (SQLException e) {
                    System.out.println("SQLException caught");
                } finally {

                    try {
                        rs.getStatement().close();
                    } catch (Exception ex) {

                    }


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null)
                con.close();
        }
    }
}