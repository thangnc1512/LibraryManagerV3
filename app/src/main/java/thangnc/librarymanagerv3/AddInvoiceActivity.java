package thangnc.librarymanagerv3;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import thangnc.librarymanagerv3.database.DatabaseHelper;
import thangnc.librarymanagerv3.model.Category;
import thangnc.librarymanagerv3.model.Invoice;
import thangnc.librarymanagerv3.sqlitedao.InvoiceDAO;


public class AddInvoiceActivity extends AppCompatActivity {

    private EditText edtInvoiceID;
    private TextView tvDatePicker;
    private Button btnDatePicker;
    private Button btnCreateInvoice;
    private long datePicker = -1;
    private InvoiceDAO invoiceDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_invoice);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        edtInvoiceID = (EditText) findViewById(R.id.edtInvoiceID);
        tvDatePicker = (TextView) findViewById(R.id.tvDatePicker);
        btnDatePicker = (Button) findViewById(R.id.btnDatePicker);
        btnCreateInvoice = (Button) findViewById(R.id.btnCreateInvoice);
        invoiceDAO = new InvoiceDAO(new DatabaseHelper(this));
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
        btnCreateInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String invoiceID = edtInvoiceID.getText().toString().trim();
                if (invoiceID.length()>7){
                    edtInvoiceID.setError(getString(R.string.notify_max_bill_id));
                    return;
                }
                if (datePicker<0){
                    Toast.makeText(AddInvoiceActivity.this, "Choose Date", Toast.LENGTH_SHORT).show();
                    return;
                }
                long result = invoiceDAO.insertInvoice(new Invoice(invoiceID, datePicker));
                if (result < 0){
                    Toast.makeText(AddInvoiceActivity.this, "Create not success", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(AddInvoiceActivity.this, "Create success", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void showDatePicker(){
        Calendar currentDate = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                long datePicked = 0;
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                datePicked = calendar.getTimeInMillis();
                String datePicked_ = new Date(datePicked).toString();
                tvDatePicker.setText(datePicked_);
            }
        },currentDate.get(Calendar.YEAR),
                currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
        }
}
