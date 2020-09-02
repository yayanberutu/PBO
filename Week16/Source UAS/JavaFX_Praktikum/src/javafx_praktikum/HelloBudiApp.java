/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_praktikum;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author STU
 */
public class HelloBudiApp extends Application {

    /**
     * @param args the command line arguments
     */
    
    @Override
    public void start(Stage PrimaryStage) throws Exception {
        GridPane grdPnl = new GridPane();
        Label LblName = new Label("Nama");
        Label LblOutput = new Label();
        TextField txtName = new TextField();
        Button btn = new Button();
        btn.setText("Click Me");
        
        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
                if(txtName.getText().trim().isEmpty()){
                    LblOutput.setTextFill(Color.RED);
                    LblOutput.setText("Silahkan isi nama anda!!");
                }
                else{
                    LblOutput.setTextFill(Color.BLACK);
                    LblOutput.setText("Hello "+ txtName.getText().trim());
                }
            }
            
        });
        
        grdPnl.add(LblName,0,0);
        grdPnl.add(txtName,1,0);
        grdPnl.add(btn, 1,1,2,1);
        grdPnl.add(LblOutput,1,2,2,1);
        
        txtName.setPadding(new Insets(8,8,8,3));
        btn.setPadding(new Insets(8));
        
        ColumnConstraints cons1 = new ColumnConstraints();
        ColumnConstraints cons2 = new ColumnConstraints();
        cons1.setPercentWidth(30);
        cons2.setPercentWidth(70);
        
        grdPnl.getColumnConstraints().addAll(cons1,cons2);
        grdPnl.setPadding(new Insets(8,8,0,8));
        grdPnl.setHgap(8);
        grdPnl.setVgap(8);
        
        Scene scene = new Scene(grdPnl,400,150);
        
        PrimaryStage.setTitle("Aplikasi");
        PrimaryStage.setScene(scene);
        PrimaryStage.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    
}
