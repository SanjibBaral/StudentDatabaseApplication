package com.studentdatabase.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class StudentDatabaseFile {
	String file_location=".\\data\\student.properties";
	File file=new File(file_location);
	FileInputStream fis=null;
	FileOutputStream fos=null;
	Properties props= new Properties();
	static Map mp=new HashMap();
	
	//Load Properties file data into map object
	void loadFile() {
		openFile(false);
		try {
			props.load(fis);
			Set keys=props.keySet();
			Iterator it=keys.iterator();
			String key;
			while(it.hasNext())
			{	
				key=(String) it.next();
				mp.put(key, props.getProperty(key));
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			closeFile(false);
		}
	}
	
	
	//Store student entry in map object
	void addEntry(Student s) {
		mp.put(s.generateKey(), s.generateValue());
	}
	
	//Save all map entries in a file
	void saveMap() {
		openFile(true);
		Set keys=mp.keySet();
		Iterator it=keys.iterator();
		String key;
		
		try {
			while(it.hasNext())
			{
				key=(String)it.next();
				props.setProperty(key, (String)mp.get(key));
			}
			props.store(fos,null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeFile(true);
	}
	//Search Student entry in a file
	
	//Compare duplicate entry in a file
	
	//Update entry in a file
	
	//Report Generation
	
	void openFile(boolean write_mode) {
		try {
			
			if (write_mode)
				fos=new FileOutputStream(file);
			else
				fis=new FileInputStream(file);
		}
		catch (FileNotFoundException fnf) {
			System.out.println("File is Not Found");
		}
		catch (Exception e) {
			System.out.println("Error in File Initialization");
		}	
	}
	
	void closeFile(boolean write_mode) {
		try {
			if (write_mode)
				fos.close();
			else
				fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	void printMap()
	{
		Set keys=mp.keySet();
		System.out.println("Printing Map Object");
		Iterator it=keys.iterator();
		while(it.hasNext())
			System.out.println(mp.get(it.next()));
	}
}
