package com.example.firebaseauth;

import android.content.Context;
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

import java.util.ArrayList;

public class recycleradapter extends RecyclerView.Adapter<recycleradapter.holder> {
    ArrayList<String> data;
    ArrayList<Integer> images;
    Context context;
    public recycleradapter(Context c ,ArrayList<String> data,ArrayList<Integer> images){
        context=c;
        this.data=data;
        this.images=images;
    }
    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=View.inflate(parent.getContext(),//R.layout.recycle,null);
        return new holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final holder holder, final int position) {
        holder.t.setText(data.get(position));
        holder.i.setImageResource(images.get(position));
        holder.c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, data.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class holder extends RecyclerView.ViewHolder {
        TextView t,t2;
        ImageView i;
        CardView c;

        public  holder(@NonNull View itemView) {
            super(itemView);
            //t=itemView.findViewById(R.id.t);
            //i=itemView.findViewById(R.id.recycleimage);

        }
    }
}
