/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persiapanuas;

import java.awt.TextField;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.application.Application;
//import javafx.scene.Scene;
import javafx.scene.Scene;
import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class App extends Application {
    public static ArrayList<Pengguna> listPengguna = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridpane = new GridPane(); 
        Label lblNama = new Label();
        javafx.scene.control.TextField tfNim = new javafx.scene.control.TextField();
        gridpane.add(tfNim, 0 , 0);
        gridpane.add(lblNama, 0, 1);
        loadData();
        String ID = tfNim.getText().toString().trim();
        int id = Integer.parseInt(ID);
//        int id = Integer.valueOf(tfNim.getText());
//        for(int i=0; i<listPengguna.size(); i++){
//            if(id == listPengguna.get(i).getId()){
//                lblNama.setText(listPengguna.get(i).getNama());
//                break;
//            }
//        }
        Scene scene = new Scene(gridpane, 100, 100);
        primaryStage.setTitle("Aplikasi pencari nama pengguna");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        loadData();
        launch(args);
    }
    
    public static void loadData() throws ClassNotFoundException, SQLException{
        listPengguna = Database.getAllPengguna();
        
    }
       
}
