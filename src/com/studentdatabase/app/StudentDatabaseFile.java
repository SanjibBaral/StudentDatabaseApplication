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
	static String[] year= {"Freshman","Sophomore","Junior","Senior"};
	
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
	void viewStudent(String key) {
		//Displays details on student by searching key in map object.
		//Input: key - String (MMDDYYYYFirstnameLastname)
		String DateOfBirth=key.substring(0,8);
		String name=key.substring(8);
		String value=(String) mp.get(key);
		
		//Stored value is string in format "gradeyear,balance,course1,course2,.."
		String[] str_array=value.split(",");
		
		System.out.println("\n----Printing Student Status Detail------\n");
		System.out.println("Name: "+ name);
		System.out.println("Date of Birth:"+DateOfBirth);
		System.out.println("Year: "+year[(Integer.parseInt(str_array[0])-1)]);
		System.out.println("Balance: $"+str_array[1]);
		System.out.print("Courses Enrolled: ");
		for(int i=2;i<str_array.length;i++)
			System.out.print(str_array[i]+",");
		System.out.println("\n");
		
	}
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
		String key;
		Set keys=mp.keySet();
		System.out.println("Printing Map Object");
		Iterator it=keys.iterator();
		while(it.hasNext())
		{
			key=(String) it.next();
			System.out.print(key+": ");
			System.out.println(mp.get(key));
		}
	}
}
