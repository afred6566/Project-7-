/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_7;

import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class VisualInterface extends Application {
    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Inventory Managment");
        
        //Place nodes here
        pane.setLeft(getVBox());
        
        //Create a scene and place it in stage
        primaryStage.setScene(scene); //placing scene in stage
        primaryStage.show();
        
    }
    public static VBox getVBox() {
        VBox leftColumn = new VBox(15);
        leftColumn.setPadding(new Insets(15, 5, 5, 5));
        leftColumn.getChildren().add(new Label("Options"));
        
        Button[] options = {new Button("Enter"), new Button("Find"), 
            new Button("Delete"), new Button("List All"), new Button("Save")};
        
        for (Button newButton: options) {
            VBox.setMargin(newButton, new Insets(0, 0, 0, 15));
            leftColumn.getChildren().add(newButton);
        }
            
        return leftColumn;
    }
}
