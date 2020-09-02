/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no2_kuis2_pbo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author Sarah Christine (11318058)
 */
public class App extends Application{
public static List<TMahasiswa> lstMahasiswa;
public static List<TMatakuliah> lstMatakuliah;
public static List<TMkMhs> lstKrs;
private ObservableList<ObservableList> data;
private TableView tableview;

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane grdView = new GridPane();
        ColumnConstraints cons1 = new ColumnConstraints();
        ColumnConstraints cons2 = new ColumnConstraints();
        cons1.setPercentWidth(30);
        cons2.setPercentWidth(70);
        
         //TableView
            tableview = new TableView();
            
        
         grdView.setPadding(new Insets(8));
 
        grdView.setHgap(8); grdView.setVgap(8);
    
        Label lblNim = new Label("NIM");
        TextField txtNim = new TextField();
        Label lblNama = new Label("Nama");
        Label lblInfoNama = new Label("Tidak ditemukan");
        lblInfoNama.setTextFill(Color.RED);
        Label lblKuliah = new Label("Rencananya disini tabel");
        
        txtNim.textProperty().addListener((ObservableValue<? extends String>observable, 
                 String oldValue, String newValue)->{
        Object objMaha = GeneralUtils.findMahasiswa(newValue, lstMahasiswa);
        if(objMaha == null){
            lblInfoNama.setTextFill(Color.RED);
             lblInfoNama.setText("Tidak ditemukan");
        }
        else{
             lblInfoNama.setTextFill(Color.BLUE);
             lblInfoNama.setText(((TMahasiswa)objMaha).getNama());
        }
    });
        
         txtNim.textProperty().addListener((ObservableValue<? extends String>observable, 
                 String oldValue, String newValue)->{
        Object objMaha = GeneralUtils.findKRS(newValue, lstKrs);
        if(objMaha == null){
            lblKuliah.setTextFill(Color.RED);
             lblKuliah.setText("Tabelnya gabisaaaa TAT");
        }
        else{
            String Nim = txtNim.getText().toString().trim();
            buildData(Nim);
        }
    });
        
        Label lblIPS = new Label("IPS");
        TextField txtIPS = new TextField("Not yet");
        
         grdView.getColumnConstraints().addAll(cons1, cons2);
         grdView.add(lblNim, 0, 0); grdView.add(txtNim, 1, 0);
         grdView.add(lblNama, 0, 1); grdView.add(lblInfoNama, 1, 1);
         //grdView.add(lblKuliah, 1,2);
         grdView.add(tableview, 1,2);
         grdView.add(lblIPS, 0, 3); grdView.add(txtIPS, 1, 3);
        
        
            
          Scene scene = new Scene(grdView, 500, 400);
         
         primaryStage.setTitle("Aplikasi IPS Mahasiswa");
         primaryStage.setScene(scene);
         primaryStage.show();
    }
    
     public static void main(String[] args) throws SQLException, ClassNotFoundException{
        loadAllData();
        launch(args);
    }
    
    public static void loadAllData() throws SQLException, ClassNotFoundException{
        lstMahasiswa = DBUtils.getAllMahasiswa();
        lstMatakuliah = DBUtils.getAllMataKuliah();
        lstKrs = DBUtils.getAllKrs();
    }
    
     //CONNECTION DATABASE
    public void buildData(String Nim) {
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = DBUtils.getConnection();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * FROM TMKMHS WHERE NIM =?";
            PreparedStatement pst = DBUtils.getConnection().prepareStatement(SQL);
                                            pst.setString(1, Nim);
            //ResultSet
            ResultSet rs = pst.executeQuery();

            /**
             * ********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             *********************************
             */
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            /**
             * ******************************
             * Data added to ObservableList *
             *******************************
             */
          while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
    
}
