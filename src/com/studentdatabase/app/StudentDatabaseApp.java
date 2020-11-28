package com.studentdatabase.app;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentDatabaseApp {
	
	static ArrayList<Student> student_objects=new ArrayList<Student>();
	
	public static void main(String[] args) {
		//Display list of choices for User to select
		String choice;
		StudentDatabaseFile file=new StudentDatabaseFile();
		boolean stop_app=false;
		
		while(!stop_app)
		{
			StudentDatabaseApp.startUpMessage();
			file.loadFile(); //loads current student data from file
			System.out.println("Enter the operation number: ");
			Scanner sc=new Scanner(System.in);
			choice=sc.nextLine();
			switch(choice) {
			//Student Entry
				case "1":
					//Complete
					System.out.println("You have selected to perform Student Entry.");
					Student s=StudentDatabaseApp.StudentEntry();
					file.addEntry(s);
					break;
						
			//Search Student and display Status
				case "2":
					String search_key=null;
					System.out.println("You have selected to display status of a student.");
					System.out.println("Enter date of birth in format MMDDYYYY:");
					search_key=sc.nextLine();
					System.out.println("Enter student first and last name without space with initial capitalized:");
					search_key=search_key.concat(sc.nextLine());
					file.viewStudent(search_key);
					break;
			
			//Pay Tuition for a Student and update balance
				case "3":
					System.out.println("You have selected to pay tuition.");
					break;
			
			//Run Report
				case "4":
					System.out.println("You have selected to run report.");
					break;
			
			//Quit
				case "5":
					//Complete
					System.out.println("You have selected to exit.");
					//Saving student entry to file
					
					file.saveMap();
					stop_app=true;
					break;
			
			}
		}
		
		/*
		//Ask how many students to enter
		int student_no;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter no. of students to enroll.");
		student_no=sc.nextInt();
		
		//CreateStudentObject
		for(int i=0;i<student_no;i++)
			StudentDatabaseApp.StudentEntry();
		StudentDatabaseApp.displayStudents();
		sc.close();
		*/
	}

	private static Student StudentEntry() {
		//Creates new entry for student and returns student object.
		Student student=new Student();
		student.enrollCourses();
		student.payTuition();
		//Check for Duplicate Entry
		return student;
	}
	
	private static void displayStudents() {
		for(Student student:StudentDatabaseApp.student_objects)
			student.viewStatus();
	}
	
	static void startUpMessage() {
		int count=20;
		String[] top_menu= {"\n1. Student Entry", "2. View Status of a student",
				"3. Pay Tuition", "4. Reports","5. Quit"};
		for(int i=0;i<count;i++)
			System.out.print("*");
		//System.out.println("\n");
		for(String menu:top_menu)
			System.out.println(menu);
		for(int i=0;i<count;i++)
			System.out.print("*");
		System.out.println("\n");
	}
}
