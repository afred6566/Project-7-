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
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

class Entry {
	public String name, quantity, note;
}

public class Project_7_Main extends Application {
    public static Entry[]  entryList;
    public static int     num_entries;
    public static Scanner  stdin = new Scanner(System.in);

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Inventory Managment");
        
        //Place nodes here
        pane.setLeft(VisualInterface.getVBox());
        
        //Create a scene and place it in stage
        primaryStage.setScene(scene); //placing scene in stage
        primaryStage.show();
        
    }
    public static void main(String args[]) throws Exception{
        launch(args);
        int i; 
        char C;
	String code, Command;

	entryList = new Entry[200];
	num_entries = 0;
	Command = null;
	C = ' ';
	readInventory("inventory.txt");
	
	System.out.println("Codes are entered as 1 to 8 characters.\nUse" +
			" \"e\" for enter," +
			" \"f\" for find," +
			" \"l\" for listing all the entries," +
			" \"q\" to quit.");
	
	while(C != 'q'){
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
			if (i >= 0) displayEntry(entryList [i]);
			else        System.out.println("**No entry with code "
                                + code); 
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
		default:
			System.out.println("Invalid command. "
                                + "Please enter the command again!!!");				}
	}

    

}
public static void readInventory(String FileName) throws Exception {
	File F;
	F  = new File(FileName);		
	Scanner S = new Scanner(F);				
	
	while (S.hasNextLine()) {
		entryList [num_entries]= new Entry();
		entryList [num_entries].name   = S.next();
		entryList [num_entries].quantity = S.next();
		entryList [num_entries].note   = S.nextLine();
		num_entries++;
	}
	S.close();		
}

public static void addItem() {
	String name = stdin.next();
	String quantity;
	stdin.nextLine();
	entryList [num_entries] 	  = new Entry();
	entryList [num_entries].name = name;
	
	System.out.print("Enter Quantity: ");
	quantity = stdin.nextLine();
	entryList [num_entries].quantity = quantity;
		
	System.out.print("Enter Notes: ");
	entryList [num_entries].note = stdin.nextLine();
	num_entries++;
}

	 
public static int index(String Key) {
// Function to get the index of a key from an array
// if not found, returns -1
	for (int i=0; i < num_entries; i++) {
		if (entryList [i].name.equalsIgnoreCase(Key))
				return i; // Found the Key, return index.
	}
	return -1;
}

public static void displayEntry(Entry item) {
	System.out.println("--"+ item.name+"\t"+
	   		   item.quantity+"\t"+
	   		   item.note);
}

public static void listAllItems() {
	int i = 0;
	while (i < num_entries) {
		displayEntry(entryList [i]);
		i++;
	}
}

public static void sortList() {
	int i;
	Entry temp;
	temp = new Entry();
	
	for(int j=0; j < num_entries; j++) {
		for(i = j+1; i < num_entries; i++) {
			if(entryList [j].name.compareToIgnoreCase(entryList [i].name)>0) {
				//Swap the entries if their names are not in ascending order
				temp 		   = entryList [j];
				entryList [j] = entryList [i];
				entryList [i] = temp;
				}
			}
		}
	System.out.println("Sorted the list. Press l to list all the items");
}


public static void CopyInventoryToFile(String FileName) throws Exception{
	FileOutputStream out = new FileOutputStream(FileName);
	PrintStream P        = new PrintStream( out );
	 			
	for (int i=0; i < num_entries; i++) {
		P.println(entryList [i].name + "\t" + entryList [i].quantity +
					"\t" + entryList [i].note);
	}		
}
}


