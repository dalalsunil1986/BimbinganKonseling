package com.example.bimbingankonseling;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.bimbingankonseling.Adapter.AdapterRiwayatKejadian;
import com.example.bimbingankonseling.Model.DataSiswa;
import com.example.bimbingankonseling.Model.ResponseSiswa;
import com.example.bimbingankonseling.Rest.ApiClient;
import com.example.bimbingankonseling.Rest.ApiInterface;

import java.util.List;

public class SuratPanggiilanActivity extends AppCompatActivity {


    private ApiInterface mApiInterface;
    private TextView tvNama,tvKelas;
    private String nama,kelas;
    private SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surat_panggiilan);

        mApiInterface   = ApiClient.getClient().create(ApiInterface.class);
        sessionManager = new SessionManager(this);

        tvNama  = findViewById(R.id.tvNama);
        tvKelas = findViewById(R.id.tvKelas);

        getDataSiswa(sessionManager.pref.getString("id_siswa",null));
    }

    private void getDataSiswa(final String id_siswa) {

        Call<ResponseSiswa> dataSiswa = mApiInterface.postDataSiswa(id_siswa);
        dataSiswa.enqueue(new Callback<ResponseSiswa>() {
            @Override
            public void onResponse(Call<ResponseSiswa> call, Response<ResponseSiswa> response) {
                List<DataSiswa> data = response.body().getData();
                if (data.isEmpty()){
                    tvNama.setText("-");
                    tvKelas.setText("-");
                }else {
                    nama = data.get(0).getNama();
                    kelas= data.get(0).getKelas();

                    tvNama.setText(nama);
                    tvKelas.setText(kelas);
                }
            }

            @Override
            public void onFailure(Call<ResponseSiswa> call, Throwable t) {
                Log.e("cek ","Error");
            }
        });
    }
}
