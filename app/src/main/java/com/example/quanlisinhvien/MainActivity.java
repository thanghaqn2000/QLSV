package com.example.quanlisinhvien;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
public class MainActivity extends AppCompatActivity {
    ListView lvSinhVien;
    public static String DATABASE_NAME="QuanliSV.db";
    String DB_PATH_SUFFIX="/databases/";
    public static SQLiteDatabase database=null;
    ArrayAdapter<SinhVien> adapter;
    public static SinhVien selectedContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvents();
        processcopy();
        showData();
    }
    public void showData() {
        try{
            database=openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
            //Cursor cursor=database.query("Contact",null,"name=?",new String[]{"toan"},null,null,null);
            Cursor cursor=database.rawQuery("select * from SinhVien",null);
            adapter.clear();
            while (cursor.moveToNext()){
                String idSv=cursor.getString(0);
                String TenSV=cursor.getString(1);
                String LopSV=cursor.getString(2);
                String Email=cursor.getString(3);
                int SDT=cursor.getInt(4);
                SinhVien sinhvien=new SinhVien(idSv,TenSV,LopSV,Email,SDT);
                adapter.add(sinhvien);
            }
            cursor.close();
        }catch (Exception ex){
            Toast.makeText(MainActivity.this,ex.toString(),Toast.LENGTH_LONG).show();
        }
    }
    private void processcopy() {
        try {
            File dbfile = getDatabasePath(DATABASE_NAME);
            if (!dbfile.exists()) {
                copyDatabaseFromAsset();
                Toast.makeText(MainActivity.this,"Them data thanh cong",Toast.LENGTH_LONG).show();
            }
        }catch (Exception ex){
            Toast.makeText(MainActivity.this,ex.toString(),Toast.LENGTH_LONG).show();
            Log.e("Loi",ex.toString());
        }
    }
    private void addControl() {
        lvSinhVien=findViewById(R.id.lvSinhVien);
        adapter=new ArrayAdapter<SinhVien>(MainActivity.this,android.R.layout.simple_list_item_1);
        lvSinhVien.setAdapter(adapter);
       registerForContextMenu(lvSinhVien);
    }
    private void addEvents() {
        lvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedContact=adapter.getItem(i);
            }
        });
        lvSinhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedContact=adapter.getItem(i);
                return false;
            }
        });
    }
    private String getDatabasePath() {
        return getApplicationInfo().dataDir+DB_PATH_SUFFIX+DATABASE_NAME;
    }
    private void copyDatabaseFromAsset() {
        try{
            InputStream myInput=getAssets().open(DATABASE_NAME);
            String outFileName=getDatabasePath();
            File f=new File(getApplicationInfo().dataDir+DB_PATH_SUFFIX);
            if(!f.exists()){
                f.mkdir();
            }
            OutputStream myOutput=new FileOutputStream(outFileName);
            byte []buffer=new byte[1024];
            int length;
            while ((length=myInput.read(buffer))>0){
                myOutput.write(buffer,0,length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }catch (Exception ex){
            Log.e("Loi",ex.toString()) ;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.itemThem){
            Intent intent=new Intent(MainActivity.this,Them_Sua_Xoa_SV.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.item_Sua){
            Intent intent=new Intent(MainActivity.this,UpdateSV.class);
            startActivity(intent);
        }else if (item.getItemId()==R.id.item_Xoa){
            try{
                    int kq=database.delete("SinhVien","idSV=?",new String[]{selectedContact.getIdSV()+""});
                    if (kq>0){
                        Toast.makeText(MainActivity.this,"Xoa thanh cong",Toast.LENGTH_LONG).show();
                        showData();
                    }else {
                        Toast.makeText(MainActivity.this,"xoa that bai",Toast.LENGTH_LONG).show();
                    }

            }catch (Exception ex){
                Toast.makeText(MainActivity.this,ex.toString(),Toast.LENGTH_LONG).show();
            }
        }
        return super.onContextItemSelected(item);
    }
}