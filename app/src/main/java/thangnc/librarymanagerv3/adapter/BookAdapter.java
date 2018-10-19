package thangnc.librarymanagerv3.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import thangnc.librarymanagerv3.R;
import thangnc.librarymanagerv3.holder.BookHolder;
import thangnc.librarymanagerv3.model.Book;
import thangnc.librarymanagerv3.sqlitedao.BookDAO;

public class BookAdapter extends RecyclerView.Adapter<BookHolder> {
    private Context context;
    private List<Book> bookList;
    private BookDAO bookDAO;

    public BookAdapter(Context context, List<Book> bookList, BookDAO bookDAO) {
        this.context = context;
        this.bookList = bookList;
        this.bookDAO = bookDAO;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, viewGroup, false);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder bookHolder, final int i) {
        final Book book = bookList.get(i);
        bookHolder.tvNameBook.setText(book.bookName);
        bookHolder.tvPriceBook.setText(book.price);
        bookHolder.imgDeleteBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long result = bookDAO.deleteBook(book.bookId);
                if (result<0){
                    Toast.makeText(context, "Delete not success", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Delete success", Toast.LENGTH_SHORT).show();
                    bookList.remove(i);
                    notifyDataSetChanged();
                }
            }
        });
        bookHolder.imgEditBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_change_book);

                final EditText edBookID;
                final EditText edBookName;
                final EditText edtBookAuthor;
                final EditText edBookPrice;
                Button btnSaveBook;
                Button btnCancelBook;

                edBookID =  dialog.findViewById(R.id.edBookID);
                edBookName =  dialog.findViewById(R.id.edBookName);
                edtBookAuthor = dialog.findViewById(R.id.edBookAuthor);
                edBookPrice =  dialog.findViewById(R.id.edBookPrice);
                btnSaveBook =  dialog.findViewById(R.id.btnSaveBook);
                btnCancelBook =  dialog.findViewById(R.id.btnCancelBook);

                edBookID.setText(book.bookId);
                edBookName.setText(book.bookName);
                edtBookAuthor.setText(book.author);
                edBookPrice.setText(book.price);

                btnSaveBook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Book book1 = new Book();
                        String id = book.bookId;
                        String name = edBookName.getText().toString().trim();
                        String author = edtBookAuthor.getText().toString().trim();
                        String price = edBookPrice.getText().toString().trim();
                        Book book = new Book(id, name, author, price);
                        long result = bookDAO.updateBook(book);
                        if (result < 0){
                            Toast.makeText(context, "Update not success", Toast.LENGTH_SHORT).show();
                        }else {
                            bookList.get(i).bookName = name;
                            bookList.get(i).author = author;
                            bookList.get(i).price = price;
                            notifyDataSetChanged();
                            Toast.makeText(context, "Update successfully", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    }
                });
                btnCancelBook.setOnClickListener(new View.OnClickListener() {
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
        if (bookList == null) return 0;
        return bookList.size();
    }
}
