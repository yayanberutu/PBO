/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HitungIPS;

import static HitungIPS.DBUtils.getMatkulById;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 *
 * @author Yosepri Berutu
 */
public class JavaFXJDBCApp extends Application{
    public static List<TMahasiswa> listMahasiswa;
    public static List<TMataKuliah> listMataKuliah;
    public static List<TMkMhs> listKrs;

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridpane = new GridPane(); 
        
        Label lblNim = new Label("NIM");
        TextField tfNim = new TextField();
        Label lblIP = new Label("IP");
        Label lblIPS = new Label();
        
        TableView tvkrs = new TableView();
        TableColumn<String, TMataKuliah>  columnKM = new TableColumn<>("Kode Matkul");
        columnKM.setCellFactory(new PropertyValueFactory<>("idMatkul"));
        TableColumn<String, TMataKuliah>  columnNM = new TableColumn<>("Nama Matkul");
        columnNM.setCellFactory(new PropertyValueFactory<>("namaMatkul"));
        TableColumn<String, TMataKuliah>  columnSKS = new TableColumn<>("SKS");
        columnSKS.setCellFactory(new PropertyValueFactory<>("sks"));
        TableColumn<Float, TMkMhs>  columnNilai = new TableColumn<>("Nilai");
        
        TableColumn<String, >  columnGrade = new TableColumn<>("Grade");
        
        tvkrs.getColumns().add(columnKM);
        tvkrs.getColumns().add(columnNM);
        tvkrs.getColumns().add(columnSKS);
        tvkrs.getColumns().add(columnNilai);
        tvkrs.getColumns().add(columnGrade);
        
//        gridpane.add(lblNim, 0, 0);
        gridpane.add(tfNim, 1, 0);
        gridpane.add(tvkrs, 0, 1);
        gridpane.add(lblIP, 0, 2);
        gridpane.add(lblIPS, 1, 2);
//        gridpane.setVgap(10);
//        gridpane.setHgap(10);

        String nim = tfNim.getText();
        loadAllData();
        TMataKuliah objKrs;
        for(int i=0; i<listKrs.size(); i++){
            if(listKrs.get(i).getNim().equals(nim)){
                String kodeMatkul = listKrs.get(i).getId_matkul();
                objKrs = getMatkulById(kodeMatkul);
                tvkrs.getItems().add(objKrs);
            }
        }
        Scene scene = new Scene(gridpane,1000,100);
        primaryStage.setTitle("Aplikasi Pencarian IPS Mahasiswa IT-DEL");
        primaryStage.setScene(scene);
        primaryStage.show();
        }
        public static void main(String[] args) throws SQLException,ClassNotFoundException{
            loadAllData();
            launch(args);
        }
        
        public static void loadAllData() throws SQLException,ClassNotFoundException{
            listMahasiswa = DBUtils.getAllMahasiswa();
            listMataKuliah = DBUtils.getAllMataKuliah();
            listKrs = DBUtils.getAllKrs();
        }
}
