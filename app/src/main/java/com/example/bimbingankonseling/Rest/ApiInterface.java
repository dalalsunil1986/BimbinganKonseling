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
    Call<ResponseLogin>  postLogin(@Field("nama_pengguna") String nama_pengguna,
                                   @Field("kata_sandi") String kata_sandi,
                                   @Field("level") String level);

    @FormUrlEncoded
    @POST("wspostpoinsiswa.php")
    Call<ResponsePoinSiswa>  postPoinSiswa(@Field("id_siswa") String id_siswa);

    @FormUrlEncoded
    @POST("wspostpelanggaransiswa.php")
    Call<ResponsePelanggaran>  postPelanggaranSiswa(@Field("id_pelanggaran") String id_pelanggaran);

    @FormUrlEncoded
    @POST("wspostdatasiswa.php")
    Call<ResponseSiswa>  postDataSiswa(@Field("id_siswa") String id_siswa);

    @FormUrlEncoded
    @POST("wspostpoin.php")
    Call<ResponseBody> postPoin(@Field("id_siswa") String id_siswa,
                                @Field("id_pelanggaran") String id_pelanggaran,
                                @Field("poin") String poin);
    /*
    @GET("wsgetdatatambahgarisjalan.php")
    Call<RespDataJalanKosong> getDataJalanKosong();

    @GET("wsgetdatajembatan.php")
    Call<RespDataJembatan> getDataJembatan();

    @GET("wsgetrefstatus.php")
    Call<RespGetRefStatus> getDataRefStatus();

    @GET("wsgetrefkecamatan.php")
    Call<RespGetRefKecamatan> getDataRefKecamatan();

    @GET("wsgetreftipejalan.php")
    Call<RespGetRefTipeJalan> getDataRefTipeJalan();

    @GET("wsgetstatistikjalan.php")
    Call<RespStatistikJalan> getDataStatistik();

    @GET("wsgetstatistikjembatan.php")
    Call<RespStatistikJembatan> getDataStatistikJembatan();

    @FormUrlEncoded
    @POST("wsgetdatajalanbaru.php")
    Call<RespDataJalan> postGetDataJalan(
            @Field("ii_kecamatan") String ii_kecamatan);

    @FormUrlEncoded
    @POST("wsgetdatajembatanbaru.php")
    Call<RespDataJembatan> postGetDataJembatan(
            @Field("kecamatan") String kecamatan);

    @FormUrlEncoded
    @POST("wsgetcarijalan.php")
    Call<RespDataJalan> postCariJalan(
            @Field("nama_ruas_jalan") String nama_ruas_jalan);

    @FormUrlEncoded
    @POST("wsgetcarijembatan.php")
    Call<RespDataJembatan> postCariJembatan(
            @Field("nama_jembatan") String nama_jembatan);

    @FormUrlEncoded
    @POST("wsgetcarinamajalan.php")
    Call<RespDataJalan> postCariNamaJalan(
            @Field("id") String id);

    @FormUrlEncoded
    @POST("wsgetcarinamajembatan.php")
    Call<RespDataJembatan> postCariNamaJembatan(
            @Field("id") String id);


    @FormUrlEncoded
    @POST("wsinputgarisjalan.php")
    Call<ResponseBody> postInputGarisJalan(
            @Field("id_jalan") String id_jalan,
            @Field("v_lat") String v_lat,
            @Field("v_lng") String v_lng);

    @FormUrlEncoded
    @POST("wsgetgarisjalan.php")
    Call<RespDataGarisJalan> getDataGarisJalan(@Field("id_jalan") String id_jalan);

    @FormUrlEncoded
    @POST("wsgetfotojalan.php")
    Call<RespFotoJalan> getFotoJalan(@Field("id_jalan") String id_jalan);

    @FormUrlEncoded
    @POST("wsgetfotojembatan.php")
    Call<RespFotoJembatan> getFotoJembatan(@Field("id_jembatan") String id_jalan);

    @Multipart
    @POST("wsfotojalan.php")
    Call<ResponseBody>  postGambarJalan(@Part("id_jalan") RequestBody id_jalan,
                                        @Part("file_name") RequestBody file_name,
                                        @Part MultipartBody.Part pic);

    @Multipart
    @POST("wsfotojembatan.php")
    Call<ResponseBody>  postGambarJembatan(@Part("id_jembatan") RequestBody id_jembatan,
                                           @Part("file_name") RequestBody file_name,
                                           @Part MultipartBody.Part pic);

    @FormUrlEncoded
    @POST("wsinputjalan.php")
    Call<RespDataJalan>  postDataJalan(
            @Field("kelompok_data_dasar") String kelompok_data_dasar,
            @Field("nama_ruas_jalan") String nama_ruas_jalan,
            @Field("tahun_data") String tahun_data,
            @Field("status") String status,
            @Field("fungsi") String fungsi,
            @Field("mendukung") String mendukung,
            @Field("uraian_mendukung") String uraian_mendukung,
            @Field("i_kode_bidang") String i_kode_bidang,
            @Field("i_kode_data") String i_kode_data,
            @Field("i_kode_infrastruktur") String i_kode_infrastruktur,
            @Field("ii_provinsi") String ii_provinsi,
            @Field("ii_kab_kota") String ii_kab_kota,
            @Field("ii_kecamatan") String ii_kecamatan,
            @Field("ii_kelurahan_desa") String ii_kelurahan_desa,
            @Field("ii_titik_ruas_awal") String ii_titik_ruas_awal,
            @Field("ii_titik_ruas_akhir") String ii_titik_ruas_akhir,
            @Field("ii_kode_patok") String ii_kode_patok,
            @Field("ii_km_awal_ruas") String ii_km_awal_ruas,
            @Field("ii_km_akhir_ruas") String ii_km_akhir_ruas,
            @Field("ii_nama_lintas") String ii_nama_lintas,
            @Field("iii_baik") String iii_baik,
            @Field("iii_sedang") String iii_sedang,
            @Field("iii_rusak_ringan") String iii_rusak_ringan,
            @Field("iii_rusak_berat") String iii_rusak_berat,
            @Field("iii_mantap") String iii_mantap,
            @Field("iii_tidak_mantap") String iii_tidak_mantap,
            @Field("vi_panjang") String vi_panjang,
            @Field("vi_lebar") String vi_lebar,
            @Field("vi_lhrt") String vi_lhrt,
            @Field("vi_vcr") String vi_vcr,
            @Field("vi_tipe_jalan") String vi_tipe_jalan,
            @Field("vi_kapasitas_mst") String vi_kapasitas_mst,
            @Field("vi_tanah") String vi_tanah,
            @Field("vi_macadam") String vi_macadam,
            @Field("vi_asphalt") String vi_asphalt,
            @Field("vi_rigid_beton") String vi_rigid_beton,
            @Field("vi_tahun_penanganan") String vi_tahun_penanganan,
            @Field("vi_jenis_penanganan") String vi_jenis_penanganan,
            @Field("v_lat") String v_lat,
            @Field("v_lng") String v_lng);

    @FormUrlEncoded
    @POST("wsinputjembatan.php")
    Call<ResponseBody>  postDataJembatan(
            @Field("kelompok_data_dasar") String kelompok_data_dasar,
            @Field("nama_jembatan") String nama_jembatan,
            @Field("nama_ruas") String nama_ruas,
            @Field("tahun_data") String tahun_data,
            @Field("status") String status,
            @Field("fungsi") String fungsi,
            @Field("mendukung") String mendukung,
            @Field("uraian_mendukung") String uraian_mendukung,
            @Field("i_kode_bidang") String i_kode_bidang,
            @Field("i_kode_data") String i_kode_data,
            @Field("i_kode_infrastruktur") String i_kode_infrastruktur,
            @Field("ii_provinsi") String ii_provinsi,
            @Field("ii_kab_kota") String ii_kab_kota,
            @Field("ii_kecamatan") String ii_kecamatan,
            @Field("ii_kelurahan_desa") String ii_kelurahan_desa,
            @Field("ii_kode_patok") String ii_kode_patok,
            @Field("ii_km") String ii_km,
            @Field("iii_kondisi_umum") String iii_kondisi_umum,
            @Field("iii_kodisi_bangunan_atas") String iii_kodisi_bangunan_atas,
            @Field("iii_kodisi_lantai") String iii_kodisi_lantai,
            @Field("iii_kodisi_bangunan_bawah") String iii_kodisi_bangunan_bawah,
            @Field("iii_kondisi_daerah_aliran_sungai") String iii_kondisi_daerah_aliran_sungai,
            @Field("iii_tanggal_inspeksi_detail") String iii_tanggal_inspeksi_detail,
            @Field("vi_panjang") String vi_panjang,
            @Field("vi_lebar") String vi_lebar,
            @Field("vi_tipe_bangunan_atas") String vi_tipe_bangunan_atas,
            @Field("vi_lebar_trotoar") String vi_lebar_trotoar,
            @Field("vi_jumlah_bentang") String vi_jumlah_bentang,
            @Field("vi_clearance") String vi_clearance,
            @Field("vi_tahun_pembangunan") String vi_tahun_pembangunan,
            @Field("vi_tahun_penanganan_terakhir") String vi_tahun_penanganan_terakhir,
            @Field("v_lat") String v_lat,
            @Field("v_lng") String v_lng);

    @Multipart
    @POST("wspengaduan.php")
    Call<ResponseBody>  postGambarPengaduan(@Part("nama") RequestBody id_jalan,
                                            @Part("alamat") RequestBody alamat,
                                            @Part("no_hp") RequestBody no_hp,
                                            @Part("jalan_or_jembatan") RequestBody jalan_or_jembatan,
                                            @Part("nama_jalan_or_jembatan") RequestBody nama_jalan_or_jembatan,
                                            @Part("isi_pengaduan") RequestBody isi_pengaduan,
                                            @Part("file_name_foto") RequestBody file_name_foto,
                                            @Part MultipartBody.Part pic);
    @GET("wsDataGis.json")
    Call<List<DataMaps>> getPostMaps();*/
//    @Headers("Content-Type: application/json")
//    @POST("sta_hari")
//    Call<ResponseDataStaHari>  postStaHari(@Body JsonPostHari body);
//
//    @Headers("Content-Type: application/json")
//    @POST("absen_harian_peg")
//    Call<ResponseDataPegawai>  postAbsenPegawai(@Body JsonPostPegawai body);
//
//    @Headers("Content-Type: application/json")
//    @POST("sta_bulan")
//    Call<ResponseDataStaBulan>  postStaBulan(@Body JsonPostBulan body);
//
//    @Headers("Content-Type: application/json")
//    @POST("sta_peg_bulan")
//    Call<ResponseDataPegawaiB>  postAbsenPegawaiBulan(@Body JsonPostPegawai body);
//
//    @Headers("Content-Type: application/json")
//    @POST("sta_tahun")
//    Call<ResponseDataStaTahun>  postStaTahun(@Body JsonPostBulan body);
//
//    @Headers("Content-Type: application/json")
//    @POST("sta_peg_tahun")
//    Call<ResponseDataPegawaiT>  postAbsenPegawaiTahun(@Body JsonPostPegawai body);

}
