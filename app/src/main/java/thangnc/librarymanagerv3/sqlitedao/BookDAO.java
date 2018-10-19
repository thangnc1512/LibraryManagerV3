package thangnc.librarymanagerv3.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import thangnc.librarymanagerv3.Constant;
import thangnc.librarymanagerv3.database.DatabaseHelper;
import thangnc.librarymanagerv3.model.Book;

public class BookDAO implements Constant {

    private DatabaseHelper databaseHelper;

    public BookDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public long insertBook(Book book){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOK_COLUMN_ID, book.bookId);
        contentValues.put(BOOK_COLUMN_CATEID, book.cateID);
        contentValues.put(BOOK_COLUMN_NAME, book.bookName);
        contentValues.put(BOOK_COLUMN_AUTHOR, book.author);
        contentValues.put(BOOK_COLUMN_PUBLISHER, book.publisher);
        contentValues.put(BOOK_COLUMN_PRICE, book.price);
        contentValues.put(BOOK_COLUMN_QUALITY, book.quality);

        long result = sqLiteDatabase.insert(TABLE_BOOK, null,contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public long updateBook(Book book){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOK_COLUMN_ID, book.bookId);
        contentValues.put(BOOK_COLUMN_CATEID, book.cateID);
        contentValues.put(BOOK_COLUMN_NAME, book.bookName);
        contentValues.put(BOOK_COLUMN_AUTHOR, book.author);
        contentValues.put(BOOK_COLUMN_PUBLISHER, book.publisher);
        contentValues.put(BOOK_COLUMN_PRICE, book.price);
        contentValues.put(BOOK_COLUMN_QUALITY, book.quality);

        long result = sqLiteDatabase.update(TABLE_BOOK, contentValues, BOOK_COLUMN_ID + "=?",
                new String[]{String.valueOf(book.bookId)});
        sqLiteDatabase.close();
        return result;
    }

    public long deleteBook(String bookID){
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        long result = database.delete(TABLE_BOOK, BOOK_COLUMN_ID + "=?",
                new String[]{String.valueOf(bookID)});
        database.close();
        return result;
    }

    public List<Book> getAllBook(){
        List<Book> bookList = new ArrayList<>();
        String SELECT_ALL_BOOK = "SELECT * FROM "+TABLE_BOOK;
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_BOOK, null);
        if (cursor.moveToFirst()){
            do {
                String bookID = cursor.getString(cursor.getColumnIndex(BOOK_COLUMN_ID));
                String cateID = cursor.getString(cursor.getColumnIndex(BOOK_COLUMN_CATEID));
                String name = cursor.getString(cursor.getColumnIndex(BOOK_COLUMN_NAME));
                String author = cursor.getString(cursor.getColumnIndex(BOOK_COLUMN_AUTHOR));
                String publisher = cursor.getString(cursor.getColumnIndex(BOOK_COLUMN_PUBLISHER));
                String price = cursor.getString(cursor.getColumnIndex(BOOK_COLUMN_PRICE));
                String quality = cursor.getString(cursor.getColumnIndex(BOOK_COLUMN_QUALITY));
                Book book = null;
                book = new Book(bookID, cateID, name, author, publisher, price, quality);
                bookList.add(book);
            }while (cursor.moveToNext());

        }
        sqLiteDatabase.close();
        return bookList;
    }

    public Book getBookByID(String bookID){
        Book book = null;
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_BOOK,
                new String[]{BOOK_COLUMN_ID, BOOK_COLUMN_CATEID, BOOK_COLUMN_NAME, BOOK_COLUMN_AUTHOR,
                        BOOK_COLUMN_PUBLISHER, BOOK_COLUMN_PRICE, BOOK_COLUMN_QUALITY},BOOK_COLUMN_ID + "=?",
                new String[]{bookID}, null, null, null);
        if (cursor != null && cursor.moveToFirst()){
            String ID = cursor.getString(cursor.getColumnIndex(BOOK_COLUMN_ID));
            String cateID = cursor.getString(cursor.getColumnIndex(BOOK_COLUMN_CATEID));
            String name = cursor.getString(cursor.getColumnIndex(BOOK_COLUMN_NAME));
            String author = cursor.getString(cursor.getColumnIndex(BOOK_COLUMN_AUTHOR));
            String publisher = cursor.getString(cursor.getColumnIndex(BOOK_COLUMN_PUBLISHER));
            String price = cursor.getString(cursor.getColumnIndex(BOOK_COLUMN_PRICE));
            String quality = cursor.getString(cursor.getColumnIndex(BOOK_COLUMN_QUALITY));
            book = new Book();
            book.bookId = ID;
            book.cateID = cateID;
            book.bookName = name;
            book.author = author;
            book.price = price;
            book.quality = quality;
            book.publisher = publisher;
        }
        return book;
    }
}
