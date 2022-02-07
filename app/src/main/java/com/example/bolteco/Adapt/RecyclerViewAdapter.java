package com.example.bolteco.Adapt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bolteco.ModeClass.Category_mode_class;
import com.example.bolteco.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass> {
    Context context;
    List<Category_mode_class> modeClass_category;

    public RecyclerViewAdapter(Context context, List<Category_mode_class> modeClass_category) {
        this.context = context;
        this.modeClass_category = modeClass_category;
    }

    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category,parent,false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);

        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {
        holder.categoryType.setText(modeClass_category.get(position).getCategory_name());

        holder.categoryType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, modeClass_category.get(position).getCategory_name().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return modeClass_category.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {

        TextView categoryType;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            categoryType = itemView.findViewById(R.id.categoryType);
        }
    }
}
