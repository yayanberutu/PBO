/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hellobudiapp;

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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class HelloBudiApp extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        GridPane grdPnl = new GridPane();
        Label lblNama = new Label("Nama");
        Label lblOutput = new Label();
        TextField txtNama = new TextField();
        Button btn = new Button();
        btn.setText("Click Me");
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(txtNama.getText().trim().isEmpty()){
                    lblOutput.setTextFill(Color.RED);
                    lblOutput.setText("Silahkan isi nama anda!");
                }
                else {
                    lblOutput.setTextFill(Color.BLACK);
                    lblOutput.setText("Hello "+ txtNama.getText().trim());
                }
            }
        });
        
        grdPnl.add(lblNama, 0, 0); grdPnl.add(txtNama, 1, 0);
        grdPnl.add(btn, 1, 1, 2, 1);
        grdPnl.add(lblOutput, 1, 2, 2, 1);
        
        txtNama.setPadding(new Insets(8,8,8,3));
        btn.setPadding(new Insets(8));
        
        ColumnConstraints cons1 = new ColumnConstraints();
        ColumnConstraints cons2 = new ColumnConstraints();
        cons1.setPercentWidth(30);
        cons2.setPercentWidth(70);
        
        grdPnl.getColumnConstraints().addAll(cons1, cons2);
        grdPnl.setPadding(new Insets(8,8,0,8));
        grdPnl.setHgap(8); grdPnl.setVgap(8);
        Scene scene = new Scene(grdPnl, 400, 150);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
