package Library;

public class Borrower {

    String name;
    String ID;

    public Borrower(String name, String ID) {
        this.name = name;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void borrowBook(Book book) { // this method borrows a book from the library

    }

    public void returnBook(Book book) { // this method returns a book to the library

    }

    public void getBorrowedBooks() { // this method returns a list of borrowed books

    }

}
