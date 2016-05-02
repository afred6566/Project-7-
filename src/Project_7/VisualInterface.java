/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_7;

import static Project_7.Project_7_Main.CopyInventoryToFile;
import static Project_7.Project_7_Main.entryList;
import static Project_7.Project_7_Main.num_entries;
import static Project_7.Project_7_Main.pane;
import static Project_7.Project_7_Main.debug;
import static Project_7.Project_7_Main.find;
import static Project_7.VisualInterface.results;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Pair;

public class VisualInterface {

    public static ObservableList<String> results = FXCollections.observableArrayList();
    public static Label findName = new Label();
    private static String editItem;
    

    public static VBox getVBox() {
        VBox leftColumn = new VBox(15);
        leftColumn.setStyle("-fx-background-image: url('http://images.freeimages.com/images/premium/previews/1932/19323130-fruits-and-vegetables-background.jpg');"
                + "-fx-border-width: 5;"
                + "-fx-insets: 10;"
                + "-fx-border-radius: 5;"
                + "-fx-border-color: white;");

        Label optionLabel = new Label("Options");
        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(5.0);
        shadow.setOffsetY(5.0);
        leftColumn.setPrefSize(200, 600);
        leftColumn.setSpacing(40);
        leftColumn.setPadding(new Insets(15, 5, 5, 5));
        optionLabel.setMinWidth(100);
        optionLabel.setFont(new Font("Rockwell Extra Bold", 35));
        optionLabel.setTextFill(Color.WHITE);
        optionLabel.setEffect(shadow);
        leftColumn.getChildren().add(optionLabel);

        Button btE = new Button("Enter");
        Button btF = new Button("Find");
        Button btD = new Button("Delete");
        Button btL = new Button("List All");
        Button btQ = new Button("Save");
        Button[] options = {btE, btF, btD, btL, btQ};

        for (int i = 0; i < options.length; i++) {
            VBox.setMargin(options[i], new Insets(0, 0, 0, 15));
            leftColumn.getChildren().add(options[i]);
            options[i].setStyle("-fx-font: 30 Rockwell");
            options[i].setMinWidth(150);
        }
        //------Handlers for 1st Enter Buttons----------------------//
        btE.setOnAction((ActionEvent e) -> {
            if (debug) {
                System.out.println("clicked");
            }
            VBox enterBox = enterPane();
            pane.getChildren().clear();
            pane.getChildren().addAll(leftColumn, enterBox);

            //Project_7_Main.addItem();
        });

        btF.setOnAction((ActionEvent e) -> {
            if (debug) {
                System.out.println();
            }
            VBox findBox = findPane();
            pane.getChildren().clear();
            pane.getChildren().addAll(leftColumn, findBox);
        });
        btD.setOnAction((ActionEvent e) -> {
            if (debug) {
                System.out.println("clicked");
            }
            VBox deleteBox = deletePane();
            pane.getChildren().clear();
            pane.getChildren().addAll(leftColumn, deleteBox);
        });
        btL.setOnAction((ActionEvent e) -> {
            if (debug) {
                System.out.println("clicked");
            }
            VBox listBox = listPane();
            pane.getChildren().clear();
            pane.getChildren().addAll(leftColumn, listBox);
        });
        btQ.setOnAction((ActionEvent e) -> {
            if (debug) {
                System.out.println("clicked");
            }
            VBox saveBox = savePane();
            pane.getChildren().clear();
            pane.getChildren().addAll(leftColumn, saveBox);
        });

        return leftColumn;
    }

    public static VBox enterPane() {
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Enter an item");

        // Set the picture (must be included in the project).
        //ImageView imageView = new ImageView(new Image("login.png"));
        // Set the button types.
        Button btnEnter = new Button("Enter");
        btnEnter.setMinSize(60, 40);
        Button btnCancel = new Button("Cancel");
        btnCancel.setMinSize(60, 40);

        VBox vBoxE = new VBox(15);
        vBoxE.setPadding(new Insets(15, 15, 15, 15));
        vBoxE.setPrefSize(500, 350);
        vBoxE.setStyle("-fx-background-color: red;"
                + "-fx-border-width: 5;"
                + "-fx-insets: 10;"
                + "-fx-border-radius: 5;"
                + "-fx-border-color: gold;");

        Label paneLabel = new Label("Enter an item");
        paneLabel.setMinWidth(300);
        paneLabel.setFont(new Font("Rockwell Extra Bold", 25));
        HBox label = getHBox();
        label.getChildren().add(paneLabel);

        TextField name = new TextField();
        name.setMinWidth(400);
        Label nameLabel = new Label("Item: ");
        nameLabel.setMinWidth(100);
        nameLabel.setFont(new Font("Rockwell", 20));
        HBox enterItem = getHBox();
        enterItem.getChildren().addAll(nameLabel, name);

        TextField quantity = new TextField();
        quantity.setMinWidth(400);
        Label numLabel = new Label("Quantity: ");
        numLabel.setMinWidth(100);
        numLabel.setFont(new Font("Rockwell", 20));
        HBox enterNum = getHBox();
        enterNum.getChildren().addAll(numLabel, quantity);

        TextArea notes = new TextArea();
        notes.setMinWidth(400);
        notes.setWrapText(true);
        notes.setMinHeight(250);
        Label notesLabel = new Label("Notes: ");
        notesLabel.setMinWidth(100);
        notesLabel.setFont(new Font("Rockwell", 20));
        HBox enterNotes = getHBox();
        enterNotes.getChildren().addAll(notesLabel, notes);

        HBox buttons = getHBox();
        buttons.setTranslateX(350);
        buttons.getChildren().addAll(btnEnter, btnCancel);

        vBoxE.getChildren().addAll(label, enterItem, enterNum, enterNotes, buttons);
        vBoxE.setTranslateX(200);

        btnEnter.setOnAction((ActionEvent e) -> {
            if (debug) {
                System.out.println("clicked");
            }
            entryList[num_entries] = new Entry();
            entryList[num_entries].name = name.getText();
            entryList[num_entries].quantity = quantity.getText();
            entryList[num_entries].note = notes.getText();
            if (debug) {
                System.out.println(entryList[num_entries].name + entryList[num_entries].quantity + entryList[num_entries].note);
            }
            num_entries++;
            
            
            enterPane().getChildren().clear();
            pane.getChildren().add(enterPane());
        });
        btnCancel.setOnAction((ActionEvent e) -> {
            if (debug) {
                System.out.println("clicked");
            }
            pane.getChildren().remove(vBoxE);
        });
        return vBoxE;
    }

    public static VBox findPane() {
        VBox vBoxF = new VBox(15);
        vBoxF.setPadding(new Insets(15, 15, 15, 15));
        vBoxF.setPrefSize(500, 350);

        Button btnEdit = new Button("Edit");
        btnEdit.setMinSize(60, 40);
        Button btnCancel = new Button("Cancel");
        btnCancel.setMinSize(60, 40);

        Label paneLabel = new Label("Enter an item");
        paneLabel.setMinWidth(300);
        paneLabel.setFont(new Font("Rockwell Extra Bold", 30));
        HBox label = getHBox();
        label.getChildren().add(paneLabel);

        TextField findItem = new TextField();
        findItem.setMinWidth(300);
        Label nameLabel = new Label("Item: ");
        nameLabel.setMinWidth(75);
        nameLabel.setFont(new Font("Rockwell", 20));
        HBox enterItem = getHBox();
        enterItem.getChildren().addAll(nameLabel, findItem);

        HBox buttons = getHBox();
        buttons.setTranslateX(350);
        buttons.getChildren().addAll(btnEdit, btnCancel);

        ListView findResults = new ListView();
        findResults.setMinWidth(395);
        findResults.setTranslateX(15);
        findResults.setMinHeight(300);
        
        results.clear();
        findResults.setItems(results);

        vBoxF.getChildren().addAll(label, enterItem, findResults, buttons);
        vBoxF.setTranslateX(200);

        if(debug) findResults.setItems(results);
        
        findItem.setOnKeyReleased((KeyEvent event) -> {
            results.clear();
            Project_7_Main.find(findItem.getText());
            if (debug) {
                System.out.println(results);
            }
            findResults.setItems(results);
        });
        findResults.setOnMouseClicked((MouseEvent m) -> {
            editItem = findResults.getSelectionModel().getSelectedItem().toString();
        });
        btnEdit.setOnAction((ActionEvent e) -> {
            if (debug) {
                System.out.println("clicked");
            }
            findName.setText(findResults.getSelectionModel().getSelectedItem().toString());
            if (debug) {
                System.out.println(findResults.getSelectionModel().getSelectedItem().toString());
            }
            for (int i = 0; i < num_entries; i++) {
                if (editItem.toLowerCase().contains(entryList[i].name.toLowerCase()) || entryList[i].name.toLowerCase().contains(editItem.toLowerCase())) {
                    pane.getChildren().remove(vBoxF);
                    pane.getChildren().add(editPane());
                }
            }
        });
        btnCancel.setOnAction((ActionEvent e) -> {
            if (debug) {
                System.out.println("clicked");
            }
            pane.getChildren().remove(vBoxF);
        });
        return vBoxF;
    }

    public static VBox editPane() {
        // Set the button types.
        Button btnEnter = new Button("Enter");
        btnEnter.setMinSize(60, 40);
        Button btnCancel = new Button("Cancel");
        btnCancel.setMinSize(60, 40);

        VBox vBoxEt = new VBox(15);
        vBoxEt.setPadding(new Insets(15, 15, 15, 15));
        vBoxEt.setPrefSize(500, 350);
        vBoxEt.setStyle("-fx-background: gold");

        findName.setMinWidth(300);
        findName.setFont(new Font("Rockwell Extra Bold", 25));
        HBox label = getHBox();
        label.getChildren().add(findName);

        TextField quantity = new TextField();
        for (int i = 0; i < num_entries; i++) {
            if(findName.getText().toLowerCase().contains(entryList[i].name.toLowerCase()))
                quantity.setText(entryList[i].quantity);
        }
        quantity.setMinWidth(300);
        Label numLabel = new Label("Quantity: ");
        numLabel.setMinWidth(75);
        numLabel.setFont(new Font("Rockwell", 15));
        HBox enterNum = getHBox();
        enterNum.getChildren().addAll(numLabel, quantity);

        TextArea notes = new TextArea();
        for (int i = 0; i < num_entries; i++) {
            if(findName.getText().toLowerCase().contains(entryList[i].name.toLowerCase()))
                notes.setText(entryList[i].note);
        }
        notes.setMinWidth(300);
        notes.setWrapText(true);
        notes.setMinHeight(250);
        Label notesLabel = new Label("Notes: ");
        notesLabel.setMinWidth(75);
        notesLabel.setFont(new Font("Rockwell", 15));
        HBox enterNotes = getHBox();
        enterNotes.getChildren().addAll(notesLabel, notes);

        HBox buttons = getHBox();
        buttons.setTranslateX(350);
        buttons.getChildren().addAll(btnEnter, btnCancel);

        vBoxEt.getChildren().addAll(label, enterNum, enterNotes, buttons);
        vBoxEt.setTranslateX(200);

        btnEnter.setOnAction((ActionEvent e) -> {
            if (debug) 
                System.out.println("clicked");
            for (int i = 0; i < num_entries; i++) {
                if(findName.getText().toLowerCase().contains(entryList[i].name.toLowerCase())) {
                entryList[i].quantity = quantity.getText();
                entryList[i].note = notes.getText();
                }
            } 
            try {
                CopyInventoryToFile("inventory.txt");
            }catch (Exception er){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("There was a problem saving your changes!");
            }
        });
        btnCancel.setOnAction((ActionEvent e) -> {
            if (debug) {
                System.out.println("clicked");
            }
            pane.getChildren().remove(vBoxEt);
        });
        return vBoxEt;
    }

    public static VBox listPane() {
        VBox vBoxL = new VBox(15);
        vBoxL.setPadding(new Insets(15, 15, 15, 15));
        vBoxL.setPrefSize(500, 350);
        vBoxL.setStyle("-fx-background: gold");

        Button btnEnter = new Button("Enter");
        btnEnter.setMinSize(60, 40);
        Button btnCancel = new Button("Cancel");
        btnCancel.setMinSize(60, 40);
        Button btnSort = new Button("Sort");
        btnSort.setMinSize(60, 40);

        Label paneLabel = new Label("All Items");
        paneLabel.setMinWidth(300);
        paneLabel.setFont(new Font("Rockwell Extra Bold", 30));
        HBox label = getHBox();
        label.getChildren().add(paneLabel);

        ListView allResults = new ListView();
        allResults.setMinWidth(395);
        allResults.setTranslateX(15);
        allResults.setMinHeight(300);
        results.clear();
        find("");
        allResults.setItems(results);

        HBox buttons = getHBox();
        buttons.setTranslateX(300);
        buttons.getChildren().addAll(btnSort, btnEnter, btnCancel);

        vBoxL.getChildren().addAll(label, allResults, buttons);
        vBoxL.setTranslateX(200);
        
        allResults.setOnMouseClicked((MouseEvent m) -> {
            editItem = allResults.getSelectionModel().getSelectedItem().toString();
        });
        btnSort.setOnAction((ActionEvent e) -> {
            if (debug)  System.out.println("clicked");
            Project_7_Main.sortList();
            
            pane.getChildren().remove(vBoxL);            
            pane.getChildren().add(listPane());
        });
        btnEnter.setOnAction((ActionEvent e) -> {
            if (debug) {
                System.out.println("clicked");
            }
            findName.setText(allResults.getSelectionModel().getSelectedItem().toString());
            if (debug) {
                System.out.println(allResults.getSelectionModel().getSelectedItem().toString());
            }
            pane.getChildren().remove(vBoxL);
            pane.getChildren().add(editPane());
        });
        btnCancel.setOnAction((ActionEvent e) -> {
            if (debug) {
                System.out.println("clicked");
            }
            pane.getChildren().remove(vBoxL);
        });
        return vBoxL;
    }

    public static VBox deletePane() {
        VBox vBoxD = new VBox(15);
        vBoxD.setPadding(new Insets(15, 15, 15, 15));
        vBoxD.setPrefSize(500, 350);
        vBoxD.setStyle("-fx-background: gold;");

        Button btnDelete = new Button("Delete");
        btnDelete.setMinSize(60, 40);
        Button btnCancel = new Button("Cancel");
        btnCancel.setMinSize(60, 40);

        Label paneLabel = new Label("Enter an item to delete");
        paneLabel.setMinWidth(500);
        paneLabel.setFont(new Font("Rockwell Extra Bold", 30));
        HBox label = getHBox();
        label.getChildren().add(paneLabel);

        TextField delete = new TextField();
        delete.setMinWidth(300);
        Label nameLabel = new Label("Item: ");
        nameLabel.setMinWidth(75);
        nameLabel.setFont(new Font("Rockwell", 20));
        HBox deleteItem = getHBox();
        deleteItem.getChildren().addAll(nameLabel, delete);

        HBox buttons = getHBox();
        buttons.setTranslateX(350);
        buttons.getChildren().addAll(btnDelete, btnCancel);

        ListView items = new ListView();
        items.setMinWidth(395);
        items.setTranslateX(15);
        items.setMinHeight(300);
        
        results.clear();
        items.setItems(results);

        vBoxD.getChildren().addAll(label, deleteItem, items, buttons);
        vBoxD.setTranslateX(200);
        
        delete.setOnKeyReleased((KeyEvent event) -> {
            results.clear();
            Project_7_Main.find(delete.getText());
            if (debug) {
                System.out.println(results);
            }
            items.setItems(results);
        });
        btnDelete.setOnAction((ActionEvent e) -> {
            if (debug) {
                System.out.println("clicked");
            }
            //Delete function written here
        });
        btnCancel.setOnAction((ActionEvent e) -> {
            if (debug) {
                System.out.println("clicked");
            }
            pane.getChildren().remove(vBoxD);
        });

        return vBoxD;
    }

    public static VBox savePane() {
        VBox vBoxS = new VBox(15);
        vBoxS.setPadding(new Insets(15, 15, 15, 15));
        vBoxS.setPrefSize(500, 350);
        vBoxS.setStyle("-fx-background: gold");

        Button btnOk = new Button("OK!");
        btnOk.setMinSize(60, 40);

        Label paneLabel = new Label("Items saved!");
        paneLabel.setMinWidth(300);
        paneLabel.setAlignment(Pos.CENTER);
        paneLabel.setFont(new Font("Rockwell Extra Bold", 30));
        HBox label = getHBox();
        label.getChildren().add(paneLabel);

        HBox buttons = getHBox();
        buttons.setTranslateX(400);
        buttons.getChildren().addAll(btnOk);

        vBoxS.getChildren().addAll(label, buttons);
        vBoxS.setTranslateX(200);
        btnOk.setOnAction((ActionEvent e) -> {
            if (debug) {
                System.out.println("clicked");
            }
            pane.getChildren().remove(vBoxS);
        });

        return vBoxS;
    }

    public static HBox getHBox() {
        HBox hBox = new HBox(15);
        hBox.setPadding(new Insets(15, 15, 15, 15));
        hBox.setStyle("-fx-background: gold");
        return hBox;
    }
}
