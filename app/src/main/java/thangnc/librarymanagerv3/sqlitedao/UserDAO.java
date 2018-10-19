package thangnc.librarymanagerv3.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import thangnc.librarymanagerv3.Constant;
import thangnc.librarymanagerv3.database.DatabaseHelper;
import thangnc.librarymanagerv3.model.User;

public class UserDAO implements Constant {
    DatabaseHelper databaseHelper;

    public UserDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }



    public long insertUser(User user){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, user.getUsername());
        contentValues.put(COLUMN_PASSWORD, user.getPassword());
        contentValues.put(COLUMN_NAME, user.getName());
        contentValues.put(COLUMN_PHONE_NUMBER, user.getPhone());

        long result = sqLiteDatabase.insert(TABLE_USER, null, contentValues);
        sqLiteDatabase.close();
        return result;
    }
//    public void insertUser(User user){
//        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COLUMN_USERNAME, user.getUsername());
//        contentValues.put(COLUMN_PASSWORD, user.getPassword());
//        contentValues.put(COLUMN_NAME, user.getName());
//        contentValues.put(COLUMN_PHONE_NUMBER, user.getPhone());
//
//        sqLiteDatabase.insert(TABLE_USER, null,contentValues);
//        sqLiteDatabase.close();
//    }


    public User getUser(String username){
        User user = null;
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Cursor cursor = database.query(
                TABLE_USER, new String[]{COLUMN_USERNAME, COLUMN_PASSWORD, COLUMN_NAME, COLUMN_PHONE_NUMBER},
                COLUMN_USERNAME + " = ?", new String[]{username},
                null, null, null);
        if (cursor != null && cursor.moveToFirst()){
            String user_name = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER));
            user = new User(user_name, password, name, phone);
//            user.setUsername(user_name);
//            user.setPassword(password);
//            user.setName(name);
//            user.setPhone(phone);
        }
        return user;
    }

    public List<User> getAllUsers(){
        List<User> userList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        String SELECT_ALL_USERS = "SELECT * FROM "+TABLE_USER;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_USERS,null);
        if (cursor.moveToFirst()){
            do {
                String user_name = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
                String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER));
                User users = new User();
                users.setUsername(user_name);
                users.setPassword(password);
                users.setName(name);
                users.setPhone(phone);
                userList.add(users);

            }while (cursor.moveToNext());
            cursor.close();
            sqLiteDatabase.close();
        }
        return userList;
    }

    public void deleteUser(String username){
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.delete(TABLE_USER, COLUMN_USERNAME + "= ?",
                new String[]{String.valueOf(username)});
        database.close();
    }

    public long updateUser(User user){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, user.getUsername());
        contentValues.put(COLUMN_PASSWORD, user.getPassword());
        contentValues.put(COLUMN_NAME, user.getName());
        contentValues.put(COLUMN_PHONE_NUMBER, user.getPhone());
        return db.update(TABLE_USER, contentValues, COLUMN_USERNAME +"=?",
                new String[]{String.valueOf(user.getUsername())});

    }
}
