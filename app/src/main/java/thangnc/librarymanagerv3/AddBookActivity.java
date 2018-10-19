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
import thangnc.librarymanagerv3.model.Book;
import thangnc.librarymanagerv3.sqlitedao.BookDAO;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class AddBookActivity extends AppCompatActivity {
    private EditText edtBookID;
    private EditText edtCateID;
    private EditText edtBookName;
    private EditText edtBookPublisher;
    private EditText edtBookPrice;
    private EditText edtBookQuality;
    private EditText edtBookAuthor;
    private Button btnAddBook;
    private Button btnCancelBook;
    private DatabaseHelper databaseHelper;
    private BookDAO bookDAO;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_book);
        databaseHelper = new DatabaseHelper(this);
        bookDAO = new BookDAO(databaseHelper);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        edtBookID = (EditText) findViewById(R.id.edtBookID);
        edtCateID = (EditText) findViewById(R.id.edtCateID);
        edtBookName = (EditText) findViewById(R.id.edtBookName);
        edtBookPublisher = (EditText) findViewById(R.id.edtBookPublisher);
        edtBookAuthor = findViewById(R.id.edtBookAuthor);
        edtBookPrice = (EditText) findViewById(R.id.edtBookPrice);
        edtBookQuality = (EditText) findViewById(R.id.edtBookQuality);
        btnAddBook = (Button) findViewById(R.id.btnAddBook);
        btnCancelBook = (Button) findViewById(R.id.btnCancelBook);


        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookId = edtBookID.getText().toString().trim();
                String cateId = edtCateID.getText().toString().trim();
                String name = edtBookName.getText().toString().trim();
                String author = edtBookAuthor.getText().toString().trim();
                String publisher = edtBookPublisher.getText().toString().trim();
                String quanlity = edtBookQuality.getText().toString().trim();
                String price = edtBookPrice.getText().toString().trim();
                Book book = new Book(bookId, cateId, name, author, publisher,quanlity, price);
                bookDAO.insertBook(book);
                Toast.makeText(AddBookActivity.this, "Add successfull", Toast.LENGTH_SHORT).show();
            }
        });
        btnCancelBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}
