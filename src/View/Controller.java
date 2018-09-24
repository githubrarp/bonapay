package view;

import crudOper.ClienteCrud;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Cliente;
import model.DBConnection;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private ClienteCrud clienteCrud;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("It works !");

        this.clienteCrud = new ClienteCrud();

    }

/*
    @FXML
    Button btnAddCliente;
    */
    @FXML
    TextField txtAddName;
    @FXML
    TextField txtAddLastName;
    @FXML
    TextField txtAddHomeNumber;



    @FXML
    public void insertClienteObject(ActionEvent actionEvent) {
        String firstName = txtAddName.getText();
        String lastName = txtAddLastName.getText();
        String homeNumber = txtAddHomeNumber.getText();

        Cliente cliente = new Cliente(firstName, lastName, homeNumber);

        this.clienteCrud.insertarCliente(cliente);
        System.out.println("Something" );
    }
}
