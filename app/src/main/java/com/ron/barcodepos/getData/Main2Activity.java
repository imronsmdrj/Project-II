package com.ron.barcodepos.getData;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ron.barcodepos.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends AppCompatActivity {

    private EditText etID, etNamaBarang, etSatuan, etHarga_pokok, etHarga_eceran, etStok, etKategori;
    private Button btnScan, btnSave;
    //    private APIService mAPIService;
    public static final String URL = "http://192.168.100.171/retrofit/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //editText
        etID = (EditText) findViewById(R.id.etID);
        etNamaBarang = (EditText) findViewById(R.id.etNamaBarang);
        etSatuan = (EditText) findViewById(R.id.etSatuan);
        etHarga_pokok = (EditText) findViewById(R.id.etHarga_pokok);
        etHarga_eceran = (EditText) findViewById(R.id.etHarga_eceran);
        etStok = (EditText) findViewById(R.id.etStok);
        etKategori = (EditText) findViewById(R.id.etKategori);

        //button
        btnScan = (Button) findViewById(R.id.btnScan);
        btnSave = (Button) findViewById(R.id.btnSave);

        //daftarkan listener untuk memanggil fungsi scan barcode biasa
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanBarcode("PRODUCT_MODE");
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mengambil data dari editext
                String bid = etID.getText().toString();
                String bnama = etNamaBarang.getText().toString();
                String bsatuan = etSatuan.getText().toString();
                String bharga_pokok = etHarga_pokok.getText().toString();
                String bharga_jual = etHarga_eceran.getText().toString();
                String bstok = etStok.getText().toString();
                String bkategori_id = etKategori.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiInterface api = retrofit.create(ApiInterface.class);
                Call<Contacts> call = api.input(bid, bnama, bsatuan, bharga_pokok, bharga_jual, bstok, bkategori_id);
                call.enqueue(new Callback<Contacts>() {
                    @Override
                    public void onResponse(Call<Contacts> call, Response<Contacts> response) {
                        String Contacts = response.body().getValue();
                        String message = response.body().getMessage();
                        if (Contacts.equals("1")) {
                            Toast.makeText(Main2Activity.this, message, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Main2Activity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Contacts> call, Throwable t) {
                        Toast.makeText(Main2Activity.this, "Jaringan Error!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void scanBarcode(String mode) {
        try {
            //buat intent untuk memanggil fungsi scan pada aplikasi zxing
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", mode); // product_mode for bar codes
            startActivityForResult(intent, 1);

        } catch (Exception e) {
            Uri marketUri = Uri.parse("market//details?id=com.google.zxing.client.android");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
            startActivity(marketIntent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //tangkap hasil dari scan
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                etID.setText("");
                String contents = data.getStringExtra("SCAN_RESULT");
                Toast.makeText(getBaseContext(), "Hasil :" + contents, Toast.LENGTH_SHORT).show();
                etID.setText(contents);
            }
        }
    }
}

