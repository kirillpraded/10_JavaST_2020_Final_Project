package by.praded.ask_and_go.dao.impl;

import java.sql.Connection;

/**
 * @author Kiryl Praded
 * 14.12.2020
 * Base DAO class.
 * Should be inherited by each DAO implementation.
 */
abstract public class BaseDaoImpl {
    protected Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
