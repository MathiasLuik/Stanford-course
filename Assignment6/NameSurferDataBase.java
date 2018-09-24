/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import acm.util.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.print.attribute.standard.PrinterLocation;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

public class NameSurferDataBase implements NameSurferConstants {
	List<String> dataInFile = new ArrayList<>();
	private Map <String, NameSurferEntry> nameFromDatabase = new HashMap <String, NameSurferEntry>(); 
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 * @throws FileNotFoundException,IOException 
 */
	public NameSurferDataBase(String filename)  {	
		nameData(filename);
	}
	public void nameData(String filename)  {	
		try {
			System.out.println("I'm in NameSurferDataBase(String filename)");
			File file = new File(filename);
			BufferedReader br = new BufferedReader(new FileReader(file));
	        String line = "";   
	        while ((line = br.readLine()) != null) {	
	        	//nameFromDatabase.put(NameSurferEntry.getName(), arg1)
	        	//dataInFile.add(line);
	        	
	    		
	        	//
	        	NameSurferEntry nameEntry = new NameSurferEntry(line);
	        	//String o=nameEntry.getName();
	        	//System.out.println(o);
	        	
	        	nameFromDatabase.put(nameEntry.getName(), nameEntry);
	        	//System.out.println(nameEntry.getName());
	        	//System.out.println(nameEntry);
	        	//nameFromDatabase.
	        	//nameFromDatabase.add(nameEntry.getName(), nameEntry);
	        	//NameSurferEntry nameEntry = new NameSurferEntry(line);
				//namesdb.put(nameEntry.getName(), nameEntry);
	        	//System.out.println("here "+getWord());    
	        }
	        br.close();
	        
		}catch (Exception e) {
			 e.printStackTrace();
		} 
	}
	/*
	public NameSurferDataBase(String filename)  {	
		try {
			System.out.println("I'm in NameSurferDataBase(String filename)");
			File file = new File(filename);
			//FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(new FileReader(file));
	        //StringBuilder sb = new StringBuilder();
	        String line = "";
	        String word="A";
	        while ((line = br.readLine()) != null) {
	            if (line.startsWith(word)) {
	                System.out.println(line);
	                break;
	            }
	        }
	        br.close();
	        
		}catch (Exception e) {
			 e.printStackTrace();
		} 
	}
	*/
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		char ch = name.charAt(0);
		if(Character.isLowerCase(ch) == true) {
			ch = Character.toUpperCase(ch);
		}
		String otherLetters = name.substring(1);
		otherLetters = otherLetters.toLowerCase();
		name = ch + otherLetters;
		if (nameFromDatabase.containsKey(name)) {
			System.out.println(name);
			return nameFromDatabase.get(name);
		}			
		else{
			return null;
		}
		
		/*
		System.out.println("This is findentry "+ name + " I'm in NameSurferEntry findEntry(String name)");
		for(String eleme: dataInFile) {
			if (eleme.substring(0,eleme.indexOf(' ')).toString().equals(name)) {
	        	String thisIsOutCome=eleme;
	            System.out.println(thisIsOutCome);
	            //return thisIsOutCome;
	            break;
	            //NameSurferEntry.
			}
            //System.out.println(eleme);
        }
        */
		/*
		if (line.substring(0,line.indexOf(' ')).toString().equals(getWord())) {
        	
        	String thisIsOutCome=line;
            System.out.println(thisIsOutCome);
            break;
		}
		*/
		
		
		//return null;
	}
}

