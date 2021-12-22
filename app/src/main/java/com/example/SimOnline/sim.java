package com.example.SimOnline;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.SimOnline.FirebaseHelper;
import com.example.SimOnline.ListMhsActivity;
import com.example.SimOnline.MhsModel;
import com.example.SimOnline.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class sim extends AppCompatActivity {
    ArrayList<MhsModel> mhsList ;
    MhsModel mm ;
    //    DbHelper db ;
    FirebaseHelper db ;
    boolean isEdit ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edNama = (EditText) findViewById(R.id.edNama);
        EditText edAlamat = (EditText) findViewById(R.id.edAlamat);
        EditText edNoHp = (EditText) findViewById(R.id.edNoHp);
        EditText edTglLahir = (EditText) findViewById(R.id.edTglLahir);
        Button btnMasukkan = (Button) findViewById(R.id.btnMasukkan);

        mhsList = new ArrayList<>();

        // ArrayList<MhsModel> mhsData = getIntent()
        isEdit = false ;

        Intent intent_main = getIntent();
        if(intent_main.hasExtra("mhsData")){
            mm = intent_main.getExtras().getParcelable("mhsData");
            edNama.setText(mm.getNama());
            edAlamat.setText(mm.getAlamat());
            edNoHp.setText(mm.getNohp());
            edTglLahir.setText(mm.getTgllahir());

            isEdit = true ;

            btnMasukkan.setBackgroundColor(Color.GRAY);
            btnMasukkan.setText("EDIT");

        }

//        db = new DbHelper(getApplicationContext());
        db = new FirebaseHelper();

        btnMasukkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isian_nama = edNama.getText().toString();
                String isian_alamat = edAlamat.getText().toString();
                String isian_noHp = edNoHp.getText().toString();
                String isian_tgllahir = edTglLahir.getText().toString();

                if(isian_nama.isEmpty() || isian_alamat.isEmpty() || isian_noHp.isEmpty()|| isian_tgllahir.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Isian masih kosong", Toast.LENGTH_SHORT).show();
                }else {
                    //    mhsList.add (new MhsModel(-1, isian_nama, isian_nim, isian_noHp));

                    //    boolean stts ;

                    if(!isEdit) {
                        mm = new MhsModel(isian_nama, isian_alamat, isian_noHp, isian_tgllahir);
                        db.simpan(mm).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(), "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();

                                edNama.setText("");
                                edAlamat.setText("");
                                edNoHp.setText("");
                                edTglLahir.setText("");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Gagal : " +e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });


                    }else{
                        mm = new MhsModel(mm.getKey(), isian_nama, isian_alamat, isian_noHp, isian_tgllahir);
                        db.ubah(mm).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(), "Data Berhasil Diubah", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Gagal : " +e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    //    if (stts){
                    //        Toast.makeText(getApplicationContext(), "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                    //    }else{
                    //        Toast.makeText(getApplicationContext(), "Data Gagal Disimpan", Toast.LENGTH_SHORT).show();
                    //    }

                    // intent_list.putParcelableArrayListExtra("mhsList", mhsList);
                    // startActivity(intent_list);
                }
            }

        });

        Button btnLihat = (Button) findViewById(R.id.btnLihat);
        btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mhsList = db.list();

                if(mhsList.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Belum ada data", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent_list = new Intent(com.example.SimOnline.sim.this, ListMhsActivity.class);
                    intent_list.putParcelableArrayListExtra("mhsList", mhsList);
                    startActivity(intent_list);
                }
            }
        });
    }
}