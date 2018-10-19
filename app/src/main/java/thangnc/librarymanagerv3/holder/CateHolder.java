package thangnc.librarymanagerv3.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import thangnc.librarymanagerv3.R;

public class CateHolder extends RecyclerView.ViewHolder {

    public final ImageView imgCate;
    public final TextView tvCateName;
    public final TextView tvCateDesc;
    public final ImageView imgEditCate;
    public final ImageView imgDeleteCate;
    public CateHolder(@NonNull View itemView) {
        super(itemView);
        imgCate = itemView.findViewById(R.id.imgCate);
        tvCateName = itemView.findViewById(R.id.tvCateName);
        tvCateDesc = itemView.findViewById(R.id.tvCateDesc);
        imgEditCate = itemView.findViewById(R.id.imgEditCate);
        imgDeleteCate = itemView.findViewById(R.id.imgDeleteCate);
    }
}
