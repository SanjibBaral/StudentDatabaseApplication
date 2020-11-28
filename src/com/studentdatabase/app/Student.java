package com.studentdatabase.app;

import java.util.ArrayList;
import java.util.Scanner;

public class Student {
	private String first_name;
	private String last_name;
	private String grade_year;
	private String id;
	private String birth_year,birth_month,birth_day;
	
	ArrayList<String> courses_enrolled=new ArrayList<String>();
	private int balance=0;
	static final int course_cost=600;
	static int object_count=1000;
	static String[] courses= {"History 101","Mathematics 101","English 101","Chemistry 101","Computer Science 101"};
	static String[] year= {"Freshman","Sophomore","Junior","Senior"};
	static Scanner sc=new Scanner(System.in);
	
	//Student Constructor to prompt for student name and year
	Student(){
		System.out.println("Enter First Name: ");
		this.first_name=sc.nextLine();
		System.out.println("\nEnter Last Name: ");
		this.last_name=sc.nextLine();
		System.out.println("Enter Birthdate info:");
		System.out.print("Year:");
		this.birth_year=sc.nextLine();
		System.out.print("\nMonth:");
		this.birth_month=sc.nextLine();
		System.out.print("\nDay:");
		this.birth_day=sc.nextLine();
		System.out.println("\nEnter Year: ");
		System.out.println("\n1. Freshman\n2. Sophomore\n3. Junior\n4. Senior");
		this.grade_year=sc.nextLine();
		this.id=Student.generateID(this.grade_year);
		Student.object_count++;	
	}
	//Generate 5-digit unique ID for student (first number being the grade level)
	private static String generateID(String year) {
		return (year+Integer.toString(Student.object_count));
		
	}
	
	//enroll to course and compute balance
	void enrollCourses() {
		int total_courses=0;
		int enroll_course;
		int i=1;
		System.out.println("Following are the courses availables");
		for (String course:Student.courses) {
			System.out.println(Integer.toString(i)+". "+course);
			i++;
		}
				
		//Get info on no. of courses to get enrolled.
		
		System.out.println("How many course do you want to enroll for "+this.first_name+" "+this.last_name+"?");
		total_courses=Integer.parseInt(sc.nextLine());
		
		
		//Enter courses to get enrolled
		for(int j=0;j<total_courses;j++) {
			System.out.println("Enter Course # "+Integer.toString(j+1)+":");
			enroll_course=Integer.parseInt(sc.nextLine());
			this.courses_enrolled.add(Student.courses[enroll_course-1]);
		}
		
		
		//Compute balance for total course enrolled
		this.balance=this.courses_enrolled.size()*Student.course_cost;
		this.viewBalance();
	}
	
	//view balance
	void viewBalance() {
		System.out.println(this.first_name+" "+this.last_name+"'s current balance is: $"+Integer.toString(this.balance));
	}
	
	//pay tuition
	void payTuition() {
		int tuition_paid;
		System.out.println("Enter amount you want to pay: ");
		tuition_paid=Integer.parseInt(sc.nextLine());
		//Next process after this is reading nextLine() 
		this.balance=this.balance-tuition_paid;
		System.out.println("Thank you for your payment of $"+Integer.toString(tuition_paid));
		this.viewBalance();

		}
	
	//View Status (student name, ID, courses enrolled and balance
	void viewStatus() {
		//print student name
		System.out.println("\nStudent Name: "+this.first_name+" "+this.last_name);
		System.out.println("Date of Birth: "+this.birth_month+"-"+this.birth_day+"-"+this.birth_year);
		//print ID
		System.out.println("ID: "+this.id);
		//print courses enrolled
		System.out.print("Courses Enrolled: ");
		for(String course:this.courses_enrolled)
			System.out.print(course+ ",");
		//print balance
		System.out.println("");
		this.viewBalance();
	}

	String generateKey() {
		//Returned string will be used as key to store info about student in a file.
		// returned String Format: MMDDYYYYFirstnameLastName
		return (this.birth_month+this.birth_day+this.birth_year+this.first_name+this.last_name);
	}
	
	String generateValue() {
		//Returned string will be used
		//returned string format: gradeYear,balance,Course1,Course2,..
		String value=null;
		value=this.grade_year+","+this.balance;
		for(String course:this.courses_enrolled)
			value=value+","+course;
		return value;
	}
	
}
