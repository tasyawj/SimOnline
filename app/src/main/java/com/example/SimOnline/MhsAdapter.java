package com.example.SimOnline;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MhsAdapter extends RecyclerView.Adapter<MhsAdapter.MhsVH> {

    private ArrayList<MhsModel> mhsList ;
    private final OnItemClickListener listener;

    public MhsAdapter(ArrayList<MhsModel> mhsList, OnItemClickListener listener) {
        this.mhsList = mhsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MhsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_listmhs,parent,false);
        return new MhsVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MhsVH holder, int position) {
        holder.tvNamaVal.setText(mhsList.get(position).getNama());
        holder.tvAlamatVal.setText(mhsList.get(position).getAlamat());
        holder.tvNoHpVal.setText(mhsList.get(position).getNohp());
        holder.tvTglLahirVal.setText(mhsList.get(position).getTgllahir());

        holder.bind(mhsList, position, listener);
    }
    public interface OnItemClickListener {
        void OnItemClick(ArrayList<MhsModel> mhsList, int position);
    }

    public void removeItem(int position){
        this.mhsList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mhsList.size();
    }

    public class MhsVH extends RecyclerView.ViewHolder{

        private TextView tvNamaVal, tvAlamatVal, tvNoHpVal, tvTglLahirVal ;
        private CardView cvItem;

        public MhsVH(@NonNull View itemView) {
            super(itemView);

            tvNamaVal = itemView.findViewById(R.id.tvNamaVal)   ;
            tvAlamatVal = itemView.findViewById(R.id.tvAlamatVal)     ;
            tvNoHpVal = itemView.findViewById(R.id.tvNoHpVal)   ;
            tvTglLahirVal = itemView.findViewById(R.id.tvTglLahirVal)   ;
            cvItem = itemView.findViewById(R.id.cvItem);
        }
        public void bind(ArrayList<MhsModel> mhsList, int position, OnItemClickListener listener){
        cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnItemClick(mhsList, position);
                notifyDataSetChanged();
            }
        });
        }
    }
}
