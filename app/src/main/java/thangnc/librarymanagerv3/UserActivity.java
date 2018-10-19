package thangnc.librarymanagerv3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import thangnc.librarymanagerv3.adapter.UserAdapter;
import thangnc.librarymanagerv3.database.DatabaseHelper;
import thangnc.librarymanagerv3.model.User;
import thangnc.librarymanagerv3.sqlitedao.UserDAO;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class UserActivity extends AppCompatActivity implements Constant {

    private RecyclerView lvlistUser;
    private UserAdapter adapterUser;
    private LinearLayoutManager linearLayoutManager;
    private DatabaseHelper databaseHelper;
    private UserDAO userDAO;
    private List<User> userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        lvlistUser = findViewById(R.id.lvListUser);
        databaseHelper = new DatabaseHelper(this);
        userDAO = new UserDAO(databaseHelper);
        userList = new ArrayList<>();
        userList = userDAO.getAllUsers();
        adapterUser = new UserAdapter(this, userList, userDAO);
        lvlistUser.setAdapter(adapterUser);
        linearLayoutManager = new LinearLayoutManager(this);
        lvlistUser.setLayoutManager(linearLayoutManager);

        for (int i = 0; i<20; i++){
            User user = new User(

                    "thangnc",
                    "abc123",
                    "Nguyen Thang",
                    "0909909990");
            userDAO.insertUser(user);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_user, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_add_user:
                Intent intent = new Intent(this, AddUserActivity.class);
                startActivityForResult(intent, ADD_USER);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
