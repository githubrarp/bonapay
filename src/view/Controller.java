package view;

import crudOper.ClienteCrud;
import javafx.beans.property.SimpleStringProperty;
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

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private ClienteCrud clienteCrud;

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
    ObservableList<Cliente> tblObservableResultListingCliente;
    @FXML
    TableColumn<Cliente, String> columnFirstName;
    @FXML
    TableColumn<Cliente, String> columnLastName;
    @FXML
    TableColumn<Cliente, String> columnHomeNumber;
    @FXML
    TableColumn<Cliente, String> columnPhoneNumber;
    @FXML
    Button btnEliminarCliente;
    @FXML
    TextField txtUpdateFirstName;
    @FXML
    TextField txtUpdateLastName;
    @FXML
    TextField txtUpdateHomeNumber;
    @FXML
    TextField txtUpdatePhoneNumber;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblObservableResultListingCliente = FXCollections.observableArrayList();
        tblResultListing.setItems(tblObservableResultListingCliente);

        columnFirstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        columnLastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        columnHomeNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHomeNumber()));
        columnPhoneNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));


        System.out.println("It works !");

        this.clienteCrud = new ClienteCrud();
    }

    @FXML
    public void insertClienteObject(ActionEvent actionEvent) {

        String firstName = txtAddName.getText();
        String lastName = txtAddLastName.getText();
        String homeNumber = txtAddHomeNumber.getText();

        Cliente cliente = new Cliente(firstName, lastName, homeNumber);

        this.clienteCrud.insertarCliente(cliente);
    }

    @FXML
    public void searchMethod(ActionEvent actionEvent) {
        String firstName = txtSearchName.getText();
        String lastName = txtSearchLastName.getText();
        String homeNumber = txtSearchHomeNumber.getText();
        String phoneNumber = txtSearchPhoneNumber.getText();

        Cliente cliente = new Cliente(firstName, lastName, homeNumber);
        if (phoneNumber != null || phoneNumber.length() > 0){
            cliente.setPhoneNumber(phoneNumber);
        }

        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(homeNumber);
        System.out.println(phoneNumber);
        List<Cliente> resultListing = this.clienteCrud.searchCliente(cliente);
        tblObservableResultListingCliente.removeAll(tblObservableResultListingCliente);

        for (Cliente clientesInList: resultListing) {
            tblObservableResultListingCliente.add(clientesInList);
        }
    }

    @FXML
    private void eliminarCliente(ActionEvent actionEvent){
        String firstName = tblResultListing.getSelectionModel().getSelectedItem().getFirstName();
        String lastName = tblResultListing.getSelectionModel().getSelectedItem().getLastName();
        String homeNumber = tblResultListing.getSelectionModel().getSelectedItem().getHomeNumber();
        this.clienteCrud.removeCliente(homeNumber);

        Cliente cliente = new Cliente(firstName, lastName, homeNumber);
        ObservableList<Cliente> temporaryList = FXCollections.observableArrayList();
        temporaryList.add(cliente);
        tblObservableResultListingCliente.removeAll(temporaryList);

        System.out.println(tblObservableResultListingCliente);

    }


    @FXML
    private void editarCliente(ActionEvent actionEvent){
        String firstName = tblResultListing.getSelectionModel().getSelectedItem().getFirstName();
        String lastName = tblResultListing.getSelectionModel().getSelectedItem().getLastName();
        String homeNumber = tblResultListing.getSelectionModel().getSelectedItem().getHomeNumber();
        String phoneNumber = tblResultListing.getSelectionModel().getSelectedItem().getPhoneNumber();
        txtUpdateFirstName.setText(firstName);
        txtUpdateLastName.setText(lastName);
        txtUpdateHomeNumber.setText(homeNumber);
        txtUpdatePhoneNumber.setText(phoneNumber);
    }

    @FXML
    private void actualizarCliente(ActionEvent actionEvent){
        String firstName = txtUpdateFirstName.getText();
        String lastName = txtUpdateLastName.getText();
        String homeNumber = txtUpdateHomeNumber.getText();
        String phoneNumber = txtUpdatePhoneNumber.getText();

        Cliente cliente = new Cliente(firstName, lastName, homeNumber, phoneNumber);
        this.clienteCrud.updateCliente(cliente);


        tblObservableResultListingCliente = FXCollections.observableArrayList();
        tblResultListing.setItems(tblObservableResultListingCliente);
        Cliente demoCliente = new Cliente("","","","");
        List<Cliente> fecthClienteTable = this.clienteCrud.searchCliente(demoCliente);

        System.out.println("The printed client: " + cliente.toString());

        for (Cliente clienteItem: fecthClienteTable) {
            tblObservableResultListingCliente.add(clienteItem);
        }

    }
}