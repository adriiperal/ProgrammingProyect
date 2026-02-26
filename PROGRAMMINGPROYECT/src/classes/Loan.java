package classes;

import java.time.LocalDate;

import exceptions.InvalidLoanException;

public class Loan {
	private String bookCode;
	private String bookTitle;
	private User member;
	private LocalDate LoanDate;
	private LocalDate dueDate;
	private LocalDate actualReturnDate;

	public Loan(String bookCode, String bookTitle, User member, LocalDate loanDate, LocalDate dueDate,
			LocalDate actualReturnDate) throws InvalidLoanException {
		if (!bookCode.matches("[A-Z]{3}\\d{4}")) {
			throw new InvalidLoanException("invalid book code");
		}
		if (bookTitle == null || loanDate == null || loanDate.isAfter(LocalDate.now())) {
			throw new InvalidLoanException("Invalid loan data.");
		}
		this.bookCode = bookCode;
		this.bookTitle = bookTitle;
		this.member = member;
		this.LoanDate = loanDate;
		this.dueDate = loanDate.plusDays(14);
		this.actualReturnDate = null;

	}

	public LocalDate registerReturn(LocalDate date) throws InvalidLoanException {
		if (date == null || date.isBefore(LoanDate)) {
			throw new InvalidLoanException("date cant be null or before the loan date");
		}
		return date;
	}

	public int calculateDelayDays() {
		LocalDate end;
		if (actualReturnDate != null) {
			end = actualReturnDate;
		} else
			end = LocalDate.now();

		int diff = end.getDayOfYear() - dueDate.getDayOfYear();

		if (diff > 0) {
			return diff;
		} else {
			return 0;
		}
	}

	public boolean isOverdue() {
		if (dueDate.isBefore(LocalDate.now())) {
			return true;
		} else
			return false;
	}
	
	

	@Override
	public String toString() {
		return "Loan [bookCode=" + bookCode + ", bookTitle=" + bookTitle + ", member=" + member + ", LoanDate="
				+ LoanDate + ", dueDate=" + dueDate + ", actualReturnDate=" + actualReturnDate + "]";
	}

}