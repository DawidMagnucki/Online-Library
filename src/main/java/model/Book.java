package model;

import java.util.Objects;

public class Book {

    String title;
    String author;
    BookStatus bookStatus;


    public Book(String title, String author, BookStatus bookStatus) {
        this.title = title;
        this.author = author;
        this.bookStatus = bookStatus;
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.bookStatus = BookStatus.AVAILABLE;
    }

    public Book (String line) {
        String[] parts = line.split("\" ");
        parts[0] = parts[0].replace("\"", "");
        if (parts.length == 2) {
            this.title = parts[0].trim();
            this.author = parts[1].trim();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookStatus getBookStatus(){
        return bookStatus;
    }

    @Override
    public String toString() {
        return title + "\" " + author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!Objects.equals(title, book.title)) return false;
        return Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }
}
