/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxjdbcapp;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
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
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 *
 * @author ASUS
 */
public class JavaFXJDBCApp extends Application {
    public static List<TMahasiswa> listMahasiswa;
    public static List<TMataKuliah> listMatakuliah;
    public static List<TMkMhs> listKrs;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //kumpulan nodes/komponen masing-masing tab
        TabPane tp = new TabPane();
        Tab tab1 = new Tab("Mahasiswa");
        Tab tab2 = new Tab("Mata Kuliah");
        Tab tab3 = new Tab("Isi KRS");
        tp.getTabs().addAll(tab1, tab2, tab3);
        
        //buat grid tiap tab
        GridPane grdPaneMhs = new GridPane();
        GridPane grdPaneMatkul = new GridPane();
        GridPane grdKrs = new GridPane();
        
        tab1.setContent(grdPaneMhs);
        tab2.setContent(grdPaneMatkul);
        tab3.setContent(grdKrs);
        
        //kasih padding ke komponen yang ada di setiap grid sebesar 8
        grdPaneMhs.setPadding(new Insets(8));
        grdPaneMatkul.setPadding(new Insets(8));
        grdKrs.setPadding(new Insets(8));
        
        //kasih jarak tiap komponen yang berada di masing-masing grid
        
        grdPaneMhs.setHgap(8); grdPaneMhs.setVgap(8);
        grdPaneMatkul.setHgap(8); grdPaneMatkul.setVgap(8);
        grdKrs.setHgap(8); grdKrs.setVgap(8);
        
//        set Proporsi antara label dengan text inputan 30:70
        ColumnConstraints cons1 = new ColumnConstraints();
        ColumnConstraints cons2 = new ColumnConstraints();
        cons1.setPercentWidth(30);
        cons2.setPercentWidth(70);
        
        //Isi konten grid pane dosen dengan berbagai komponen yang dipeelukan
        //tab 1 (Mahasiswa)
        Label lblNama = new Label("Nama Mahasiswa");
        TextField txtNama = new TextField();
        Label lblNim = new Label("NIM");
        TextField txtNim = new TextField();
        Label lblDob = new Label("Date of Birth");
        DatePicker dpDob = new DatePicker(LocalDate.now());
        dpDob.setEditable(false);
        
        dpDob.setConverter(new StringConverter<LocalDate>() {
            String pattern = "dd/MM/yyyy";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern, Locale.US);

            {
                dpDob.setPromptText(pattern.toLowerCase());
            }
            @Override
            public String toString(LocalDate date) {
                if(date != null){
                    return dateFormatter.format(date);
                }
                else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if(string != null && !string.isEmpty()){
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
                
            }
        });
            Label lblEmail = new Label("Email");
            TextField txtEmail = new TextField();
            Label lblInfoTab1 = new Label();
            lblInfoTab1.setStyle("-fx-font-style: italic");
            
            Button btnSaveTab1 = new Button();
            btnSaveTab1.setPadding(new Insets(8));
            btnSaveTab1.setText("Simpan Data Mahasiswa");
            
            grdPaneMhs.getColumnConstraints().addAll(cons1, cons2);
            grdPaneMhs.add(lblNama, 0, 0); grdPaneMhs.add(txtNama, 1, 0);
            grdPaneMhs.add(lblNim, 0, 1); grdPaneMhs.add(txtNim, 1, 1);
            grdPaneMhs.add(lblDob, 0, 2); grdPaneMhs.add(dpDob, 1, 2);
            grdPaneMhs.add(lblEmail, 0, 3); grdPaneMhs.add(txtEmail, 1, 3);
            grdPaneMhs.add(btnSaveTab1, 1, 4);
            grdPaneMhs.add(lblInfoTab1, 1, 5);
            
            btnSaveTab1.setOnAction((ActionEvent event) -> {
                if(txtNim.getText().trim().isEmpty() || txtNama.getText().trim().isEmpty()){
                    lblInfoTab1.setTextFill(Color.RED);
                    lblInfoTab1.setText("Silahkan isi dengan lengkap setiap kolom yang ada");
                } else {
                    TMahasiswa obj = new TMahasiswa();
                    obj.setNim(txtNim.getText().trim());
                    obj.setNama(txtNama.getText().trim());
                    obj.setEmail(txtEmail.getText().trim());
                    
                    try {
                        obj.setDob(GeneralUtils.convertToDateFromString(dpDob.getEditor().getText(), "dd/MM/yyyy"));
                    } catch (ParseException ex) {
                        Logger.getLogger(JavaFXJDBCApp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    try {
                        int response = DBUtils.saveMahasiswa(obj);
                        loadAllData();
                        if(response > 0){
                            lblInfoTab1.setTextFill(Color.BLACK);
                            lblInfoTab1.setText("Data is successfully saved");
                        } else {
                            lblInfoTab1.setTextFill(Color.RED);
                            lblInfoTab1.setText("Unable to save the data!");
                        }
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(JavaFXJDBCApp.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(JavaFXJDBCApp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
        //end
        
        //tab 2 mata kuliah
        Label lblIdMatkul = new Label("Kode Mata Kuliah");
        TextField txtIdMatkul = new TextField();
         
        Label lblNamaMatkul = new Label("Nama Mata Kuliah");
        TextField txtNamaMatkul = new TextField();
        
        Label lblJlhSks = new Label("Jumlah SKS");
        TextField txtJlhSks = new TextField();
    
        Label lblInfoTab2 = new Label();
        lblInfoTab2.setStyle("-fx-font-style: italic");
        
        //force the field to be numeric only
        txtJlhSks.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if(!newValue.matches("\\d*")){
                txtJlhSks.setText(newValue.replace("[^\\d]", ""));
            }
        });
        
        Button btnSaveTab2 = new Button();
        btnSaveTab2.setPadding(new Insets(8));
        btnSaveTab2.setText("Simpan Data Mata Kuliah");
        
        grdPaneMatkul.getColumnConstraints().addAll(cons1, cons2);
        grdPaneMatkul.add(lblIdMatkul, 0, 0); grdPaneMatkul.add(txtIdMatkul, 1, 0);
        grdPaneMatkul.add(lblNamaMatkul, 0, 1); grdPaneMatkul.add(txtNamaMatkul, 1, 1);
        grdPaneMatkul.add(lblJlhSks, 0, 2); grdPaneMatkul.add(txtJlhSks, 1, 2);
        grdPaneMatkul.add(btnSaveTab2, 1, 3);
        grdPaneMatkul.add(lblInfoTab2, 1, 4);
        
        btnSaveTab2.setOnAction((t) -> {
            if(txtIdMatkul.getText().trim().isEmpty() || txtNamaMatkul.getText().trim().isEmpty() || txtJlhSks.getText().trim().isEmpty()){
                lblInfoTab2.setTextFill(Color.RED);
                lblInfoTab2.setText("Silahkan isi dengan legnkap setiap kolom yang ada");
            }
            else {
                TMataKuliah obj = new TMataKuliah();
                obj.setIdMatkul(txtIdMatkul.getText().trim());
                obj.setNamaMatkul(txtNamaMatkul.getText().trim());
                obj.setSks(Integer.valueOf(txtJlhSks.getText().trim()));
                
                try {
                    int response = DBUtils.saveMataKuliah(obj);
                    loadAllData();
                    if(response > 0){
                        lblInfoTab2.setTextFill(Color.BLACK);
                        lblInfoTab2.setText("Data is successfully saved");
                    } else {
                        lblInfoTab2.setTextFill(Color.RED);
                        lblInfoTab2.setText("Unable to save the data");
                    }
                
                } catch (SQLException ex){
                    Logger.getLogger(JavaFXJDBCApp.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(JavaFXJDBCApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        //end
        
        //tab 3 (krs)
        Label okMatkul = new Label("0"); Label okMhs = new Label("0");
        okMatkul.setVisible(false); okMhs.setVisible(false);
        Button btnSaveTab3 = new Button("Simpan Data KRS");
        btnSaveTab3.setDisable(true);
        Label lblInfoTab3 = new Label();
        Label lblIdMatkulKrs = new Label("Kode Mata Kuliah");
        Label lblNamaMatkulKrs = new Label("Tidak ditemukan");
        lblNamaMatkulKrs.setTextFill(Color.RED);
        Label lblNamaMatkul1 = new Label("Nama Mata Kuliah");
        TextField txtIdMatkulKrs = new TextField();
        
        Label lblNimKrs = new Label("NIM");
        Label lblNamaMhsKrs = new Label("Tidak ditemukan");
        lblNamaMhsKrs.setTextFill(Color.RED);
        Label lblNama1 = new Label("Nama Mahasiswa");
        TextField txtNimKrs = new TextField();
        
        //add Listener to text field
        txtIdMatkulKrs.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            Object objMatkul = GeneralUtils.findMatakuliah(newValue, listMatakuliah);
            if(objMatkul == null){
                lblNamaMatkulKrs.setTextFill(Color.RED);
                lblNamaMatkulKrs.setText("Tidak ditemukan");
                okMatkul.setText("0");
            }
            else {
                lblNamaMatkulKrs.setTextFill(Color.BLUE);
                lblNamaMatkulKrs.setText(((TMataKuliah)objMatkul).getNamaMatkul());
                okMatkul.setText("1");
            }
            
            if(okMatkul.getText().equals("0") || okMhs.getText().equals("0"))
                btnSaveTab3.setDisable(true);
            else 
                btnSaveTab3.setDisable(false);
        });
        
        txtNimKrs.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> { 
            Object objMhs = GeneralUtils.findMahasiswa(newValue, listMahasiswa);
            if(objMhs == null){
                lblNamaMhsKrs.setTextFill(Color.RED);
                lblNamaMhsKrs.setText("Tidak ditemukan");
                okMhs.setText("0");
            } else {
                lblNamaMhsKrs.setTextFill(Color.BLUE);
                lblNamaMhsKrs.setText(((TMahasiswa)objMhs).getNama());
                okMhs.setText("1");
            }
            if(okMatkul.getText().equals("0") || okMhs.getText().equals("0"))
                btnSaveTab3.setDisable(true);
            else 
                btnSaveTab3.setDisable(false);
        });
        
        grdKrs.getColumnConstraints().addAll(cons1, cons2);
        grdKrs.add(lblIdMatkulKrs, 0, 0); grdKrs.add(txtIdMatkulKrs, 1, 0);
        grdKrs.add(lblNamaMatkul1, 0, 1); grdKrs.add(lblNamaMatkulKrs, 1, 1);
        grdKrs.add(lblNimKrs, 0, 2); grdKrs.add(txtNimKrs, 1, 2);
        grdKrs.add(lblNama1, 0, 3); grdKrs.add(lblNamaMhsKrs,1,3);
        grdKrs.add(btnSaveTab3, 1, 4); grdKrs.add(lblInfoTab3, 1, 5);

        lblNamaMatkulKrs.setStyle("-fx-font-style: italic");
        lblNamaMhsKrs.setStyle("-fx-font-style: italic");
        lblInfoTab3.setStyle("-fx-font-style: italic");
        
        btnSaveTab3.setOnAction((t) -> {
            if(txtIdMatkulKrs.getText().trim().isEmpty() || txtNimKrs.getText().trim().isEmpty()){
                lblInfoTab3.setTextFill(Color.RED);
                lblInfoTab3.setText("Silahkan isi dengan lengkap setiap kolom yang ada");
            } else {
                TMkMhs obj = new TMkMhs();
                obj.setId_matkul(txtIdMatkulKrs.getText().trim().toUpperCase());
                obj.setNim(txtNimKrs.getText().trim());
                if(GeneralUtils.checkKRSExist(txtIdMatkulKrs.getText().trim().toUpperCase(), txtNimKrs.getText().trim(), listKrs) != null){
                    lblInfoTab3.setTextFill(Color.RED);
                    lblInfoTab3.setText(String.format("Mahasiswa %s telah mengambil mata kuliah ini", txtNimKrs.getText().trim()));
                }else {
                    try {
                        int response = DBUtils.saveKrs(obj);
                        loadAllData();
                        if(response > 0){
                            lblInfoTab3.setTextFill(Color.BLACK);
                            lblInfoTab3.setText("Data is successfully saved");
                        } else {
                            lblInfoTab3.setTextFill(Color.RED);
                            lblInfoTab3.setText("Unable to save data!");
                        }
                       
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(JavaFXJDBCApp.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(JavaFXJDBCApp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        });
        //end
        
        Scene scene = new Scene(tp, 750, 300);
        
        primaryStage.setTitle("Aplikasi Pengisian KRS Mahasiswa IT-Del");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        loadAllData();
        launch(args);
    }
    
    public static void loadAllData() throws ClassNotFoundException, ClassNotFoundException, SQLException, SQLException, SQLException{
        listMahasiswa = DBUtils.getAllMahasiswa();
        listMatakuliah = DBUtils.getAllMatakuliah();
        listKrs = DBUtils.getAllKrs();
    }
}
