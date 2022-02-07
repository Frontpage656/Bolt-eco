package com.example.bolteco.Adapt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bolteco.Activities.Address;
import com.example.bolteco.ModeClass.Address_mode_class;
import com.example.bolteco.R;

import java.util.List;

public class RecyclerAdatpterAddress extends RecyclerView.Adapter<RecyclerAdatpterAddress.ViewHolderClass> {
    RadioButton radioButton;
    Context context;
    List<Address_mode_class> list;

    public RecyclerAdatpterAddress(Context context, List<Address_mode_class> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_it,parent,false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);

        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, @SuppressLint("RecyclerView") int position) {

         holder.my_address.setText(list.get(position).getAddress());
         holder.select_it.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 
                 // How is this happening select only single radio button in list but make sure you have declared Radio button at the top.
                 for (Address_mode_class addressModeClass:list){
                     addressModeClass.setSelected(false);
                 }
                 list.get(position).setSelected(true);
                 if (radioButton != null){
                     radioButton.setChecked(false);
                 }
                 radioButton = (RadioButton) view;
                 radioButton.setChecked(true);
             }
         });


    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {
        TextView my_address;
        RadioButton select_it;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            my_address = itemView.findViewById(R.id.my_address);
            select_it = itemView.findViewById(R.id.select_it);
        }
    }
}
