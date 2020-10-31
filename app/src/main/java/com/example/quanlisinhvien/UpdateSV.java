package com.example.quanlisinhvien;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class UpdateSV extends AppCompatActivity {
    EditText edtIdsua,edttensua,edtlopsua,edtemaisua,edtsdtsua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_s_v);
        addcontrol();
    }
    private void addcontrol() {
        edtIdsua=findViewById(R.id.edtIdsua);
        edttensua=findViewById(R.id.edttensua);
        edtlopsua=findViewById(R.id.edtlopsua);
        edtemaisua=findViewById(R.id.edtemaisua);
        edtsdtsua=findViewById(R.id.edtsdtsua);
        edtIdsua.setText(MainActivity.selectedContact.getIdSV()+"");
        edttensua.setText(MainActivity.selectedContact.getTenSV()+"");
        edtlopsua.setText(MainActivity.selectedContact.getLopSV()+"");
        edtemaisua.setText(MainActivity.selectedContact.getEmail()+"");
        edtsdtsua.setText(MainActivity.selectedContact.getSDT()+"");
        edtIdsua.setEnabled(false);
    }
    public void thoatsv(View view) {
        finish();
    }
    public void updatesv(View view) {
        ContentValues newvalues=new ContentValues();
        newvalues.put("TenSV",edttensua.getText().toString());
        newvalues.put("LopSV",edtlopsua.getText().toString());
        newvalues.put("Email",edtemaisua.getText().toString());
        newvalues.put("SDT",edtsdtsua.getText().toString());
        try {
        int kq=MainActivity.database.update("SinhVien",newvalues,"idSV=?",new String[]{edtIdsua.getText().toString()});
        if (kq>0){
            Toast.makeText(UpdateSV.this,"Cập nhật thành công",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(UpdateSV.this,"Sửa thất bại",Toast.LENGTH_SHORT).show();
        }}catch (Exception ex){
            Toast.makeText(UpdateSV.this,ex.toString(),Toast.LENGTH_LONG).show();
        }
    }
}