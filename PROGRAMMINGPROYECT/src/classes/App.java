package classes;

import java.util.*;

import java.time.*;
import java.time.format.DateTimeFormatter;

import exceptions.*;

public class App {
	private static LibrayManager manager = new LibrayManager();
	private static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
		int option = 0;

		do {
			System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
			System.out.println("1. Register new user");
			System.out.println("2. Issue book loan");
			System.out.println("3. Return book");
			System.out.println("4. Check user status");
			System.out.println("5. Show active loans");
			System.out.println("6. Show sanctioned users");
			System.out.println("7. Update sanctions");
			System.out.println("8. Exit");
			System.out.print("Write your option: ");

			try {
				option = Integer.parseInt(keyboard.nextLine());

				switch (option) {
				case 1:
					registerUser();
					break;
				case 2:
					issueLoan();
					break;
				case 3:
					returnBook();
					break;
				case 4:
					checkUserStatus();
					break;
				case 5:
					showActiveLoans();
					break;
				case 6:
					showSanctionedUsers();
					break;
				case 7:
					updateSanctions();
					break;
				case 8:
					System.out.println("EXIT");
					break;
				default:
					System.out.println("Invalid option. Please try again.");
				}

			} catch (Exception e) {

				System.out.println("Error: " + e.getMessage());
			}

		} while (option != 8);
	}

	private static void registerUser() throws Exception {
		System.out.println("Name:");
		String name = keyboard.nextLine();
		System.out.println("email:");
		String email = keyboard.nextLine();
		System.out.print("Member ID (SOCxxxxx): ");
		String id = keyboard.nextLine();
		System.out.println("Registration Date:");
		System.out.print("Day (dd): ");
		int d = Integer.parseInt(keyboard.nextLine());
		System.out.print("Month (mm): ");
		int m = Integer.parseInt(keyboard.nextLine());
		System.out.print("Year (yyyy): ");
		int y = Integer.parseInt(keyboard.nextLine());
		
		LocalDate date = LocalDate.of(y, m, d);
		User newUser = new User(name, email, id, date, false, null);
		manager.registeruser(newUser);
		System.out.println("User registered successfully.");
	}

	private static void issueLoan() throws Exception {
		System.out.print("Member ID: ");
		String id = keyboard.nextLine();
		User u = manager.findUser(id);
		if (u == null) {
			throw new InvalidUserException("User not found.");
		}
		System.out.print("Book Code (AAA0000): ");
		String code = keyboard.nextLine();
		System.out.print("Title: ");
		String title = keyboard.nextLine();
		
		manager.issueLoan(code, title, LocalDate.now(), u);
        System.out.println("Loan issued successfully.");
	}

	private static void returnBook() throws Exception {
		System.out.println("BookCode");
		String bookCode = keyboard.nextLine();
		
		boolean canReturn = manager.returnBook(bookCode, LocalDate.now());
		if(canReturn) {
			System.out.println("valid return");
		}else {
			throw new BookNotAvailableException("not aviable book");
		}
	}

	private static void checkUserStatus() throws Exception {
		System.out.println("User ID:  ");
		String id = keyboard.nextLine();
		
	}

	private static void showActiveLoans() throws Exception {
	}

	private static void showSanctionedUsers() throws Exception {
	}

	private static void updateSanctions() throws Exception {

	}
}
