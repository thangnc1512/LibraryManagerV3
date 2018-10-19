package thangnc.librarymanagerv3.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import thangnc.librarymanagerv3.R;

public class DetailInvoiceHolder extends RecyclerView.ViewHolder {
    public final ImageView imgDI;
    public final TextView tvDIName;
    public final TextView tvDIQuality;
    public final ImageView imgDIDelete;

    public DetailInvoiceHolder(@NonNull View itemView) {
        super(itemView);
        imgDI = itemView.findViewById(R.id.imgDI);
        tvDIName = itemView.findViewById(R.id.tvDIName);
        tvDIQuality = itemView.findViewById(R.id.tvDIQuality);
        imgDIDelete = imgDI.findViewById(R.id.imgDIDelete);
    }
}
