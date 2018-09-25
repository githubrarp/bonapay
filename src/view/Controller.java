package view;

import crudOper.ClienteCrud;
import crudOper.PagoCrud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Cliente;
import model.DBConnection;

import javax.sound.midi.Soundbank;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private ClienteCrud clienteCrud;
//    private PagoCrud pagoCrud;

    @FXML
    TextField txtAddName;
    @FXML
    TextField txtAddLastName;
    @FXML
    TextField txtAddHomeNumber;
    @FXML
    TextField txtSearchName;
    @FXML
    TextField txtSearchLastName;
    @FXML
    TextField txtSearchHomeNumber;
    @FXML
    TextField txtSearchPhoneNumber;
    @FXML
    TableView<Cliente> tblResultListing;
    ObservableList<Cliente> tblResultListingCliente;
    @FXML
    TableColumn<Cliente, String> columnFirstName;
    @FXML
    TableColumn<Cliente, String> columnLastName;
    @FXML
    TableColumn<Cliente, String> columnHomeNumber;
    @FXML
    TableColumn<Cliente, String> columnPhoneNumber;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblResultListingCliente = FXCollections.observableArrayList();
        Cliente cc = new Cliente("Jon", "Snow", "Wintefell");
        tblResultListingCliente.add(cc);
        //System.out.println(tblResultListingCliente);
        tblResultListing.setItems(tblResultListingCliente);
        System.out.println("It works !");

        this.clienteCrud = new ClienteCrud();
//        this.pagoCrud = new PagoCrud();

    }

    @FXML
    public void insertClienteObject(ActionEvent actionEvent) {

        String firstName = txtAddName.getText();
        String lastName = txtAddLastName.getText();
        String homeNumber = txtAddHomeNumber.getText();

        Cliente cliente = new Cliente(firstName, lastName, homeNumber);

        this.clienteCrud.insertarCliente(cliente);
        System.out.println("Something" );
    }

    @FXML
    public void searchMethod(ActionEvent actionEvent) {
        String firstName = txtSearchName.getText();
        String lastName = txtSearchLastName.getText();
        String homeNumber = txtSearchHomeNumber.getText();
        String phoneNumber = txtSearchPhoneNumber.getText();

        Cliente cliente = new Cliente(firstName, lastName, homeNumber);
        if (phoneNumber.length() > 0){
            cliente.setPhoneNumber(phoneNumber);
        }

//        this.clienteCrud.searchCliente(cliente);
        System.out.println("DONT FORGET TO DELETE THIS LINE: but this is your Controller and I'm sending seraching criteria to ClienteCrud");



        List<Cliente> resultListing = this.clienteCrud.searchCliente(cliente);

        for (Cliente clientesInList: resultListing) {
            tblResultListingCliente.add(clientesInList);
        }

    }
}
