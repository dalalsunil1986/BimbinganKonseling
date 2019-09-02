package com.example.bimbingankonseling;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bimbingankonseling.Adapter.AdapterPelanggaran;
import com.example.bimbingankonseling.Adapter.AdapterSiswa;
import com.example.bimbingankonseling.Model.DataPelanggaran;
import com.example.bimbingankonseling.Model.DataSiswa;
import com.example.bimbingankonseling.Model.ResponsePelanggaran;
import com.example.bimbingankonseling.Model.ResponseSiswa;
import com.example.bimbingankonseling.Rest.ApiClient;
import com.example.bimbingankonseling.Rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSatuFragment extends Fragment {

    private ApiInterface mApiInterface;
    private RecyclerView rvSiswa;
    private AdapterSiswa mAdapter;
    private List<DataSiswa> dataSiswas = new ArrayList<>();

    public FragmentSatuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment_satu, container, false);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        rvSiswa = v.findViewById(R.id.rvSiswa);
        rvSiswa.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvSiswa.setLayoutManager(layoutManager);

        getDataSiswa();
        return v;
    }

    private void getDataSiswa() {
        Call<ResponseSiswa> dataSiswa = mApiInterface.getDataSiswa();
        dataSiswa.enqueue(new Callback<ResponseSiswa>() {
            @Override
            public void onResponse(Call<ResponseSiswa> call, Response<ResponseSiswa> response) {
                List<DataSiswa> data = response.body().getData();
                mAdapter = new AdapterSiswa(getActivity(),data);
                rvSiswa.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<ResponseSiswa> call, Throwable t) {
                Log.e("cek ","Error");
            }
        });
    }

}
