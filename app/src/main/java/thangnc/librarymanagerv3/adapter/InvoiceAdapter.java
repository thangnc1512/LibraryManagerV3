package thangnc.librarymanagerv3.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import thangnc.librarymanagerv3.R;
import thangnc.librarymanagerv3.database.DatabaseHelper;
import thangnc.librarymanagerv3.holder.InvoiceHolder;
import thangnc.librarymanagerv3.model.Invoice;
import thangnc.librarymanagerv3.sqlitedao.CategoryDAO;
import thangnc.librarymanagerv3.sqlitedao.InvoiceDAO;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceHolder> {

    public Context context;
    public List<Invoice> invoiceList;
    public DatabaseHelper databaseHelper;
    public InvoiceDAO invoiceDAO;

    public InvoiceAdapter(Context context, List<Invoice> invoiceList, InvoiceDAO invoiceDAO) {
        this.context = context;
        this.invoiceList = invoiceList;
        this.invoiceDAO = invoiceDAO;
    }

    @NonNull
    @Override
    public InvoiceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,  final int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_invoice,viewGroup, false );
        databaseHelper = new DatabaseHelper(context);
        invoiceDAO = new InvoiceDAO(databaseHelper);
        return new InvoiceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceHolder invoiceHolder, final int i) {
        final Invoice invoice = invoiceList.get(i);
        invoiceHolder.tvInvoiceID.setText(invoice.toString());
        invoiceHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete Invoice");
                builder.setMessage("Do you want delete this invoice?");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        invoiceList.remove(i);
                        long result = invoiceDAO.deleteInvoice(invoiceList.get(i).invoiceID);
                        if(result<0){
                            Toast.makeText(context, "Nothing to delete!!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Delete Successfully!", Toast.LENGTH_SHORT).show();
                        }
                        notifyDataSetChanged();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return invoiceList.size();
    }
}
