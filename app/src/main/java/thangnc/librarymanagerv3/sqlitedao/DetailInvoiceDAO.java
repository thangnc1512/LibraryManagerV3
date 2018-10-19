package thangnc.librarymanagerv3.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import thangnc.librarymanagerv3.Constant;
import thangnc.librarymanagerv3.database.DatabaseHelper;
import thangnc.librarymanagerv3.model.DetailInvoice;

public class DetailInvoiceDAO implements Constant {
    DatabaseHelper databaseHelper;

    public DetailInvoiceDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public long insertDI(DetailInvoice detailInvoice){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DI_COLUMN_DIID, detailInvoice.getDetailID());
        contentValues.put(DI_COLUMN_IID, detailInvoice.getInvoiceID());
        contentValues.put(DI_COLUMN_BOOK_ID, detailInvoice.getDetailBookID());
        contentValues.put(DI_COLUMN_QUALITY, detailInvoice.getDetailquality());
        long result = sqLiteDatabase.insert(TABLE_DETAIL_INVOICE, null, contentValues);
        return result;

    }

    public long updateDI(DetailInvoice detailInvoice){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DI_COLUMN_DIID, detailInvoice.getDetailID());
        contentValues.put(DI_COLUMN_IID, detailInvoice.getInvoiceID());
        contentValues.put(DI_COLUMN_BOOK_ID, detailInvoice.getDetailBookID());
        contentValues.put(DI_COLUMN_QUALITY, detailInvoice.getDetailquality());

        long result = sqLiteDatabase.update(TABLE_DETAIL_INVOICE, contentValues, DI_COLUMN_DIID+"=?",
                new String[]{String.valueOf(detailInvoice.getDetailID())});
        sqLiteDatabase.close();
        return result;
    }

    public long deleteDI(String detailInvoiceID){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        long result = sqLiteDatabase.delete(TABLE_DETAIL_INVOICE, DI_COLUMN_DIID+"=?",
                new String[]{String.valueOf(detailInvoiceID)});
        sqLiteDatabase.close();
        return result;

    }

    public DetailInvoice getDetailByID(String diID){
        DetailInvoice detailInvoice = null;
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_DETAIL_INVOICE, new String[]{DI_COLUMN_DIID, DI_COLUMN_IID, DI_COLUMN_BOOK_ID, DI_COLUMN_QUALITY},
                DI_COLUMN_DIID+"=?",new String[]{diID}, null, null, null);
        if (cursor != null && cursor.moveToFirst()){
            String dIID = cursor.getString(cursor.getColumnIndex(DI_COLUMN_DIID));
            String iID = cursor.getString(cursor.getColumnIndex(DI_COLUMN_IID));
            String dIBookID = cursor.getString(cursor.getColumnIndex(DI_COLUMN_BOOK_ID));
            int dIQuality = cursor.getInt(cursor.getColumnIndex(DI_COLUMN_QUALITY));
            detailInvoice = new DetailInvoice();
            detailInvoice.setDetailID(dIID);
            detailInvoice.setInvoiceID(iID);
            detailInvoice.setDetailBookID(dIBookID);
            detailInvoice.setDetailquality(dIQuality);
        }
        return detailInvoice;
    }

    public List<DetailInvoice> getAllDetailInvoice(String diID){
        List<DetailInvoice> detailInvoiceList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        String SELECT_ALL_DETAIL_INVOICE_BYID = "SELECT * FROM "+TABLE_DETAIL_INVOICE + " WHERE "+DI_COLUMN_IID+" = "+"'"+diID+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_DETAIL_INVOICE_BYID, null);
        if (cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                String dIID = cursor.getString(cursor.getColumnIndex(DI_COLUMN_DIID));
                String iID = cursor.getString(cursor.getColumnIndex(DI_COLUMN_IID));
                String dIBookID = cursor.getString(cursor.getColumnIndex(DI_COLUMN_BOOK_ID));
                int dIQuality = cursor.getInt(cursor.getColumnIndex(DI_COLUMN_QUALITY));
                DetailInvoice detailInvoice = new DetailInvoice();
                detailInvoice.setDetailID(dIID);
                detailInvoice.setInvoiceID(iID);
                detailInvoice.setDetailBookID(dIBookID);
                detailInvoice.setDetailquality(dIQuality);

                detailInvoiceList.add(detailInvoice);
            }while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return detailInvoiceList;

    }
}
