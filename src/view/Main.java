package view;

import crudOper.ClienteCrud;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.*;
import model.Cliente;


public class Main extends Application {//implements EventHandler<ActionEvent>  {

    Button  btnAddClient = new Button();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle("Sistema de Gestion de Cobros");
        primaryStage.setScene(new Scene(root, 800, 400));
        primaryStage.show();


        Cliente cliente = new Cliente("Rebeca", "Rodriguez", "9D");
        ClienteCrud clienteCrud = new ClienteCrud();

        btnAddClient.setOnAction(e -> System.out.println("Ofi"));
    }
/*
    @Override
    public void handle(ActionEvent event){
        if (event.getSource()== btnAddClient){
            System.out.println("Nice clicking");
        }
    }
*/
    public static void main(String[] args) {
        launch(args);
    }
}
