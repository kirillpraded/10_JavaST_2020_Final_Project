package by.praded.ask_and_go.dao.sql;

import java.sql.Connection;

/**
 * @author Kiryl Praded
 * 14.12.2020
 * Base DAO class.
 * Should be inherited by each DAO implementation.
 */
abstract public class BaseDaoImpl {
    /**
     * Connection to connect with database.
     */
    protected Connection connection;

    /**
     * Method to set value to the field {@link BaseDaoImpl#connection}.
     *
     * @param connection - connection to set.
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
