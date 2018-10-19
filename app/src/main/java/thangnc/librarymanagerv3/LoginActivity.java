package thangnc.librarymanagerv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import thangnc.librarymanagerv3.database.DatabaseHelper;
import thangnc.librarymanagerv3.model.User;
import thangnc.librarymanagerv3.sqlitedao.UserDAO;

public class LoginActivity extends AppCompatActivity {
    private EditText edUserName;
    private EditText edPassWord;
    private CheckBox chkRemember;
    private Button btnLogin;
    private String userName, password;
    private UserDAO userDAO;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseHelper = new DatabaseHelper(this);
        userDAO = new UserDAO(databaseHelper);
        edUserName = (EditText) findViewById(R.id.edUsername);
        edPassWord = (EditText) findViewById(R.id.edPass);
        chkRemember = (CheckBox) findViewById(R.id.chkRemember);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        chkRemember = findViewById(R.id.chkRemember);
        userDAO = new UserDAO(databaseHelper);
        List<User> listAllUser = new ArrayList<>();
        listAllUser.addAll(userDAO.getAllUsers());
        Log.e("sizeUser", listAllUser.size()+"");
//        userDAO.insertUser(new User("admin", "abc123","Thang Nguyen","0981517098"));

    }


    public void checkLogin(View view) {
        userName = edUserName.getText().toString().trim();
        password = edPassWord.getText().toString().trim();


        if (userName.isEmpty() || password.isEmpty()) {
            if (userName.isEmpty())
                edUserName.setError(getString(R.string.notify_empty_user));

            if (password.isEmpty())
                edPassWord.setError(getString(R.string.notify_empty_pass));
        } else {
            List<User> listUser = new ArrayList<>();
            listUser.addAll(userDAO.getAllUsers());
            boolean check = false;
            for(int i=0; i<listUser.size(); i++){
                if(listUser.get(i).getUsername().equalsIgnoreCase(userName) && listUser.get(i).getPassword().equalsIgnoreCase(password)){
                    check = true;
                }else{
                    check=false;
                }
            }
            if(check==true){
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Failed!!!", Toast.LENGTH_SHORT).show();
            }
//            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
//
//
//
////            String user_name = edUserName.getText().toString().trim();
//
////
//            User user = userDAO.getUser(userName);
//
//            //neu user1 tra ve null : user chua ton tai thi inser user
//            if (user != null) {
//
//                if (password.equalsIgnoreCase(user.getPassword())) {
//                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                    startActivity(intent);
////                    finish();
//                } else {
//                    Toast.makeText(LoginActivity.this, "Khong thanh cong!!", Toast.LENGTH_SHORT).show();
//                }
//            } else {
////                    neu != null; thi ko duoc phep insert user voi user name = admin
//                Toast.makeText(LoginActivity.this, "Khong thanh cong!", Toast.LENGTH_SHORT).show();
//            }
        }
    }
}
