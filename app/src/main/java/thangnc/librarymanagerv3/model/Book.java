package thangnc.librarymanagerv3.model;

public class Book {


    public String bookId;
    public String cateID;
    public String bookName;
    public String author;
    public String publisher;
    public String price;
    public String quality;

    public Book(){

    }

    public Book(String bookId, String cateID, String bookName, String author, String publisher, String price, String quality) {
        this.bookId = bookId;
        this.cateID = cateID;
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.quality = quality;
    }


    public Book(String bookId, String cateId, String name, String author, String publisher, String price) {
    }

    public Book(String id, String name, String author, String price) {

    }
}
