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
import com.example.bolteco.Activities.Home_page;
import com.example.bolteco.Activities.ui_details;
import com.example.bolteco.ModeClass.Feature_mode_class;
import com.example.bolteco.R;

import java.util.List;

public class RecyclerFeatureAdapter extends RecyclerView.Adapter<RecyclerFeatureAdapter.ViewHolderClass> {
    Context context;
    List<Feature_mode_class> modeClass_feature;

    public RecyclerFeatureAdapter(Home_page context, List<Feature_mode_class> modeClass_feature) {
        this.context = context;
        this.modeClass_feature = modeClass_feature;
    }

    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_feature, parent, false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);

        return viewHolderClass;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, @SuppressLint("RecyclerView") int position) {
        holder.prod_price.setText(modeClass_feature.get(position).getPrice() + " $");
        holder.prod_name.setText(modeClass_feature.get(position).getProductName());
        Glide.with(context).load(modeClass_feature.get(position).getProductImage()).into(holder.prod_image);
        holder.prod_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Amazing
                Intent intent = new Intent(context, ui_details.class);
                intent.putExtra("details",modeClass_feature.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modeClass_feature.size();
    }


    public class ViewHolderClass extends RecyclerView.ViewHolder {

        ImageView prod_image;
        TextView prod_name, prod_price;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);

            prod_image = itemView.findViewById(R.id.prod_image);
            prod_name = itemView.findViewById(R.id.prod_name);
            prod_price = itemView.findViewById(R.id.prod_price);

        }
    }
}
