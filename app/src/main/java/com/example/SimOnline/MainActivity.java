package com.example.SimOnline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String username_valid = "SIM";
        String password_valid = "ONLINE";

        EditText edusername = (EditText) findViewById(R.id.edusername);
        EditText edpassword = (EditText) findViewById(R.id.edpassword);

        Button btnsubmit = (Button) findViewById(R.id.btnsubmit);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((edusername.getText().toString().trim().isEmpty()) || (edpassword.getText().toString().trim().isEmpty()) ) {
                    // JIKA KEDUA ATAU SALAH SATU ISIAN ADA YANG KOSONG
                    Toast.makeText(getApplicationContext(), "Isian tidak valid", Toast.LENGTH_LONG).show();
                }else{
                    // JIKA KEDUA ISIAN TELAH TERISI
                    if ((edusername.getText().toString().trim().equals(username_valid)) && (edpassword.getText().toString().trim().equals(password_valid))
                    ) {
                        //Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();

                        Intent intent_dashboard = new Intent(MainActivity.this, sim.class);
                        intent_dashboard.putExtra("Username",username_valid);
                        startActivity(intent_dashboard);

                    } else {
                        Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}