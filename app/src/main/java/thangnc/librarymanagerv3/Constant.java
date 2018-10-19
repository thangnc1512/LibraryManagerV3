package thangnc.librarymanagerv3;

public interface Constant {

    // result Code

    boolean isDEBUG = true;

    String D_DAY = "\"%Y-%m-%d\"";
    String M_MONTH = "\"%Y-%m\"";
    String Y_YEAR = "\"%Y\"";

    int ADD_USER = 8888;
    int ADD_CATE = 9999;
    int ADD_BOOK = 10000;

    // User Table

    // ----------------------------------------
    String TABLE_USER = "USER";

    String COLUMN_USERNAME = "Username";

    String COLUMN_PASSWORD = "Password";

    String COLUMN_NAME = "Name";

    String COLUMN_PHONE_NUMBER = "Phone_number";


    String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + " (" +

            COLUMN_USERNAME + " VARCHAR PRIMARY KEY," +
            COLUMN_PASSWORD + " VARCHAR," +
            COLUMN_NAME + " VARCHAR," +
            COLUMN_PHONE_NUMBER + " VARCHAR"

            + ")";

    //------CATEGORY-----
    String TABLE_CATEGORY = "CATEGORY";

    String CATE_COLUMN_ID = "CateID";

    String CATE_COLUMN_NAME = "CateName";

    String CATE_COLUMN_DES = "Des";

    String CATE_COLUMN_POSITION = "Position";

    String CREATE_TABLE_CATEGORY = "CREATE TABLE "+TABLE_CATEGORY+"("
            +CATE_COLUMN_ID+" VARCHAR(5) PRIMARY KEY NOT NULL,"
            +CATE_COLUMN_NAME+" VARCHAR(50),"
            +CATE_COLUMN_DES+" VARCHAR(250),"
            +CATE_COLUMN_POSITION+" INT)";

    //-----BOOK-----
    //
    String TABLE_BOOK = "BOOK";

    String BOOK_COLUMN_ID = "BookID";

    String BOOK_COLUMN_CATEID = "CateId";

    String BOOK_COLUMN_NAME = "BookName";

    String BOOK_COLUMN_AUTHOR = "Author";

    String BOOK_COLUMN_PUBLISHER = "Publisher";

    String BOOK_COLUMN_PRICE = "Price";

    String BOOK_COLUMN_QUALITY = "Quality";

    String CREATE_TABLE_BOOK = "CREATE TABLE "+TABLE_BOOK+"("
            + BOOK_COLUMN_ID +" VARCHAR(5) PRIMARY KEY NOT NULL,"
            + BOOK_COLUMN_CATEID +" VARCHAR(5),"
            + BOOK_COLUMN_NAME + " VARCHAR(50),"
            + BOOK_COLUMN_AUTHOR+ " VARCHAR(50),"
            + BOOK_COLUMN_PUBLISHER+" VARCHAR(50),"
            + BOOK_COLUMN_PRICE +" FLOAT,"
            + BOOK_COLUMN_QUALITY + " INT,"
            + " FOREIGN KEY ("+BOOK_COLUMN_CATEID+") REFERENCES "+TABLE_CATEGORY+"("+CATE_COLUMN_ID+"))";


    //-----Invoice---

    // CREATE TABLE INVOICE (MaHoaDon NCHAR(7) PRIMARY KEY NOT NULL, NgayMua LONG NOT NULL)
    String TABLE_INVOICE = "INVOICE";

    String I_COLUMN_ID = "MaHoaDon";

    String I_COLUMN_DATE = "NGayMua";

    String CREATE_TABLE_INVOICE = "CREATE TABLE "+TABLE_INVOICE +"("+
            "" + I_COLUMN_ID + " NCHAR(7) PRIMARY KEY NOT NULL,"+
            "" + I_COLUMN_DATE + " LONG NOT NULL"+
            ")";

    String TABLE_DETAIL_INVOICE = "DetailInvoice";
    String DI_COLUMN_DIID = "DetailInvoiceID";
    String DI_COLUMN_IID = "InvoiceID";
    String DI_COLUMN_BOOK_ID = "BookID";
    String DI_COLUMN_QUALITY = "Quality";

    String CREATE_TABLE_DETAIL_INVOICE = "CREATE TABLE "+TABLE_DETAIL_INVOICE+"("
            + DI_COLUMN_DIID + " INTERGER PRIMARY KEY AUTOINCREMENT,"
            + DI_COLUMN_DIID + " NCHAR(7) NOT NULL,"
            + DI_COLUMN_BOOK_ID + " NCHAR(5) NOT NULL,"
            + DI_COLUMN_QUALITY +" INT NOT NULL"
            +")";

}
