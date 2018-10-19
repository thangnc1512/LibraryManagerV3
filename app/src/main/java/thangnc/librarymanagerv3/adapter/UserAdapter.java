package thangnc.librarymanagerv3.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import thangnc.librarymanagerv3.R;
import thangnc.librarymanagerv3.holder.UserHolder;
import thangnc.librarymanagerv3.model.User;
import thangnc.librarymanagerv3.sqlitedao.UserDAO;

public class UserAdapter extends RecyclerView.Adapter<UserHolder> {

    private Context context;
    private List<User> userList;
    private UserDAO userDAO;

    public UserAdapter(Context context, List<User> userList, UserDAO userDAO) {
        this.context = context;
        this.userList = userList;
        this.userDAO = userDAO;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, viewGroup, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder userHolder, final int position) {
        User user1 = userList.get(position);
        userHolder.tvName.setText(user1.getName());
        userHolder.tvPhone.setText(user1.getPhone()+"");

        userHolder.imgDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDAO.deleteUser(userList.get(position).getUsername());
                userList.remove(position);
                notifyDataSetChanged();
            }
        });
        userHolder.imgEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setTitle(userList.get(position).getName());
                dialog.setContentView(R.layout.dialog_change_user);
                EditText edtPass;
                EditText edtRePass;
                final EditText edtName;
                final EditText edtPhone;

                edtPass = dialog.findViewById(R.id.edtPassWord);
                edtRePass = dialog.findViewById(R.id.edtConfirmPassword);
                edtName = dialog.findViewById(R.id.edtName);
                edtPhone = dialog.findViewById(R.id.edtPhone);

                edtName.setText(userList.get(position).getName());
                edtPhone.setText(userList.get(position).getPhone());

                dialog.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        User user = new User();
                        user.setUsername(userList.get(position).getUsername());
                        user.setName(edtName.getText().toString().trim());
                        user.setPhone(edtPhone.getText().toString().trim());

                        userDAO.updateUser(user);
                        userList.get(position).setName(edtName.getText().toString().trim());
                        userList.get(position).setPhone(edtPhone.getText().toString().trim());
                        notifyDataSetChanged();
                        Toast.makeText(context, "Saved!!!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (userList == null) return 0;
        return userList.size();
    }
}
