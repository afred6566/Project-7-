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

    public static Entry[] entryList;
    public static int num_entries;
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
        
        entryList = new Entry[200];
        num_entries = 0;
        
        readInventory("inventory.txt");
        
        launch(args);
        int i;
        char C;
        String code, Command;

        
        Command = null;
        C = ' ';
        

        System.out.println("Codes are entered as 1 to 8 characters.\nUse"
                + " \"e\" for enter,"
                + " \"f\" for find,"
                + " \"l\" for listing all the entries,"
                + " \"q\" to quit.");

        while (C != 'q') {
            System.out.print("Command: ");
            Command = stdin.next();
            C = Command.charAt(0);
            switch (C) {
                case 'e':
                    addItem();
                    break;
                case 'f':
                    code = stdin.next();
                    stdin.nextLine();
                    i = index(code);
                    if (i >= 0) {
                        displayEntry(entryList[i]);
                    } else {
                        System.out.println("**No entry with code "
                                + code);
                    }
                    break;
                case 'l':
                    listAllItems();
                    break;
                case 's':
                    sortList();
                    break;
                case 'q':
                    CopyInventoryToFile("inventory.txt");
                    System.out.println("Quitting the application. "
                            + "All the entries are stored in the file "
                            + "inventory.txt");
                    break;
                case 'd':
                    String todelete = stdin.next();
                    boolean deleteDone = false;
                    for (int j = 0; j < num_entries; j++) {
                        if (deleteDone == false) {
                            if (entryList[j].equals(todelete)) {
                                deleteDone = true;
                            }
                        } else {
                            entryList[j - 1] = entryList[j];
                        }
                    }
                    //This checks if there is anything to delete
                    if (deleteDone == true) {
                        num_entries--;
                    } else {
                        System.out.println("Could not find that in the list of entries. ");
                    }
                    break;
                default:
                    System.out.println("Invalid command. "
                            + "Please enter the command again!!!");
            }
        }

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

    public static void addItem() {

        /*entryList [num_entries] = new Entry();
            entryList[num_entries].name = name.get();
        
    
            entryList [num_entries].quantity = quantity.get();
        
            entryList [num_entries].note = notes.get();
        
    num_entries++;*/
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
