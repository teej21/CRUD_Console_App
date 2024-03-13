package org.teej21.crud_console_app;

import org.teej21.crud_console_app.dao.StudentDAO;
import org.teej21.crud_console_app.enity.Student;

import java.util.Scanner;

public class CrudApplication {
	private final StudentDAO studentDAO;
	private final Scanner sc = new Scanner(System.in);
	
	public CrudApplication(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	
	public void run() {
		while (true) {
			System.out.println("CRUD application running");
			showMenu();
			int choice;
			try {
				choice = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid choice");
				continue;
			}
			switch (choice) {
				case 1:
					create();
					break;
				case 2:
					read();
					break;
				case 3:
					update();
					break;
				case 4:
					delete();
					break;
				case 0:
					System.out.println("Exiting");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid choice");
					break;
			}
			
		}
	}
	
	private void showMenu() {
		System.out.println("1. Create");
		System.out.println("2. Read");
		System.out.println("3. Update");
		System.out.println("4. Delete");
		System.out.println("0. Exit");
		System.out.print("Enter your choice: ");
	}
	
	private void create() {
		System.out.println("Create new student...");
		System.out.println("Enter first name: ");
		String firstName = sc.nextLine();
		System.out.println("Enter last name: ");
		String lastName = sc.nextLine();
		System.out.println("Enter email: ");
		String email = sc.nextLine();
		Student student = new Student(firstName, lastName, email);
		this.studentDAO.save(student);
		System.out.println("Student created");
	}
	
	private void read() {
		System.out.println("1. Read all");
		System.out.println("2. Read by id");
		System.out.println("3. Read by last name");
		System.out.print("Enter your choice: ");
		int choice;
		try {
			choice = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Invalid choice");
			return;
		}
		switch (choice) {
			case 1 -> {
				System.out.println("Read all");
				this.studentDAO.findAll().forEach(System.out::println);
			}
			case 2 -> {
				System.out.println("Read by id");
				System.out.println("Enter id: ");
				int id;
				try {
					id = Integer.parseInt(sc.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Invalid id");
					return;
				}
				System.out.println(this.studentDAO.findById(id));
			}
			case 3 -> {
				System.out.println("Read by last name");
				System.out.println("Enter last name: ");
				String lastName = sc.nextLine();
				this.studentDAO.findByLastName(lastName).forEach(System.out::println);
			}
		}
	}
	
	private void update() {
		System.out.println("Update");
		this.studentDAO.findAll().forEach(System.out::println);
		System.out.println("Enter student id to update: ");
		int id;
		try {
			id = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Invalid id");
			return;
		}
		Student student = this.studentDAO.findById(id);
		if (student == null) {
			System.out.println("Student not found");
			return;
		}
		System.out.println("Enter new first name: ");
		String firstName = sc.nextLine();
		System.out.println("Enter new last name: ");
		String lastName = sc.nextLine();
		System.out.println("Enter new email: ");
		String email = sc.nextLine();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		this.studentDAO.update(student);
		System.out.println("Student updated");
	}
	
	private void delete() {
		System.out.println("Delete");
		System.out.println("1. Delete by id");
		System.out.println("2. Delete all");
		System.out.print("Enter your choice: ");
		int choice;
		try {
			choice = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Invalid choice");
			return;
		}
		switch (choice) {
			case 1 -> {
				System.out.println("Delete by id");
				System.out.println("Enter id: ");
				int id;
				try {
					id = Integer.parseInt(sc.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Invalid id");
					return;
				}
				this.studentDAO.delete(id);
				System.out.println("Student deleted");
			}
			case 2 -> {
				System.out.println("Delete all");
				this.studentDAO.deleteAll();
				System.out.println("All students deleted");
			}
		}
	}
}
