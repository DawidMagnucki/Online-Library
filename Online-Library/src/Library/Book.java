package Library;

public class Book {

    String title;
    String author;
    boolean isAvailable;

    public Book(String title, String author, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.isAvailable = isAvailable;

    }

    public static Book parseBookInfo (String line){
        String[] parts = line.split("\"");
        if (parts.length == 2){
            String title = parts[0].trim();
            String author = parts[1].trim();
            return new Book(title, author);
        }
        return null;
    }


    public Book(String title, String author) {
        this.title = title;
        this.author = author;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
