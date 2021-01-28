package by.praded.ask_and_go.dao.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.DatabaseMetaData;
import java.sql.SQLWarning;
import java.sql.Savepoint;
import java.sql.Clob;
import java.sql.Blob;
import java.sql.NClob;
import java.sql.SQLClientInfoException;
import java.sql.Array;
import java.sql.Struct;
import java.sql.SQLXML;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;


/**
 * @author Kiryl Praded
 * 04.12.2020
 * <p>
 * Class of connection for pool.
 */
public class WrappedConnection implements Connection {
    /**
     * Connection to database.
     */
    private final Connection connection;

    /**
     * Class constructor.
     *
     * @param connection - connection for the field {@link WrappedConnection#connection}.
     * @throws SQLException - exception of the {@link Connection}.
     */
    public WrappedConnection(Connection connection) throws SQLException {
        this.connection = connection;
        this.connection.setAutoCommit(true);
    }

    /**
     * Method to completely close connection.
     *
     * @throws SQLException - exception of the {@link Connection}.
     */
    void completelyClose() throws SQLException {
        connection.close();
    }

    /**
     * Method to remove connection from taken to free.
     */
    @Override
    public void close() {
        ConnectionPool.getInstance().freeConnection(this);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public PreparedStatement prepareStatement(String s) throws SQLException {
        return connection.prepareStatement(s);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public CallableStatement prepareCall(String s) throws SQLException {
        return connection.prepareCall(s);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String nativeSQL(String s) throws SQLException {
        return connection.nativeSQL(s);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getAutoCommit() throws SQLException {
        return connection.getAutoCommit();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setAutoCommit(boolean b) throws SQLException {
        connection.setAutoCommit(b);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void commit() throws SQLException {
        connection.commit();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void rollback() throws SQLException {
        connection.rollback();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isClosed() throws SQLException {
        return connection.isClosed();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return connection.getMetaData();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isReadOnly() throws SQLException {
        return connection.isReadOnly();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setReadOnly(boolean b) throws SQLException {
        connection.setReadOnly(b);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getCatalog() throws SQLException {
        return connection.getCatalog();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setCatalog(String s) throws SQLException {
        connection.setCatalog(s);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getTransactionIsolation() throws SQLException {
        return connection.getTransactionIsolation();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setTransactionIsolation(int i) throws SQLException {
        connection.setTransactionIsolation(i);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public SQLWarning getWarnings() throws SQLException {
        return connection.getWarnings();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void clearWarnings() throws SQLException {
        connection.clearWarnings();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Statement createStatement(int i, int i1) throws SQLException {
        return connection.createStatement(i, i1);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public PreparedStatement prepareStatement(String s, int i, int i1) throws SQLException {
        return connection.prepareStatement(s, i, i1);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public CallableStatement prepareCall(String s, int i, int i1) throws SQLException {
        return connection.prepareCall(s, i, i1);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return connection.getTypeMap();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        connection.setTypeMap(map);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getHoldability() throws SQLException {
        return connection.getHoldability();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setHoldability(int i) throws SQLException {
        connection.setHoldability(i);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Savepoint setSavepoint() throws SQLException {
        return connection.setSavepoint();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Savepoint setSavepoint(String s) throws SQLException {
        return connection.setSavepoint(s);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        connection.rollback(savepoint);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        connection.releaseSavepoint(savepoint);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Statement createStatement(int i, int i1, int i2) throws SQLException {
        return connection.createStatement(i, i1, i2);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public PreparedStatement prepareStatement(String s, int i, int i1, int i2) throws SQLException {
        return connection.prepareCall(s, i, i1, i2);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public CallableStatement prepareCall(String s, int i, int i1, int i2) throws SQLException {
        return connection.prepareCall(s, i, i1, i2);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public PreparedStatement prepareStatement(String s, int i) throws SQLException {
        return connection.prepareStatement(s, i);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public PreparedStatement prepareStatement(String s, int[] ints) throws SQLException {
        return connection.prepareStatement(s, ints);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public PreparedStatement prepareStatement(String s, String[] strings) throws SQLException {
        return connection.prepareStatement(s, strings);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Clob createClob() throws SQLException {
        return connection.createClob();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Blob createBlob() throws SQLException {
        return connection.createBlob();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public NClob createNClob() throws SQLException {
        return connection.createNClob();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public SQLXML createSQLXML() throws SQLException {
        return connection.createSQLXML();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(int i) throws SQLException {
        return connection.isValid(i);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setClientInfo(String s, String s1) throws SQLClientInfoException {
        connection.setClientInfo(s, s1);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getClientInfo(String s) throws SQLException {
        return connection.getClientInfo(s);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Properties getClientInfo() throws SQLException {
        return connection.getClientInfo();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        connection.setClientInfo(properties);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Array createArrayOf(String s, Object[] objects) throws SQLException {
        return connection.createArrayOf(s, objects);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Struct createStruct(String s, Object[] objects) throws SQLException {
        return connection.createStruct(s, objects);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getSchema() throws SQLException {
        return connection.getSchema();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setSchema(String s) throws SQLException {
        connection.setSchema(s);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void abort(Executor executor) throws SQLException {
        connection.abort(executor);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setNetworkTimeout(Executor executor, int i) throws SQLException {
        connection.setNetworkTimeout(executor, i);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getNetworkTimeout() throws SQLException {
        return connection.getNetworkTimeout();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T unwrap(Class<T> aClass) throws SQLException {
        return connection.unwrap(aClass);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        return connection.isWrapperFor(aClass);
    }
}
