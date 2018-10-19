package thangnc.librarymanagerv3.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import thangnc.librarymanagerv3.R;

public class InvoiceHolder extends RecyclerView.ViewHolder {

    public TextView tvInvoiceID;
    public ImageView imgDelete;
    public ImageView imgEdit;

    public InvoiceHolder(@NonNull View itemView) {
        super(itemView);
        tvInvoiceID = itemView.findViewById(R.id.tvInvoiceID);
        imgEdit = itemView.findViewById(R.id.imgEditInvoice);
        imgDelete = itemView.findViewById(R.id.imgDeleteInvoice);
        

    }
}
