package crudOper;

import model.Cliente;
import model.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClienteCrud {

    Connection connection = null;
    Statement statement = null;

    public ClienteCrud(){
    }

    public boolean insertarCliente(Cliente cliente){

        DBConnection dbConnection = new DBConnection();

        this.connection = dbConnection.getDriverConnection(this.connection);
        this.statement = dbConnection.createStatement(this.statement, this.connection);

        String firstName = cliente.getFirstName();
        String lastName = cliente.getLastName();
        String homeNumber = cliente.getHomeNumber();
        String phoneNumber = cliente.getPhoneNumber();

        String insertQuery = "INSERT INTO Cliente (firstName, lastName, homeNumber, phoneNumber)" +
                "VALUES('" + firstName + "','" + lastName + "','" + homeNumber + "','" + phoneNumber + "');";

        try {
            this.statement.executeUpdate(insertQuery);
            statement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCliente(Cliente cliente){

        DBConnection dbConnection = new DBConnection();

        this.connection = dbConnection.getDriverConnection(this.connection);
        this.statement = dbConnection.createStatement(this.statement, this.connection);

        String firstName = cliente.getFirstName();
        String lastName = cliente.getLastName();
        String homeNumber = cliente.getHomeNumber();
        String phoneNumber = cliente.getPhoneNumber();
        int idCliente = getClientId(homeNumber);

        String sqlUpdateClienteFirstName = "UPDATE Cliente SET firstName = '"+ firstName +" ' WHERE idCliente = " + idCliente + " ;";
        String sqlUpdateClienteLastName = "UPDATE Cliente SET lastName = '"+ lastName +" ' WHERE idCliente = " + idCliente + " ;";
        String sqlUpdateClienteHomeNumber = "UPDATE Cliente SET homeNumber = '"+ homeNumber +" ' WHERE idCliente = " + idCliente + " ;";
        String sqlUpdateClientePhoneNumber = "UPDATE Cliente SET phoneNumber = '"+ phoneNumber +" ' WHERE idCliente = " + idCliente + " ;";

        try {
            statement.executeUpdate(sqlUpdateClienteFirstName);
            statement.executeUpdate(sqlUpdateClienteLastName);
            statement.executeUpdate(sqlUpdateClienteHomeNumber);
            statement.executeUpdate(sqlUpdateClientePhoneNumber);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return true;
    }

    public boolean removeCliente(Cliente cliente){

        DBConnection dbConnection = new DBConnection();

        this.connection = dbConnection.getDriverConnection(this.connection);
        this.statement = dbConnection.createStatement(this.statement, this.connection);

        String homeNumber = cliente.getHomeNumber();

        int idCliente = getClientId(homeNumber);

        String removeQuery = "DELETE FROM Cliente WHERE idCliente = " + idCliente + ";";

        try {
            statement.executeUpdate(removeQuery);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean searchCliente(Cliente cliente){

        DBConnection dbConnection = new DBConnection();

        this.connection = dbConnection.getDriverConnection(this.connection);
        this.statement = dbConnection.createStatement(this.statement, this.connection);

        String homeNumber = cliente.getHomeNumber();

        ResultSet resultSet;
        int idCliente = getClientId(homeNumber);

        String searchQuery = "SELECT * FROM Cliente WHERE idCliente = " + idCliente + ";";

        try{
            resultSet = this.statement.executeQuery(searchQuery);

            while (resultSet.next()){
                System.out.println(
                        resultSet.getInt("idCliente") + " " +
                                resultSet.getString("firstName") + " " +
                                resultSet.getString("homeNumber") + " " +
                                resultSet.getString("phoneNumber"));
            }
            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }

    public int getClientId(String homeNumber){

        int idCliente = 0;
        ResultSet resultSet;

        String sqlGetClienteId = "SELECT idCliente FROM Cliente WHERE homeNumber = '"+ homeNumber +"';";

        try {
            resultSet = this.statement.executeQuery(sqlGetClienteId);

            while (resultSet.next()){
                idCliente = resultSet.getInt("idCliente");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idCliente;
    }

    public void updateHomeNumber(String oldHomeNumber, String newHomeNumber){

        DBConnection dbConnection = new DBConnection();

        this.connection = dbConnection.getDriverConnection(this.connection);
        this.statement = dbConnection.createStatement(this.statement, this.connection);

        int idCliente = getClientId(oldHomeNumber);

        String queryChangeHomeNunmber = "UPDATE Cliente SET homeNumber = '"+ newHomeNumber +"' WHERE idCliente = " + idCliente + ";";

        /**
         * Trying to execute our query.
         * Documentation is pretty much important.
         */
        try{
//            statement.executeQuery(queryChangeHomeNunmber);
//          executeQuery uses SELECT and return a ResultSet
            statement.executeUpdate(queryChangeHomeNunmber);
            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}