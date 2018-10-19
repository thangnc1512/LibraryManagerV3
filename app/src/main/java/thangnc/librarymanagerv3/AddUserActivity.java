package thangnc.librarymanagerv3;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import thangnc.librarymanagerv3.database.DatabaseHelper;
import thangnc.librarymanagerv3.model.User;
import thangnc.librarymanagerv3.sqlitedao.UserDAO;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class AddUserActivity extends AppCompatActivity {

    private EditText edtUserName;
    private EditText edtPassword;
    private EditText edtConfirmPassword;
    private EditText edtName;
    private EditText edtPhone;
    private Button btnAddUser;
    private UserDAO userDAO;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_add_user);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        databaseHelper = new DatabaseHelper(this);
        userDAO =new UserDAO(databaseHelper);
        edtUserName = (EditText) findViewById(R.id.edtUserName);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtConfirmPassword = (EditText) findViewById(R.id.edtConfirmPassword);
        edtName = (EditText) findViewById(R.id.edtName);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        btnAddUser = (Button) findViewById(R.id.btnAddUser);
        

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setUsername(edtUserName.getText().toString().trim());
                user.setPassword(edtPassword.getText().toString().trim());
                user.setName(edtName.getText().toString().trim());
                user.setPhone(edtPhone.getText().toString().trim());
                userDAO.insertUser(user);
                Toast.makeText(AddUserActivity.this, "Add successful", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
