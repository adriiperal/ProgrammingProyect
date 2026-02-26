package classes;

import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.BookNotAvailableException;
import exceptions.InvalidLoanException;
import exceptions.RepeatedUserException;
import exceptions.SanctionedUserException;

public class LibrayManager {
	public static final int DAYS_TO_RETURN = 14;
	private ArrayList<User> users = new ArrayList<>();
	private ArrayList<Loan> loans = new ArrayList<>();

	public User findUser(String id) {
		for (User u : users) {
			if (u.getMemberNumber().equals(id))
				return u;
		}
		return null;
	}

	public void registeruser(User u) throws RepeatedUserException {
		if (findUser(u.getMemberNumber()) != null) {
			throw new RepeatedUserException("repeated user");
		} else
			users.add(u);

	}

	public void issueLoan(String bookCode, String title, LocalDate date, User u)
			throws InvalidLoanException, SanctionedUserException, BookNotAvailableException {
		if (u.isSanctioned()) {
			throw new SanctionedUserException("User is sanctioned.");
		}

		for (Loan p : loans) {
			if (p.getBookCode().equals(bookCode) && p.getActualReturnDate() == null) {
				throw new BookNotAvailableException("Book is already out on loan.");
			}
		}

		loans.add(new Loan(bookCode, title, u, date, date.plusDays(DAYS_TO_RETURN), null));
	}

	public boolean returnBook(String bookCode, LocalDate returnDate) throws InvalidLoanException {
		for (Loan p : loans) {
			if (p.getBookCode().equals(bookCode) && p.getActualReturnDate() == null) {
				p.registerReturn(returnDate);
				p.setActualReturnDate(returnDate);
				int delay = p.calculateDelayDays();
				if (delay > 0) {
					p.getMember().sanction(delay);
				}
				return true;

			}
		}
		return false;

	}

	@Override
	public String toString() {
		return "LibrayManager [users=" + users + ", loans=" + loans + "]";
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public ArrayList<Loan> getLoans() {
		return loans;
	}

	public void setLoans(ArrayList<Loan> loans) {
		this.loans = loans;
	}

}
