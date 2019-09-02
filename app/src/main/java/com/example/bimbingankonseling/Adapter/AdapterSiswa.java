package com.example.bimbingankonseling.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bimbingankonseling.DetailSiswaActivity;
import com.example.bimbingankonseling.Model.DataSiswa;
import com.example.bimbingankonseling.R;

import java.util.List;


public class AdapterSiswa extends RecyclerView.Adapter<AdapterSiswa.ViewHolder>{
    private List<DataSiswa> mArrayList;
    private Context context;

    public AdapterSiswa(Context context, List<DataSiswa> inputData) {
        mArrayList = inputData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_list_siswa, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int i) {
        holder.tvNama.setText(mArrayList.get(i).getNama());
        holder.tvNis.setText(mArrayList.get(i).getNis());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(context, DetailSiswaActivity.class);
                a.putExtra("id",mArrayList.get(i).getId());
                a.putExtra("nama",mArrayList.get(i).getNama());
                a.putExtra("nis",mArrayList.get(i).getNis());
                a.putExtra("kelas",mArrayList.get(i).getKelas());
                a.putExtra("jurusan",mArrayList.get(i).getJurusan());
                a.putExtra("notelp",mArrayList.get(i).getNotelp());
                context.startActivity(a);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNama,tvNis;

        public ViewHolder(View v) {
            super(v);
            tvNama         = (TextView) v.findViewById(R.id.tvNama);
            tvNis          = (TextView) v.findViewById(R.id.tvNis);
        }
    }
}
