package com.example.firebaseauth;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class recycleradapter extends RecyclerView.Adapter<recycleradapter.holder> {
    ArrayList<String> des;
    ArrayList<String> title;

    ArrayList<String> images;
    Context context;

    public recycleradapter(Context c, ArrayList<String> title, ArrayList<String> des, ArrayList<String> images) {
        context = c;
        this.title = title;
        this.des = des;
        this.images = images;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = View.inflate(parent.getContext(), R.layout.recyclerviewer, null);
        return new holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final holder holder, final int position) {
        holder.t.setText(des.get(position));
        holder.t2.setText(title.get(position));

        Glide.with(context).load(images.get(position)).centerCrop().into(holder.i);

        holder.c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context.getApplicationContext(), Enrollment.class);
                i.putExtra("TITLE", title.get(position));
                i.putExtra("Description",des.get(position));
                i.putExtra("Image_url",images.get(position));
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        if (title != null) {
            return title.size();
        } else {
            return 0;
        }
    }

    class holder extends RecyclerView.ViewHolder {
        TextView t, t2;
        ImageView i;
        CardView c;

        public holder(@NonNull View itemView) {
            super(itemView);
            t = itemView.findViewById(R.id.title);
            t2 = itemView.findViewById(R.id.description);
            i = itemView.findViewById(R.id.imageview);
            c = itemView.findViewById(R.id.cardview);

        }
    }
}
