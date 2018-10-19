package thangnc.librarymanagerv3.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import thangnc.librarymanagerv3.R;
import thangnc.librarymanagerv3.holder.DetailInvoiceHolder;
import thangnc.librarymanagerv3.model.DetailInvoice;
import thangnc.librarymanagerv3.sqlitedao.DetailInvoiceDAO;

public class DetailInvoiceAdapter extends RecyclerView.Adapter<DetailInvoiceHolder> {
    private Context context;
    private List<DetailInvoice> detailInvoiceList;
    private DetailInvoiceDAO dIDAO;

    public DetailInvoiceAdapter(Context context, List<DetailInvoice> detailInvoiceList, DetailInvoiceDAO dIDAO) {
        this.context = context;
        this.detailInvoiceList = detailInvoiceList;
        this.dIDAO = dIDAO;
    }

    @NonNull
    @Override
    public DetailInvoiceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detail_invoice, viewGroup, false);
        return new DetailInvoiceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailInvoiceHolder detailInvoiceHolder, int i) {
        final DetailInvoice detailInvoice = detailInvoiceList.get(i);
        detailInvoiceHolder.tvDIName.setText(detailInvoice.DetailID);
        detailInvoiceHolder.tvDIQuality.setText(detailInvoice.Detailquality);
        detailInvoiceHolder.imgDIDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDelete();
            }
        });

    }

    public void showDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xoá mục này không?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                System.exit(0);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    @Override
    public int getItemCount() {
        if (detailInvoiceList == null) return 0;
        return detailInvoiceList.size();
    }
}
