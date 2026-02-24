package classes;

import java.time.LocalDate;

import exceptions.InvalidUserException;

public class User {
	private String name;
	private String email;
	private String memberNumber;
	private LocalDate registrationDate;
	private boolean sanctioned;
	private LocalDate sanctionEndDate;

	public User(String name, String email, String memberNumber, LocalDate registrationDate, boolean sanctioned,
			LocalDate sanctionEndDate) throws InvalidUserException {

		if (!email.contains("@") || !email.contains(".")) {
			throw new InvalidUserException("Invalid email format.");
		}
		if (!memberNumber.matches("SOC\\d{5}")) {
			throw new InvalidUserException("Invalid member number.");
		}
		this.name = name;
		this.email = email;
		this.memberNumber = memberNumber;
		this.registrationDate = registrationDate;
		this.sanctioned = false;
		this.sanctionEndDate = null;
	}

	public void sanction(int days) {
		this.sanctioned = true;
		this.sanctionEndDate = LocalDate.now().plusDays(days);
	}

	public void liftSanction() {
		this.sanctioned = false;
	}

	public boolean isSancioned() {
		return sanctioned;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", memberNumber=" + memberNumber + ", registrationDate="
				+ registrationDate + ", sanctioned=" + sanctioned + ", sanctionEndDate=" + sanctionEndDate + "]";
	}

}
