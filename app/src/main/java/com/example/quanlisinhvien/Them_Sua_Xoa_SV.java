package com.example.quanlisinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Them_Sua_Xoa_SV extends AppCompatActivity {
    EditText edtten,edtid,edtlop,edtemail,edtsdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them__sua__xoa__s_v);
        addcontrol();
    }
    private void addcontrol() {
        edtid=findViewById(R.id.edtId);
        edtten=findViewById(R.id.edtten);
        edtlop=findViewById(R.id.edtlop);
        edtemail=findViewById(R.id.edtemail);
        edtsdt=findViewById(R.id.edtsdt);
    }
    public void themsv(View view) {
        try {
            String idSV = edtid.getText().toString();
            String TenSV=edtten.getText().toString();
            String LopSV=edtlop.getText().toString();
            String Email=edtemail.getText().toString();
            int SDT = Integer.parseInt(edtsdt.getText().toString());
            ContentValues values = new ContentValues();
            values.put("idSV", idSV);
            values.put("TenSV", TenSV);
            values.put("LopSV", LopSV);
            values.put("Email", Email);
            values.put("SDT", SDT);
            long kq = MainActivity.database.insert("SinhVien", null, values);
            if (kq > 0) {
                Toast.makeText(Them_Sua_Xoa_SV.this, "Thêm thành công", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(Them_Sua_Xoa_SV.this, "Thêm thất bại", Toast.LENGTH_LONG).show();
            }
        }catch (Exception ex){
            Toast.makeText(Them_Sua_Xoa_SV.this,ex.toString(),Toast.LENGTH_LONG).show();
        }
    }
    public void suasv(View view) {

    }

    public void thoatsv(View view) {
        finish();
    }
}