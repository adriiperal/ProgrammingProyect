package classes;

import java.time.LocalDate;

import exceptions.InvalidLoanException;

public class Loan {
	public static final int DAYS_TO_RETURN = 14;
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
		this.dueDate = loanDate.plusDays(DAYS_TO_RETURN);
		this.actualReturnDate = null;

	}

	public void registerReturn(LocalDate date) throws InvalidLoanException {
	    if (date == null || date.isBefore(LoanDate)) {
	        throw new InvalidLoanException("Date cannot be null or before the loan date.");
	    }
	    this.actualReturnDate = date;
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
	
	

	public String getBookCode() {
		return bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public User getMember() {
		return member;
	}

	public void setMember(User member) {
		this.member = member;
	}

	public LocalDate getLoanDate() {
		return LoanDate;
	}

	public void setLoanDate(LocalDate loanDate) {
		LoanDate = loanDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getActualReturnDate() {
		return actualReturnDate;
	}

	public void setActualReturnDate(LocalDate actualReturnDate) {
		this.actualReturnDate = actualReturnDate;
	}

	@Override
	public String toString() {
		return "Loan [bookCode=" + bookCode + ", bookTitle=" + bookTitle + ", member=" + member + ", LoanDate="
				+ LoanDate + ", dueDate=" + dueDate + ", actualReturnDate=" + actualReturnDate + "]";
	}

}