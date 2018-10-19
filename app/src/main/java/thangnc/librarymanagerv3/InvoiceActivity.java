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
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;
import java.util.Random;

import thangnc.librarymanagerv3.adapter.InvoiceAdapter;
import thangnc.librarymanagerv3.database.DatabaseHelper;
import thangnc.librarymanagerv3.model.Invoice;
import thangnc.librarymanagerv3.sqlitedao.InvoiceDAO;


public class InvoiceActivity extends AppCompatActivity {
    private RecyclerView lvListInvoice;
    private InvoiceAdapter adapterInvoice;
    private LinearLayoutManager linearLayoutManager;
    private List<Invoice> invoices;

    private InvoiceDAO invoiceDAO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_invoice);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        invoiceDAO = new InvoiceDAO(new DatabaseHelper(this));
        lvListInvoice = findViewById(R.id.lvListInvoice);

        invoices = invoiceDAO.getAllInvoice();

        for (int i = 0; i<10; i++){
            Invoice invoice = new Invoice(new Random().nextInt()+"",
                    System.currentTimeMillis());
            invoices.add(invoice);
        }
        linearLayoutManager = new LinearLayoutManager(this);
        adapterInvoice = new InvoiceAdapter(this, invoices, invoiceDAO);
        lvListInvoice.setAdapter(adapterInvoice);
        lvListInvoice.setLayoutManager(linearLayoutManager);

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
                Intent intent = new Intent(this, AddInvoiceActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
