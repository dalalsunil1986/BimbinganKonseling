package com.example.bimbingankonseling;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bimbingankonseling.Model.DataPoinSiswa;
import com.example.bimbingankonseling.Model.ResponsePoinSiswa;
import com.example.bimbingankonseling.Rest.ApiClient;
import com.example.bimbingankonseling.Rest.ApiInterface;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDuaFragment extends Fragment {

    private ApiInterface mApiInterface;
    private EditText etNamaSiswa,etNis,etKelas,etJurusan,etEmail,etNotelp,etAyah,etIbu,etAlamat,
            etNotelpOrangtua;
    private Button btDaftar;

    public FragmentDuaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment_dua, container, false);

        mApiInterface       = ApiClient.getClient().create(ApiInterface.class);
        etNamaSiswa         = v.findViewById(R.id.etNamaSiswa);
        etNis               = v.findViewById(R.id.etNis);
        etKelas             = v.findViewById(R.id.etKelas);
        etJurusan           = v.findViewById(R.id.etJurusan);
        etEmail             = v.findViewById(R.id.etEmail);
        etNotelp            = v.findViewById(R.id.etNotelp);
        etAyah              = v.findViewById(R.id.etAyah);
        etIbu               = v.findViewById(R.id.etIbu);
        etAlamat            = v.findViewById(R.id.etAlamat);
        etNotelpOrangtua    = v.findViewById(R.id.etNotelpOrangtua);
        btDaftar            = v.findViewById(R.id.btDaftar);

        btDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = etNamaSiswa.getText().toString();
                String nis = etNis.getText().toString();
                String kelas = etKelas.getText().toString();
                String jurusan = etJurusan.getText().toString();
                String email = etEmail.getText().toString();
                String notelp = etNotelp.getText().toString();
                String ayah = etAyah.getText().toString();
                String ibu = etIbu.getText().toString();
                String alamat = etAlamat.getText().toString();
                String notelp_orangtua = etNotelpOrangtua.getText().toString();

                daftarSiswa(nama,nis,kelas,jurusan,email,notelp,ayah,ibu,alamat,notelp_orangtua);
            }
        });
        return v;
    }

    private void daftarSiswa(String nama, String nis, String kelas, String jurusan, String email,
                             String notelp, String ayah, String ibu, String alamat, String notelp_orangtua) {

        Call<ResponseBody> call = mApiInterface.postDaftarSiswa(nama,nis,kelas,jurusan,email,notelp,ayah,
                ibu,alamat,notelp_orangtua);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(getActivity(), "Berhasil daftar", Toast.LENGTH_SHORT).show();
                etNamaSiswa.setText("");
                etNis.setText("");
                etKelas.setText("");
                etJurusan.setText("");
                etEmail.setText("");
                etNotelp.setText("");
                etAyah.setText("");
                etIbu.setText("");
                etAlamat.setText("");
                etNotelpOrangtua.setText("");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), "Jaringan bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
