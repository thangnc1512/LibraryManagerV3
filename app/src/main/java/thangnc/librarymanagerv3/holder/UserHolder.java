package thangnc.librarymanagerv3.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import thangnc.librarymanagerv3.R;

public class UserHolder extends RecyclerView.ViewHolder {

    public final ImageView imgUsers;
    public final TextView tvName;
    public final TextView tvPhone;
    public final ImageView imgEditUser;
    public final ImageView imgDeleteUser;


    public UserHolder(@NonNull View itemView) {
        super(itemView);
        imgUsers = itemView.findViewById(R.id.imgUsers);
        tvName = itemView.findViewById(R.id.tvName);
        tvPhone = itemView.findViewById(R.id.tvPhone);
        imgEditUser = itemView.findViewById(R.id.imgEditUser);
        imgDeleteUser = itemView.findViewById(R.id.imgDeleteUser);
    }
}
