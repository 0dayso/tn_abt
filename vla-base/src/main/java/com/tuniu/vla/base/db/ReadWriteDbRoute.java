package com.tuniu.vla.base.db;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

/**
 * 读写分离数据源路由处理
 * Created by rongzhiming on 2015/11/5.
 */
public class ReadWriteDbRoute extends AbstractDataSource implements InitializingBean {

    private DataSource writeDb;

    private List<DataSource> readDbs;

    private ThreadLocal<Boolean> readOnly = new ThreadLocal<Boolean>();

    private Random rand = new Random();

    public DataSource getWriteDb() {
        return writeDb;
    }

    public void setWriteDb(DataSource writeDb) {
        this.writeDb = writeDb;
    }

    public List<DataSource> getReadDbs() {
        return readDbs;
    }

    public void setReadDbs(List<DataSource> readDbs) {
        this.readDbs = readDbs;
    }

    public Boolean getReadOnly() {
        return readOnly.get();
    }

    public void setReadOnly(Boolean readOnly) {
        this.readOnly.set(readOnly);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return determineTargetDataSource().getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return determineTargetDataSource().getConnection(username, password);
    }

    private DataSource determineTargetDataSource() {
        if (readOnly.get() == null || !readOnly.get()) {
            return writeDb;
        } else {
            int i = rand.nextInt(readDbs.size());
            return readDbs.get(i);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (writeDb == null) {
            throw new IllegalArgumentException("writeDb is null");
        }
        if (CollectionUtils.isEmpty(readDbs)) {
            throw new IllegalArgumentException("readDbs is empty");
        }
    }
}
