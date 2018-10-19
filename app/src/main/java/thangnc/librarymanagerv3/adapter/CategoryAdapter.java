package thangnc.librarymanagerv3.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import thangnc.librarymanagerv3.R;
import thangnc.librarymanagerv3.holder.CateHolder;
import thangnc.librarymanagerv3.model.Category;
import thangnc.librarymanagerv3.sqlitedao.CategoryDAO;

public class CategoryAdapter extends RecyclerView.Adapter<CateHolder> {

    private Context context;
    private List<Category> categoryList;
    private CategoryDAO categoryDAO;

    public CategoryAdapter(Context context, List<Category> categoryList, CategoryDAO categoryDAO) {
        this.context = context;
        this.categoryList = categoryList;
        this.categoryDAO = categoryDAO;
    }

    @NonNull
    @Override
    public CateHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_cate, viewGroup, false);
        return new CateHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull CateHolder cateHolder, final int i) {
        final Category category = categoryList.get(i);
        cateHolder.tvCateName.setText(category.id);
        cateHolder.tvCateDesc.setText(category.name);
        cateHolder.imgDeleteCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long result = categoryDAO.deleteCategory(category.id);

                if (result < 0){
                    Toast.makeText(context, "Delete not success!!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Delete success!!", Toast.LENGTH_SHORT).show();
                    categoryList.remove(i);
                    notifyDataSetChanged();
                }
            }
        });
        cateHolder.imgEditCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_change_cate);
                final EditText edtID;
                final EditText edtName;
                final EditText edtDes;
                final EditText edtPos;
                Button btnAddCate;
                Button btnCancel;

                edtID = dialog.findViewById(R.id.edtID);
                edtID.setVisibility(View.GONE);
                edtName = dialog.findViewById(R.id.edtName);
                edtDes = dialog.findViewById(R.id.edtDes);
                edtPos = dialog.findViewById(R.id.edtPos);
                btnAddCate = dialog.findViewById(R.id.btnAddCategory);
                btnCancel = dialog.findViewById(R.id.btnCancel);
                edtName.setText(category.name);
                edtDes.setText(category.desc);
                edtPos.setText(category.postion);
                btnAddCate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = category.id;
                        String name = edtName.getText().toString().trim();
                        String desc = edtDes.getText().toString().trim();
                        String pos = edtPos.getText().toString().trim();
                        Category category = new Category(id, name, desc, pos);
                        long result = categoryDAO.updateCategory(category);
                        if (result < 0){
                            Toast.makeText(context, "Update not success", Toast.LENGTH_SHORT).show();

                        }else {
                            categoryList.get(i).name = name;
                            categoryList.get(i).desc = desc;
                            categoryList.get(i).postion = pos;

                            notifyDataSetChanged();
                            Toast.makeText(context, "Update success", Toast.LENGTH_SHORT).show();
                            dialog.cancel();

                        }
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
