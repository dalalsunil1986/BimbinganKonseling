package com.example.bimbingankonseling;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bimbingankonseling.Model.DataLogin;
import com.example.bimbingankonseling.Model.ResponseLogin;
import com.example.bimbingankonseling.Rest.ApiClient;
import com.example.bimbingankonseling.Rest.ApiInterface;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ApiInterface mApiInterface;
    private SessionManager sessionManager;
    private Button btLogin;
    private TextInputLayout etNama,etPassword;
    private Spinner spinlevel;
    ProgressDialog Proses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sessionManager  = new SessionManager(this);
        Proses          = new ProgressDialog(this);

        if (sessionManager.isLoggedIn()){
            HashMap<String, String> user = sessionManager.getUserDetails();
            String level = user.get(SessionManager.KEY_LEVEL);

            if (level.equals("admin")){
                startActivity(new Intent(this,MainActivity.class));
                finish();
            }else{
                startActivity(new Intent(this,OrangTuaActivity.class));
                finish();
            }
        }

        etNama      = findViewById(R.id.etNama);
        etPassword  = findViewById(R.id.etPassword);
        spinlevel   = findViewById(R.id.level);
        btLogin     = findViewById(R.id.btLogin);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama     = etNama.getEditText().getText().toString();
                String sandi    = etPassword.getEditText().getText().toString();
                String level    = spinlevel.getSelectedItem().toString();

                if (TextUtils.isEmpty(nama) && TextUtils.isEmpty(sandi) && level.equals("Guru BK")) {
                    Toast.makeText(LoginActivity.this, "Akun tidak sesuai", Toast.LENGTH_SHORT).show();
                }else {
                    if (level.equals("Guru BK")) {
                        Login(nama,sandi,"admin");
                    }else{
                        Login(nama,sandi,"user");
                    }
                }
            }
        });
    }

    private void Login(String nama, String sandi, String level) {
        Proses.setMessage("Sedang Memproses");
        Proses.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        Proses.show();
        Proses.setCancelable(false);

        Call<ResponseLogin> call = mApiInterface.postLogin(nama,sandi,level);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                Proses.dismiss();
                DataLogin data = response.body().getData();
                String id           = data.getId();
                String idSiswa      = data.getIdSiswa();
                String namaPengguna = data.getNamaPengguna();
                String kataSandi    = data.getKataSandi();
                String level        = data.getLevel();

                sessionManager.createLoginSession(id,idSiswa,namaPengguna,kataSandi,level);
                if (level.equals("admin")){
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Intent i = new Intent(LoginActivity.this, OrangTuaActivity.class);
                    startActivity(i);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Proses.dismiss();
                Toast.makeText(LoginActivity.this, "Jaringan bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
