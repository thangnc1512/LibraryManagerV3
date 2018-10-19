package thangnc.librarymanagerv3.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import thangnc.librarymanagerv3.Constant;
import thangnc.librarymanagerv3.database.DatabaseHelper;
import thangnc.librarymanagerv3.model.Invoice;

public class InvoiceDAO implements Constant {
    private DatabaseHelper databaseHelper;

    public InvoiceDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public long insertInvoice(Invoice invoice){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(I_COLUMN_ID, invoice.invoiceID);
        contentValues.put(I_COLUMN_DATE, invoice.invoiceDate);

        long result = sqLiteDatabase.insert(TABLE_INVOICE, null, contentValues);

        sqLiteDatabase.close();
        return result;
    }

    public long updateInvoice(Invoice invoice){

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(I_COLUMN_DATE, invoice.invoiceDate);

        long result = sqLiteDatabase.update(TABLE_INVOICE, contentValues, I_COLUMN_ID +"=?",
                new String[]{invoice.invoiceID});

        sqLiteDatabase.close();
        return result;


    }
    public long deleteInvoice(String invoiceID){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        long result = sqLiteDatabase.delete(TABLE_INVOICE, I_COLUMN_ID + "=?",
                new String[]{invoiceID});
        sqLiteDatabase.close();
        return result;
    }

    public List<Invoice> getAllInvoice(){

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        List<Invoice> invoices = new ArrayList<>();
        String SELECT_ALL_INVOICE  = "SELECT * FROM "+TABLE_INVOICE;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_INVOICE, null);

        if (cursor != null && cursor.getCount() >0){
            cursor.moveToFirst();

            do {
                String id = cursor.getString(cursor.getColumnIndex(I_COLUMN_ID));
                long date = cursor.getLong(cursor.getColumnIndex(I_COLUMN_DATE));
                Invoice invoice = new Invoice(id, date);
                invoices.add(invoice);
            }while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return invoices;
    }

    public Invoice getInvoiceByID(String invoiceID){
            SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
            Invoice invoice = null;
            Cursor cursor = sqLiteDatabase.query(TABLE_INVOICE,
                    new String[]{I_COLUMN_ID, I_COLUMN_DATE}, I_COLUMN_ID+"=?",
                    new String[]{invoiceID}, null, null, null);

            if (cursor != null && cursor.getCount() >0){
                String id = cursor.getString(cursor.getColumnIndex(I_COLUMN_ID));
                long date = cursor.getLong(cursor.getColumnIndex(I_COLUMN_DATE));

                invoice.invoiceID = id;
                invoice.invoiceDate = date;
                invoice = new Invoice(id, date);
            }
            return invoice;

    }

}
