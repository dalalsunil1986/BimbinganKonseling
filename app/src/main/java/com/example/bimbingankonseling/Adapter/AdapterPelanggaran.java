package com.example.bimbingankonseling.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bimbingankonseling.LoginActivity;
import com.example.bimbingankonseling.MainActivity;
import com.example.bimbingankonseling.Model.DataLogin;
import com.example.bimbingankonseling.Model.DataPelanggaran;
import com.example.bimbingankonseling.Model.DataPoin;
import com.example.bimbingankonseling.Model.DataPoinSiswa;
import com.example.bimbingankonseling.Model.DataSiswa;
import com.example.bimbingankonseling.Model.ResponseLogin;
import com.example.bimbingankonseling.Model.ResponsePoinSiswa;
import com.example.bimbingankonseling.OrangTuaActivity;
import com.example.bimbingankonseling.R;
import com.example.bimbingankonseling.Rest.ApiClient;
import com.example.bimbingankonseling.Rest.ApiInterface;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdapterPelanggaran extends RecyclerView.Adapter<AdapterPelanggaran.ViewHolder>{

    private ApiInterface mApiInterface;
    private List<DataPelanggaran> mArrayList;
    private Context context;
    private Dialog myDialog;
    private int total_poin;
    private OnDialogClickListener onDialogClickListener;
    private String id_siswa,id_pelanggaran,poin,id;

    public interface OnDialogClickListener{
        void onClickFinish(int poin);
    }

    public AdapterPelanggaran(Context context, List<DataPelanggaran> inputData, OnDialogClickListener onDialogClickListener, int total_poin, String id) {
        mArrayList = inputData;
        this.context = context;
        this.onDialogClickListener = onDialogClickListener;
        this.total_poin = total_poin;
        this.id= id;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pelanggaran, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int i) {
        mApiInterface   = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponsePoinSiswa> call = mApiInterface.postPoinSiswa(id);
        call.enqueue(new Callback<ResponsePoinSiswa>() {
            @Override
            public void onResponse(Call<ResponsePoinSiswa> call, Response<ResponsePoinSiswa> response) {
                List<DataPoinSiswa> poinSiswa = response.body().getData();
                try {
                    String idSiswa          = poinSiswa.get(i).getIdSiswa();
                    String idPelanggaran    = poinSiswa.get(i).getIdPelanggaran();
                    String poin             = poinSiswa.get(i).getPoin();

                    if (idSiswa.equals(id) && idPelanggaran.equals(mArrayList.get(i).getId())){
                        holder.tvPoin.setText(poin);
                    }else {
                        holder.tvPoin.setText("0");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    holder.tvPoin.setText("0");
                }
            }

            @Override
            public void onFailure(Call<ResponsePoinSiswa> call, Throwable t) {
                Toast.makeText(context, "Jaringan bermasalah", Toast.LENGTH_SHORT).show();
            }
        });

        holder.tvPelanggaran.setText(mArrayList.get(i).getJenisPelanggaran());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tvSelesai;
                final EditText etPoin;

                myDialog = new Dialog(context);
                myDialog.setContentView(R.layout.dialog_tambah_poin);
                tvSelesai   = myDialog.findViewById(R.id.tvSelesai);
                etPoin      = myDialog.findViewById(R.id.etPoin);

                tvSelesai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int total_poin_s = total_poin+Integer.parseInt(etPoin.getText().toString());
                        holder.tvPoin.setText(etPoin.getText().toString());

                        id_siswa        = id;
                        id_pelanggaran  = mArrayList.get(i).getId();
                        poin            = etPoin.getText().toString();

                        postDataPelanggaran(id_siswa,id_pelanggaran,poin);
                        onDialogClickListener.onClickFinish(total_poin_s);

                        myDialog.dismiss();
                    }
                });
                myDialog.show();
            }
        });
    }

    private void postDataPelanggaran(String id_siswa, String id_pelanggaran, String poin) {
        Call<ResponseBody> postPoin = mApiInterface.postPoin(id_siswa,id_pelanggaran,poin);
        postPoin.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(context, "Berhasil Ubah Poin", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvPelanggaran,tvPoin;

        public ViewHolder(View v) {
            super(v);
            tvPelanggaran   = (TextView) v.findViewById(R.id.tvPelanggaran);
            tvPoin          = (TextView) v.findViewById(R.id.tvPoin);
        }
    }
}
