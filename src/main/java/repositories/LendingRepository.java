package repositories;

import model.Book;
import model.Borrower;
import model.Lending;

import java.util.Date;
import java.util.List;

public interface LendingRepository {

    //TODO: public unnecessary
    public List<Lending> readLendingList();

    public List<Lending> readLendingListByBorrower(Borrower borrower);

    void addBookToLendingList(Book book, Borrower borrower, Date date);

    void removeBookFromLendingList(Book book, Borrower borrower);

    void updateLendingDetails(Book book, Borrower borrower, Date newReturnDate);

}
