package com.example.bimbingankonseling.Rest;

import com.example.bimbingankonseling.Model.DataPelanggaran;
import com.example.bimbingankonseling.Model.ResponseLogin;
import com.example.bimbingankonseling.Model.ResponsePelanggaran;
import com.example.bimbingankonseling.Model.ResponsePoinSiswa;
import com.example.bimbingankonseling.Model.ResponseSiswa;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiInterface {
    @GET("wsgetpelanggaran.php")
    Call<ResponsePelanggaran> getDataPelanggaran();

    @GET("wsgetsiswa.php")
    Call<ResponseSiswa> getDataSiswa();

    @FormUrlEncoded
    @POST("wspengguna.php")
    Call<ResponseLogin> postLogin(@Field("nama_pengguna") String nama_pengguna,
                                  @Field("kata_sandi") String kata_sandi,
                                  @Field("level") String level);

    @FormUrlEncoded
    @POST("wspostpoinsiswa.php")
    Call<ResponsePoinSiswa> postPoinSiswa(@Field("id_siswa") String id_siswa);

    @FormUrlEncoded
    @POST("wspostpelanggaransiswa.php")
    Call<ResponsePelanggaran> postPelanggaranSiswa(@Field("id_pelanggaran") String id_pelanggaran);

    @FormUrlEncoded
    @POST("wspostdatasiswa.php")
    Call<ResponseSiswa> postDataSiswa(@Field("id_siswa") String id_siswa);

    @FormUrlEncoded
    @POST("wspostpoin.php")
    Call<ResponseBody> postPoin(@Field("id_siswa") String id_siswa,
                                @Field("id_pelanggaran") String id_pelanggaran,
                                @Field("poin") String poin);

    @FormUrlEncoded
    @POST("wspostdaftarsiswa.php")
    Call<ResponseBody> postDaftarSiswa(@Field("nama") String nama,
                                       @Field("nis") String nis,
                                       @Field("kelas") String kelas,
                                       @Field("jurusan") String jurusan,
                                       @Field("email") String email,
                                       @Field("notelp") String notelp,
                                       @Field("nama_ayah") String nama_ayah,
                                       @Field("nama_ibu") String nama_ibu,
                                       @Field("alamat") String alamat,
                                       @Field("notelp_orangtua") String notelp_orangtua);
}

