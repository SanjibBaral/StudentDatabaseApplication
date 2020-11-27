package com.studentdatabase.app;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentDatabaseApp {
	
	static ArrayList<Student> student_objects=new ArrayList<Student>();
	
	public static void main(String[] args) {
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
	}

	private static void StudentEntry() {
		Student student=new Student();
		student.enrollCourses();
		student.payTuition();
		StudentDatabaseApp.student_objects.add(student);
	}
	
	private static void displayStudents() {
		for(Student student:StudentDatabaseApp.student_objects)
			student.viewStatus();
	}
}
