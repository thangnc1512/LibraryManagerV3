package thangnc.librarymanagerv3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import thangnc.librarymanagerv3.adapter.CategoryAdapter;
import thangnc.librarymanagerv3.database.DatabaseHelper;
import thangnc.librarymanagerv3.model.Category;
import thangnc.librarymanagerv3.sqlitedao.CategoryDAO;


public class CategoryActivity extends AppCompatActivity implements Constant {

    private RecyclerView lvCategory;
    private LinearLayoutManager linearLayoutManager;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;

    private DatabaseHelper databaseHelper;
    private CategoryDAO categoryDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initViews();
        initData();

    }

    private void initData() {
        categoryList = new ArrayList<>();
        categoryList.clear();
        databaseHelper = new DatabaseHelper(this);
        categoryDAO = new CategoryDAO(databaseHelper);


        for (int i = 0; i<20; i++){
            Category category = new Category();
            category.id = new Random().nextInt() + "";
            category.name = System.currentTimeMillis()+"";
            category.postion = i + "";
            category.desc = "ABC ABC";
            categoryList.add(category);
        }
        categoryList = categoryDAO.getAllCategory();
        categoryAdapter = new CategoryAdapter(this, categoryList, categoryDAO);
        lvCategory.setAdapter(categoryAdapter);
    }

    private void initViews() {
        lvCategory = findViewById(R.id.recyclerViewCate);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        lvCategory.setLayoutManager(linearLayoutManager);
        lvCategory.setHasFixedSize(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cate, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_cate:
                Intent intent = new Intent(this, AddCateActivity.class);
                startActivityForResult(intent, ADD_CATE);
                return false;
            case R.id.action_search_cate:
                showDialogSearchTheLoai();
                return false;

        }
        return super.onOptionsItemSelected(item);
    }

    public void showDialogSearchTheLoai() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_searchcate, null);
        dialog.setView(dialogView);
        final Dialog dialog1 = dialog.show();

        Button tim = dialogView.findViewById(R.id.btnTimTheLoai);
        Button huy = dialogView.findViewById(R.id.btnHuyTimTheLoai);
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
        tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
