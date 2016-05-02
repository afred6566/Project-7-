/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_7;

import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.stage.Stage;
import Project_7.VisualInterface;
import static Project_7.VisualInterface.editItem;
import static Project_7.VisualInterface.enterPane;
import static Project_7.VisualInterface.results;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;
import javafx.util.Pair;


class Entry {

    public String name, quantity, note;
}

public class Project_7_Main extends Application {

    public static Entry[] entryList = new Entry[200];
    public static int num_entries = 0;
    public static Scanner stdin = new Scanner(System.in);
    public static BorderPane pane = new BorderPane();
    public static boolean debug = true;

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(pane, 800, 600);
        primaryStage.setTitle("Inventory Managment");
        pane.setStyle("-fx-background-color: #85CD66;"
                + "-fx-border-width: 5;"
                + "-fx-insets: 10;"
                + "-fx-border-radius: 5;"
                + "-fx-border-color: green;");
        //Place nodes here
        pane.setLeft(VisualInterface.getVBox());

        //Create a scene and place it in stage
        primaryStage.setScene(scene); //placing scene in stage
        primaryStage.show();
        
        primaryStage.setOnCloseRequest((WindowEvent w) -> {
            try {
                CopyInventoryToFile("inventory.txt");
            }
            catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("There was a problem saving your changes!");
            }
        });

    }

    public static void main(String args[]) throws Exception {
        readInventory("inventory.txt");
        launch(args);
    }

    public static void readInventory(String FileName) throws Exception {
        File F;
        F = new File(FileName);
        Scanner S = new Scanner(F);

        while (S.hasNextLine()) {
            entryList[num_entries] = new Entry();
            entryList[num_entries].name = S.next();
            entryList[num_entries].quantity = S.next();
            entryList[num_entries].note = S.nextLine();
            num_entries++;
        }
        S.close();
    }

    public static void enterName(String item) {
        String itemName = "Item";
        itemName = item;
    }

    public static void enterQuantity(String number) {
        String quantity = "Quantity";
        quantity = number;
    }

    public static void enterNotes(String notes) {
        String itemNotes = "Notes";
        itemNotes = notes;
    }

    public static int index(String Key) {
// Function to get the index of a key from an array
// if not found, returns -1
        for (int i = 0; i < num_entries; i++) {
            if (entryList[i].name.equalsIgnoreCase(Key)) {
                return i; // Found the Key, return index.
            }
        }
        return -1;
    }

    public static void displayEntry(Entry item) {
        System.out.println("--" + item.name + "\n"
                + "--" + item.quantity + "\n"
                + "--" + item.note);
    }

    public static void listAllItems() {
        int i = 0;
        while (i < num_entries) {
            displayEntry(entryList[i]);
            i++;
        }
    }

    public static void find(String search) {
        for (int i = 0, found = 0; i < num_entries; i++){
            if(search.toLowerCase().contains(entryList[i].name) || entryList[i].name.toLowerCase().contains(search)) {
                results.add(entryList[i].name);
                found++;
            }
        }
    }
    public static void delete(String name){        
        for(int i = 0; i < num_entries; i++) {
             if(entryList[i].name.equals(editItem)) {
                 for(int j = i; j < num_entries; j++){
                 entryList[j] = entryList[j + 1];
             }
                 num_entries--;
                 results.remove(editItem);
             }           
            
        }
    }

    public static void sortList() {
        int i;
        Entry temp;
        temp = new Entry();

        for (int j = 0; j < num_entries; j++) {
            for (i = j + 1; i < num_entries; i++) {
                if (entryList[j].name.compareToIgnoreCase(entryList[i].name) > 0) {
                    //Swap the entries if their names are not in ascending order
                    temp = entryList[j];
                    entryList[j] = entryList[i];
                    entryList[i] = temp;
                }
            }
        }
        System.out.println("Sorted the list. Press l to list all the items");
    }

    public static void CopyInventoryToFile(String FileName) throws Exception {
        FileOutputStream out = new FileOutputStream(FileName);
        PrintStream P = new PrintStream(out);

        for (int i = 0; i < num_entries; i++) {
            P.println(entryList[i].name + "\t" + entryList[i].quantity
                    + "\t" + entryList[i].note);
        }
    }
}
