package com.example.appghichu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    Context context;
    ArrayList<Note> notes = new ArrayList<>();



    Adapter(Context context, ArrayList<Note> notes ){
        this.context=context;
        this.notes=notes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.activity_item,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        String title = notes.get(position).getTitle();
        String content = notes.get(position).getContent();
        String date = notes.get(position).getDate();

        holder.title.setText(title);
        holder.content.setText(content);
        holder.date.setText(date);


    }

    public void setData(ArrayList<Note> newDataList) {
        notes = newDataList;
    }
    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, content, date;
        RelativeLayout noteContainer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleOutput);
            content = itemView.findViewById(R.id.contentOutput);
            date = itemView.findViewById(R.id.dateOutput);
            noteContainer = itemView.findViewById(R.id.noteContainer);

            noteContainer.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    PopupMenu menu = new PopupMenu(context,v);

                    menu.getMenu().add("Sửa");
                    menu.getMenu().add("Xóa");

                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getTitle().equals("Sửa")){
                                Intent intent =new Intent(context,update.class);
                                intent.putExtra("id",notes.get(getAbsoluteAdapterPosition()).getId());
                                intent.putExtra("title",notes.get(getAbsoluteAdapterPosition()).getTitle());
                                intent.putExtra("content",notes.get(getAbsoluteAdapterPosition()).getContent());

                                context.startActivity(intent);


                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setTitle("Xác nhận xóa");
                                builder.setMessage("Bạn có chắc muốn xóa!!!");

                                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }

                            return false;
                        }
                    });
                    menu.show();

                    return false;
                }
            });
        }
    }
}
