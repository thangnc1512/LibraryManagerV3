package thangnc.librarymanagerv3;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import thangnc.librarymanagerv3.adapter.BookAdapter;
import thangnc.librarymanagerv3.database.DatabaseHelper;
import thangnc.librarymanagerv3.model.Book;
import thangnc.librarymanagerv3.sqlitedao.BookDAO;


public class BookActivity extends AppCompatActivity implements Constant {

    private RecyclerView lvBook;
    private LinearLayoutManager linearLayoutManager;
    private BookAdapter bookAdapter;
    private List<Book> bookList;

    private DatabaseHelper databaseHelper;
    private BookDAO bookDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initView();
        initData();

    }

    private void initData() {
        bookList = new ArrayList<>();
        bookList.clear();
        databaseHelper = new DatabaseHelper(this);
        bookDAO = new BookDAO(databaseHelper);
        for (int i = 0; i<20; i++){
            Book book = new Book();
            book.bookId = new Random().nextInt()+"";
            book.cateID = new Random().nextInt()+"";
            book.bookName = "ABC";
            book.author = "ABC123";
            book.publisher = "ABC";
            book.price = "10000";
            book.quality = "10";
            bookList.add(book);

        }

        bookList = bookDAO.getAllBook();

        bookAdapter = new BookAdapter(this, bookList, bookDAO);
        lvBook.setAdapter(bookAdapter);
    }

    private void initView() {
        lvBook = findViewById(R.id.recyclerViewBook);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        lvBook.setLayoutManager(linearLayoutManager);
        lvBook.setHasFixedSize(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cate,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_cate:
                Intent intent = new Intent(this, AddBookActivity.class);
                startActivityForResult(intent, ADD_BOOK);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
