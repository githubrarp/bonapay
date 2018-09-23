package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private Connection connection = null;
    private Statement statement = null;

    public void DBCreateTables(){

        this.connection = getDriverConnection(this.connection);
        this.statement = createStatement(this.statement, this.connection);

        String sqlCreateTableCliente = "CREATE TABLE Cliente" +
                "(idCliente integer PRIMARY KEY AUTOINCREMENT, firstName text, lastName text, " +
                "homeNumber text, phoneNumber text, constraint homeNumber_unique UNIQUE(homeNumber));";


        String sqlCreateTablePago = "CREATE TABLE Pago" +
                "(idPago integer PRIMARY KEY AUTOINCREMENT, date text, recibo int, amount text, status text, comment text, " +
                "idClienteFk text, FOREIGN KEY (idClienteFk) REFERENCES Cliente(idCliente));";
        try {
            statement.executeUpdate(sqlCreateTableCliente);
            statement.executeUpdate(sqlCreateTablePago);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Now available -> Table: Cliente");
            System.out.println("Now available -> Table: Pago");
//            e.printStackTrace();
        }

    }

    public Connection getDriverConnection(Connection connection){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:bonapay.db");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Statement createStatement(Statement statement, Connection connection){
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }
}
