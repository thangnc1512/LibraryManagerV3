package thangnc.librarymanagerv3;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import thangnc.librarymanagerv3.model.Category;
import thangnc.librarymanagerv3.sqlitedao.CategoryDAO;


public class AddCateActivity extends AppCompatActivity {
    private EditText edtCateID;
    private EditText edtCateName;
    private EditText edtCateDesc;
    private EditText edtCatePosition;
    private Button btnAddCate;
    private Button btnCancelCate;
    private CategoryDAO categoryDAO;
    private DatabaseHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_cate);
        databaseHelper = new DatabaseHelper(this);
        categoryDAO = new CategoryDAO(databaseHelper);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        edtCateID = (EditText) findViewById(R.id.edtCateID);
        edtCateName = (EditText) findViewById(R.id.edtCateName);
        edtCateDesc = (EditText) findViewById(R.id.edtCateDesc);
        edtCatePosition = (EditText) findViewById(R.id.edtCatePosition);
        btnAddCate = (Button) findViewById(R.id.btnAddCate);
        btnCancelCate = (Button) findViewById(R.id.btnCancelCate);

        btnAddCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtCateID.getText().toString().trim();
                String name = edtCateName.getText().toString().trim();
                String desc = edtCateDesc.getText().toString().trim();
                String pos = edtCatePosition.getText().toString().trim();
                Category category = new Category(id, name, desc, pos);
                categoryDAO.insertCategory(category);
                Toast.makeText(AddCateActivity.this, "Add Success", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        btnCancelCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}
