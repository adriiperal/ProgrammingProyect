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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public boolean isSanctioned() {
		return sanctioned;
	}

	public void setSanctioned(boolean sanctioned) {
		this.sanctioned = sanctioned;
	}

	public LocalDate getSanctionEndDate() {
		return sanctionEndDate;
	}

	public void setSanctionEndDate(LocalDate sanctionEndDate) {
		this.sanctionEndDate = sanctionEndDate;
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
