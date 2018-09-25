package crudOper;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import model.Cliente;
import model.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteCrud {

    Connection connection;
    Statement statement;

    public ClienteCrud(){
        this.connection = null;
        this.statement = null;
    }


    public void insertarCliente(Cliente cliente){

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
 //           return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
 //       return false;
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

    public List<Cliente> searchCliente(Cliente cliente){

        DBConnection dbConnection = new DBConnection();

        this.connection = dbConnection.getDriverConnection(this.connection);
        this.statement = dbConnection.createStatement(this.statement, this.connection);

        String firstName = cliente.getFirstName();
        String lastName = cliente.getLastName();
        String homeNumber = cliente.getHomeNumber();
        String phoneNumber = cliente.getPhoneNumber();

        String matchCase;
        List<Cliente> resultOfSearching = new ArrayList<>();

        ResultSet resultSet;
        int idCliente = getClientId(homeNumber);

        String searchQueryAll = "SELECT * FROM Cliente;";
//        String searchQueryById = "SELECT * FROM Cliente WHERE idCliente = " + idCliente + ";";
        String searchQueryByFirstName = "SELECT * FROM Cliente WHERE firstName= '" + firstName + "';";
        String searchQueryByLastName = "SELECT * FROM Cliente WHERE lastName= '" + lastName + "';";
        String searchQueryByHomeNumber = "SELECT * FROM Cliente WHERE homeNumber= '" + homeNumber + "';";
        String searchQueryByPhoneNumber = "SELECT * FROM Cliente WHERE phoneNumber= '" + phoneNumber + "';";

        if (firstName.length() > 0) {
            matchCase = searchQueryByFirstName;
        }else if (lastName.length() > 0){
            matchCase = searchQueryByLastName;
        }else if (homeNumber.length() > 0){
            matchCase = searchQueryByHomeNumber;
        }else if(phoneNumber.length() > 0){
            matchCase = searchQueryByPhoneNumber;
        }else{
            matchCase = searchQueryAll;
        }

        System.out.println("MATCHCASE IS: " + matchCase);


        try{
            resultSet = this.statement.executeQuery(matchCase);

            while (resultSet.next()) {
                String whileFistName = resultSet.getString("firstName");
                String whileLastName = resultSet.getString("lastName");
                String whileHomeNumber = resultSet.getString("homeNumber");
                String whilePhoneNumber = resultSet.getString("phoneNumber");

                Cliente whileCliente = new Cliente(whileFistName, whileLastName, whileHomeNumber);
                if (whilePhoneNumber != null || whilePhoneNumber.length() > 0){
                    whileCliente.setPhoneNumber(whilePhoneNumber);
                }
                resultOfSearching.add(whileCliente);
            }
            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return resultOfSearching;
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