package com.studentdatabase.app;

import java.util.ArrayList;
import java.util.Scanner;

public class Student {
	private String first_name;
	private String last_name;
	private String grade_year;
	private String id;
	ArrayList<String> courses_enrolled=new ArrayList<String>();
	private int balance=0;
	static final int course_cost=600;
	static int object_count=1000;
	static String[] courses= {"History101","Mathematics 101","English 101","Chemistry 101","Computer Science 101"};
	static Scanner sc=new Scanner(System.in);
	
	//Student Constructor to prompt for student name and year
	Student(){
		System.out.println("Enter First Name: ");
		this.first_name=sc.nextLine();
		System.out.println("\nEnter Last Name: ");
		this.last_name=sc.nextLine();
		System.out.println("\nEnter Year(1-9): ");
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
	
	
	
}
