package com.example.bolteco.Adapt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bolteco.Activities.ui_details;
import com.example.bolteco.ModeClass.OnTrend_mode_class;
import com.example.bolteco.R;

import java.util.List;

public class RecyclerOnTrendAdapter extends RecyclerView.Adapter<RecyclerOnTrendAdapter.ViewHolderClass> {
    Context context;
    List<OnTrend_mode_class> modeClass_onTrend;

    public RecyclerOnTrendAdapter(Context context, List<OnTrend_mode_class> modeClass_onTrend) {
        this.context = context;
        this.modeClass_onTrend = modeClass_onTrend;
    }

    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ontrend_view,parent,false);

        return new ViewHolderClass(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, @SuppressLint("RecyclerView") int position) {
        holder.trend_name.setText(modeClass_onTrend.get(position).getName());
        holder.trend_description.setText(modeClass_onTrend.get(position).getDescription());
        holder.trend_likes.setText(modeClass_onTrend.get(position).getLikes_count()+" likes");
        holder.trend_name.setText(modeClass_onTrend.get(position).getName());
        holder.manufacture_name.setText(modeClass_onTrend.get(position).getManufacture_name());
        holder.num_buyers.setText(modeClass_onTrend.get(position).getNum_buyers()+"");

        Glide.with(context).load(modeClass_onTrend.get(position).getImage_url()).into(holder.trend_image);

        holder.trend_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ui_details.class);
                intent.putExtra("details",  modeClass_onTrend.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modeClass_onTrend.size();
    }

    public static class ViewHolderClass extends RecyclerView.ViewHolder {

        ImageView trend_image,like_btn;
        TextView trend_name,manufacture_name,num_buyers,trend_description,trend_likes;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);

            trend_image = itemView.findViewById(R.id.trend_image);
            like_btn = itemView.findViewById(R.id.like_btn);
            manufacture_name = itemView.findViewById(R.id.manufacture_name);
            num_buyers = itemView.findViewById(R.id.num_buyers);
            trend_description = itemView.findViewById(R.id.trend_description);
            trend_likes = itemView.findViewById(R.id.trend_likes);
            trend_name = itemView.findViewById(R.id.trend_name);
        }
    }
}
