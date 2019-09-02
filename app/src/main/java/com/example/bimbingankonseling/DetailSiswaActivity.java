package com.example.bimbingankonseling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.bimbingankonseling.Adapter.AdapterPelanggaran;
import com.example.bimbingankonseling.Adapter.AdapterSiswa;
import com.example.bimbingankonseling.Model.DataPelanggaran;
import com.example.bimbingankonseling.Model.DataPoin;
import com.example.bimbingankonseling.Model.DataSiswa;
import com.example.bimbingankonseling.Model.ResponsePelanggaran;
import com.example.bimbingankonseling.Rest.ApiClient;
import com.example.bimbingankonseling.Rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSiswaActivity extends AppCompatActivity implements AdapterPelanggaran.OnDialogClickListener {

    private ApiInterface mApiInterface;
    private ScrollView scrollSiswa;
    private RecyclerView rvPelanggaran;
    private AdapterPelanggaran mAdapter;
    private List<DataPoin> dataPelanggarans= new ArrayList<>();
    private TextView tvNama,tvNis,tvKelas,tvJurusan,tvNotelp,tvTotalPoin;
    private int total_poin = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_siswa);

        mApiInterface   = ApiClient.getClient().create(ApiInterface.class);
        scrollSiswa     = findViewById(R.id.scrollSiswa);
        rvPelanggaran   = findViewById(R.id.rvPelanggaran);
        tvNama          = findViewById(R.id.tvNama);
        tvNis           = findViewById(R.id.tvNis);
        tvKelas         = findViewById(R.id.tvKelas);
        tvJurusan       = findViewById(R.id.tvJurusan);
        tvNotelp        = findViewById(R.id.tvNotelp);
        tvTotalPoin     = findViewById(R.id.tvTotalPoin);

        Intent i = getIntent();
        String id       = i.getStringExtra("id");
        String nama     = i.getStringExtra("nama");
        String nis      = i.getStringExtra("nis");
        String kelas    = i.getStringExtra("kelas");
        String jurusan  = i.getStringExtra("jurusan");
        String notelp   = i.getStringExtra("notelp");

        tvNama.setText(nama);
        tvNis.setText("Nis : "+nis);
        tvKelas.setText("Kelas : "+kelas);
        tvJurusan.setText(jurusan);
        tvNotelp.setText(notelp);
        tvTotalPoin.setText("Total Poin : "+String.valueOf(total_poin));

        rvPelanggaran.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        rvPelanggaran.setLayoutManager(layoutManager);

        getDataPelanggaran(id);
//        setPelanggaran(dataPelanggarans);
//
//        mAdapter = new AdapterPelanggaran(DetailSiswaActivity.this,dataPelanggarans,DetailSiswaActivity.this,total_poin);
//        rvPelanggaran.setAdapter(mAdapter);

        scrollSiswa.smoothScrollTo(0,0);
    }

    private void getDataPelanggaran(final String id) {
        Call<ResponsePelanggaran> dataPelanggaran = mApiInterface.getDataPelanggaran();
        dataPelanggaran.enqueue(new Callback<ResponsePelanggaran>() {
            @Override
            public void onResponse(Call<ResponsePelanggaran> call, Response<ResponsePelanggaran> response) {
                List<DataPelanggaran> data = response.body().getData();
                Log.e("cek ",data.toString());
                mAdapter = new AdapterPelanggaran(DetailSiswaActivity.this, data, DetailSiswaActivity.this, total_poin,id);
                rvPelanggaran.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<ResponsePelanggaran> call, Throwable t) {
                Log.e("cek ","Error");
            }
        });


    }

    private void setPelanggaran(List<DataPoin> dataPelanggarans) {
        dataPelanggarans.add(0, new DataPoin("Perkelahian/Penganiayaan",""));
        dataPelanggarans.add(1, new DataPoin("Pencurian",""));
        dataPelanggarans.add(2, new DataPoin("Pelecehan terhadap guru",""));
        dataPelanggarans.add(3, new DataPoin("Perbuatan Asusila",""));
        dataPelanggarans.add(4, new DataPoin("Narkoba",""));
        dataPelanggarans.add(5, new DataPoin("Membawa senjata tajam",""));
        dataPelanggarans.add(6, new DataPoin("Mengadakan perusakan gedung/perlengkapan sekolah",""));
        dataPelanggarans.add(7, new DataPoin("Mengadakan/mengikuti demo",""));
        dataPelanggarans.add(8, new DataPoin("Menimbulkan keonaran disekolah",""));
        dataPelanggarans.add(9, new DataPoin("Membawa minum-minuman keras",""));
        dataPelanggarans.add(10, new DataPoin("Membawa/membaca buku porno dan sejenisnya",""));
        dataPelanggarans.add(11, new DataPoin("Merokok dalam lingkungan sekolah",""));
        dataPelanggarans.add(12, new DataPoin("Mengancam/memalak sesama siswa",""));
        dataPelanggarans.add(13, new DataPoin("Mengedarkan kupon bazar dan sejenisnya tanpa izin dari kepala sekolah",""));
        dataPelanggarans.add(14, new DataPoin("Memanjat pagar sekolah masuk/keluar",""));
        dataPelanggarans.add(15, new DataPoin("Melaporkan persoalan ke orang tua tanpa melaporkan sebelumnya kepada pihak sekolah",""));
        dataPelanggarans.add(16, new DataPoin("Menggunakan Hp dan sejenisnya  pada saat pembelajaran berlangsung",""));
        dataPelanggarans.add(17, new DataPoin("Mengadakan kegiatan atas nama sekolah secara kelompok diluar sekolah tanpa izin kepala sekolah",""));
        dataPelanggarans.add(18, new DataPoin("Mencoret-coret gedung/perlengkapan sekolah",""));
        dataPelanggarans.add(19, new DataPoin("Merusak barang orang lain baik disengaja maupun tidak",""));
        dataPelanggarans.add(20, new DataPoin("Membolos pada jam sekolah",""));
        dataPelanggarans.add(21, new DataPoin("Membawa rokok",""));
        dataPelanggarans.add(22, new DataPoin("Memakai jaket dan sejenisnya didalam lingkungan sekolah atau saat belajar",""));
        dataPelanggarans.add(23, new DataPoin("Memakai sendal",""));
        dataPelanggarans.add(24, new DataPoin("Membuang sampah sembarangan",""));
        dataPelanggarans.add(25, new DataPoin("Bermain judi",""));
        dataPelanggarans.add(26, new DataPoin("Terlambat",""));
        dataPelanggarans.add(27, new DataPoin("Tidak mengikuti upacara",""));
        dataPelanggarans.add(28, new DataPoin("Mengirim surat palsu ke sekolah",""));
        dataPelanggarans.add(29, new DataPoin("anggota badan ditato dan rambut dicat",""));
        dataPelanggarans.add(30, new DataPoin("Berpakaian tidak sesuai dengan seragam sekolah",""));
        dataPelanggarans.add(31, new DataPoin("Pakaian muslim tidak sesuai dengan ketentuan sekolah",""));
        dataPelanggarans.add(32, new DataPoin("pakaian ketat",""));
        dataPelanggarans.add(33, new DataPoin("Tidak memakai atribut",""));
        dataPelanggarans.add(34, new DataPoin("Keluar kelas tidak minta izin",""));
        dataPelanggarans.add(35, new DataPoin("Makan/minum dalam kelas saat pembelajaran",""));
        dataPelanggarans.add(36, new DataPoin("Rambut pria tidak dicukur (1,2,3 cm) dan wanita tidak rapi",""));
        dataPelanggarans.add(37, new DataPoin("Memakai topi selain topi sekolah",""));
        dataPelanggarans.add(38, new DataPoin("Berpakaian tidak rapi",""));
        dataPelanggarans.add(39, new DataPoin("Membiarkan kuku panjang dan memakai cat kuku",""));
    }

    @Override
    public void onClickFinish(int poin) {
        total_poin = total_poin+poin;
        tvTotalPoin.setText("Total Poin : "+String.valueOf(total_poin ));
    }
}
