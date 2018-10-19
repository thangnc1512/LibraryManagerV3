package thangnc.librarymanagerv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {
    private ImageView imgIntro;
    private ImageView imgCategory;
    private ImageView imgBook;
    private ImageView imgInvoice;
    private ImageView imgDInvoice;
    private ImageView imgUser;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imgIntro = (ImageView) findViewById(R.id.imgIntro);
        imgCategory = (ImageView) findViewById(R.id.imgCategory);
        imgBook = (ImageView) findViewById(R.id.imgBook);
        imgInvoice = (ImageView) findViewById(R.id.imgInvoice);
        imgDInvoice = (ImageView) findViewById(R.id.imgDInvoice);
        imgUser = findViewById(R.id.imgUser);


        imgIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, IntroActivity.class);
                startActivity(intent);
            }
        });
        imgCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });
        imgBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BookActivity.class);
                startActivity(intent);
            }
        });
        imgInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, InvoiceActivity.class);
                startActivity(intent);
            }
        });
        imgDInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DinvoiceActivity.class);
                startActivity(intent);
            }
        });
        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });
    }
}
