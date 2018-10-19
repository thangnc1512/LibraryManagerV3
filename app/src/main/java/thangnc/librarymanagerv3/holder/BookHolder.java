package thangnc.librarymanagerv3.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import thangnc.librarymanagerv3.R;

public class BookHolder extends RecyclerView.ViewHolder {

    public final ImageView imgBook;
    public final TextView tvNameBook;
    public final TextView tvPriceBook;
    public final ImageView imgEditBook;
    public final ImageView imgDeleteBook;

    public BookHolder(@NonNull View itemView) {
        super(itemView);
        imgBook = itemView.findViewById(R.id.imgBook);
        imgEditBook = itemView.findViewById(R.id.imgEditBook);
        imgDeleteBook = itemView.findViewById(R.id.imgDeleteBook);
        tvNameBook = itemView.findViewById(R.id.tvBookName);
        tvPriceBook = itemView.findViewById(R.id.tvBookPrice);
    }
}
