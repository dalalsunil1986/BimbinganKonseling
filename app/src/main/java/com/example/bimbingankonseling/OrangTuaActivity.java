package com.example.bimbingankonseling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bimbingankonseling.Adapter.AdapterRiwayatKejadian;
import com.example.bimbingankonseling.Adapter.AdapterSiswa;
import com.example.bimbingankonseling.Model.DataPoinSiswa;
import com.example.bimbingankonseling.Model.DataSiswa;
import com.example.bimbingankonseling.Model.ResponsePoinSiswa;
import com.example.bimbingankonseling.Model.ResponseSiswa;
import com.example.bimbingankonseling.Rest.ApiClient;
import com.example.bimbingankonseling.Rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrangTuaActivity extends AppCompatActivity {

    private ApiInterface mApiInterface;
    private NotificationManagerCompat notificationManager;
    private RecyclerView rvSiswa;
    private AdapterRiwayatKejadian mAdapter;
    private List<DataSiswa> dataSiswas = new ArrayList<>();
    private SessionManager sessionManager;
    private TextView tvKosong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orang_tua);
        mApiInterface   = ApiClient.getClient().create(ApiInterface.class);
        tvKosong        = findViewById(R.id.tvKosong);

        rvSiswa = findViewById(R.id.rvOrangTua);
        rvSiswa.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvSiswa.setLayoutManager(layoutManager);
        notificationManager = NotificationManagerCompat.from(this);
        sessionManager = new SessionManager(this);

        getPoinSiswa(sessionManager.pref.getString("id_siswa",null));
/*
        dataSiswas.add(0, new DataSiswa("Meisya Luna Assyura","17361","12","Perkantoran","","088744010377","M.Nasir","Aryani Anwar","Jl.Tinumbu lr.166b","082187779907"));
        dataSiswas.add(1, new DataSiswa("Ramlah","17372","12","Perkantoran","","81242050035","Syamsuddin","","Jl.Sibula dalam lr.1 no.2a",""));
        dataSiswas.add(2, new DataSiswa("Nurul Fadhila Al-Qarani","17369","12","Perkantoran","","81218635626","Muh.Arif","","Jl.Kandea 3","082198187823"));*/

    }

    private void getPoinSiswa(String id_siswa) {
        Call<ResponsePoinSiswa> call = mApiInterface.postPoinSiswa(id_siswa);
        call.enqueue(new Callback<ResponsePoinSiswa>() {
            @Override
            public void onResponse(Call<ResponsePoinSiswa> call, Response<ResponsePoinSiswa> response) {
                List<DataPoinSiswa> poinSiswa = response.body().getData();
                try {
                    int jmlPoin =0;
                    int totalPoin=0;

                    for (int i = 0; i<poinSiswa.size();i++) {
                        String idSiswa          = poinSiswa.get(i).getIdSiswa();
                        String idPelanggaran    = poinSiswa.get(i).getIdPelanggaran();
                        String poin             = poinSiswa.get(i).getPoin();
                        jmlPoin                 = Integer.parseInt(poin);
                        totalPoin               = totalPoin+jmlPoin;
                    }

                    if (totalPoin==50 && totalPoin<100){
                        getNotif("SP 1");
                    }else if (totalPoin==100 && totalPoin<200){
                        getNotif("SP 2");
                    }else if (totalPoin>=200){
                        getNotif("SP 3");
                    }else{
                        tvKosong.setVisibility(View.VISIBLE);
                    }
                    Toast.makeText(OrangTuaActivity.this,"Total Poin Siswa "+String.valueOf(totalPoin),Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponsePoinSiswa> call, Throwable t) {
                Toast.makeText(OrangTuaActivity.this, "Jaringan bermasalah", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getNotif(String sp) {

        getDataSiswa(sessionManager.pref.getString("id_siswa",null));

        Intent activityIntent = new Intent(this, SuratPanggiilanActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, activityIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, "1")
                .setSmallIcon(R.drawable.ic_student)
                .setContentTitle("Pemberitahuan Sekolah")
                .setContentText(sp)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("Diharapkan kedatangan orangtua siswa untuk menindaklanjuti SP")
                        .setBigContentTitle("Pemberitahuan "+sp)
                        .setSummaryText(sp))
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .build();

        notificationManager.notify(1, notification);
    }

    private void getDataSiswa(final String id_siswa) {
        Call<ResponseSiswa> dataSiswa = mApiInterface.postDataSiswa(id_siswa);
        dataSiswa.enqueue(new Callback<ResponseSiswa>() {
            @Override
            public void onResponse(Call<ResponseSiswa> call, Response<ResponseSiswa> response) {
                List<DataSiswa> data = response.body().getData();
                if (data.isEmpty()){
                    tvKosong.setVisibility(View.VISIBLE);
                }else {
                    tvKosong.setVisibility(View.GONE);

                    postDataPoinSiswa(id_siswa);
                }
            }

            @Override
            public void onFailure(Call<ResponseSiswa> call, Throwable t) {
                Log.e("cek ","Error");
            }
        });
    }

    private void postDataPoinSiswa(String id_siswa) {
        Call<ResponsePoinSiswa> call = mApiInterface.postPoinSiswa(id_siswa);
        call.enqueue(new Callback<ResponsePoinSiswa>() {
            @Override
            public void onResponse(Call<ResponsePoinSiswa> call, Response<ResponsePoinSiswa> response) {
                List<DataPoinSiswa> poinSiswa = response.body().getData();
                String id_pelanggaran = poinSiswa.get(0).getIdPelanggaran();

                mAdapter = new AdapterRiwayatKejadian(OrangTuaActivity.this, poinSiswa);
                rvSiswa.setAdapter(mAdapter);
//                postDataPelanggaran(id_pelanggaran);
            }

            @Override
            public void onFailure(Call<ResponsePoinSiswa> call, Throwable t) {

            }
        });
    }

    private void postDataPelanggaran(String id_pelanggaran) {

        /*Call<ResponsePoinSiswa> call = mApiInterface.postPelanggaranSiswa(id_pelanggaran);
        call.enqueue(new Callback<ResponsePoinSiswa>() {
            @Override
            public void onResponse(Call<ResponsePoinSiswa> call, Response<ResponsePoinSiswa> response) {
                List<DataPoinSiswa> poinSiswa = response.body().getData();
                String id_pelanggaran = poinSiswa.get(0).getIdPelanggaran();

                postDataPelanggaran(id_pelanggaran);
            }

            @Override
            public void onFailure(Call<ResponsePoinSiswa> call, Throwable t) {

            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.btKeluar){
            sessionManager.logoutUser();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
