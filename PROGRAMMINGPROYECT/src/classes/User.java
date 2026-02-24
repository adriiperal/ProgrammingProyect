package classes;

import java.time.LocalDate;

public class User {
	private String name;
	private String email;
	private String memberNumber;
	private LocalDate registrationDate;
	private boolean sanctioned;
	private LocalDate sanctionEndDate;

	public User(String name, String email, String memberNumber, LocalDate registrationDate, boolean sanctioned,
			LocalDate sanctionEndDate) {
		super();
		this.name = name;
		this.email = email;
		this.memberNumber = memberNumber;
		this.registrationDate = registrationDate;
		this.sanctioned = sanctioned;
		this.sanctionEndDate = sanctionEndDate;
	}
	
}
