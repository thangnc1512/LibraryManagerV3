package thangnc.librarymanagerv3.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import thangnc.librarymanagerv3.Constant;
import thangnc.librarymanagerv3.database.DatabaseHelper;
import thangnc.librarymanagerv3.model.Category;

public class CategoryDAO implements Constant {

    private DatabaseHelper databaseHelper;

    public CategoryDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public long insertCategory(Category category){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CATE_COLUMN_ID, category.id);
        contentValues.put(CATE_COLUMN_NAME,category.name);
        contentValues.put(CATE_COLUMN_DES, category.desc);
        contentValues.put(CATE_COLUMN_POSITION, category.postion);

        //tao cau lenh insert
        long result = sqLiteDatabase.insert(TABLE_CATEGORY, null, contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public long updateCategory(Category category){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CATE_COLUMN_ID, category.id);
        contentValues.put(CATE_COLUMN_NAME,category.name);
        contentValues.put(CATE_COLUMN_DES, category.desc);

        long result = sqLiteDatabase.update(TABLE_CATEGORY, contentValues,
                CATE_COLUMN_ID + "=?",
                new String[]{category.id});
        sqLiteDatabase.close();
        return result;
    }

    public long deleteCategory(String cateID){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        long result = sqLiteDatabase.delete(TABLE_CATEGORY, CATE_COLUMN_ID + "=?",
                new String[]{cateID});
        sqLiteDatabase.close();
        return result;
    }

    public List<Category> getAllCategory(){
        List<Category> categoryList = new ArrayList<>();
        String sqlSelect = "SELECT * FROM "+TABLE_CATEGORY;
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery(sqlSelect, null);
        if (cursor.moveToFirst()){
            do {
                String id = cursor.getString(cursor.getColumnIndex(CATE_COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(CATE_COLUMN_NAME));
                String desc = cursor.getString(cursor.getColumnIndex(CATE_COLUMN_DES));
                String position = cursor.getString(cursor.getColumnIndex(CATE_COLUMN_POSITION));
                Category category = null;
                category = new Category(id, name, desc, position);
                categoryList.add(category);
            }while (cursor.moveToNext());
        }
        database.close();
        return categoryList;
    }

    public Category getCategoryByID(String cateID){
        Category category = null;
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_CATEGORY,
                new String[]{CATE_COLUMN_ID, CATE_COLUMN_NAME, CATE_COLUMN_DES, CATE_COLUMN_POSITION},
                CATE_COLUMN_ID +"=?",
                new String[]{cateID}, null, null, null);
        if (cursor != null && cursor.moveToFirst()){
            String id = cursor.getString(cursor.getColumnIndex(CATE_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(CATE_COLUMN_NAME));
            String desc = cursor.getString(cursor.getColumnIndex(CATE_COLUMN_DES));
            String position = cursor.getString(cursor.getColumnIndex(CATE_COLUMN_POSITION));
            category = new Category();
            category.id = id;
            category.name = name;
            category.desc = desc;
            category.postion = position;
        }
        return category;
    }
}
