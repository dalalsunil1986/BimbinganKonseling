package com.example.bimbingankonseling.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bimbingankonseling.DetailSiswaActivity;
import com.example.bimbingankonseling.Model.DataPoinSiswa;
import com.example.bimbingankonseling.Model.DataSP;
import com.example.bimbingankonseling.Model.DataSiswa;
import com.example.bimbingankonseling.R;
import com.example.bimbingankonseling.SuratPanggiilanActivity;

import java.util.List;


public class AdapterRiwayatKejadian extends RecyclerView.Adapter<AdapterRiwayatKejadian.ViewHolder>{
    private List<DataSP> mArrayList;
    private Context context;

    public AdapterRiwayatKejadian(Context context, List<DataSP> inputData) {
        mArrayList = inputData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_riwayat_kejadian, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int i) {
        holder.tvSP.setText(mArrayList.get(i).getNamaSP());
//        holder.tvNis.setText(mArrayList.get(i).getNis());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(context, SuratPanggiilanActivity.class);
                /*a.putExtra("nama",mArrayList.get(i).getNama());
                a.putExtra("nis",mArrayList.get(i).getNis());
                a.putExtra("kelas",mArrayList.get(i).getKelas());
                a.putExtra("jurusan",mArrayList.get(i).getJurusan());
                a.putExtra("notelp",mArrayList.get(i).getNotelp());*/
                context.startActivity(a);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvSP;

        public ViewHolder(View v) {
            super(v);
            tvSP    = v.findViewById(R.id.tvSP);
//            tvTanggal           = v.findViewById(R.id.tvTanggal);
//            tvTotalPoin         = v.findViewById(R.id.tvTotalPoin);
        }
    }
}
