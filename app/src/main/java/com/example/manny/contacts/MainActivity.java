package com.example.manny.contacts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import static com.example.manny.contacts.MyHelper.COL_ID;
import static com.example.manny.contacts.MyHelper.COL_NAME;
import static com.example.manny.contacts.MyHelper.COL_PHONE_NUMBER;
import static com.example.manny.contacts.MyHelper.TABLE_NAME;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivtiy";
    private MyHelper dbHelper;
    private SQLiteDatabase db;
    private SimpleCursorAdapter adapter;

    private String mMsgId;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == RESULT_OK){
                String msgName = data.getStringExtra("name");
                String msgTel  = data.getStringExtra("tel");
                db.insert(TABLE_NAME, null, dbHelper.addContent(msgName, msgTel));
                adapterUpdate();
            }
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MyHelper(this);
        db = dbHelper.getWritableDatabase();


        Cursor cursor = readAllData();
        adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cursor,
                                            new String[] {COL_NAME, COL_PHONE_NUMBER},
                                            new int[] {android.R.id.text1 , android.R.id.text2
                                            });
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mMsgId = String.valueOf(id);

                //Cursor cursor = readAllData();
                //Log.d("MainActivity",String.valueOf(cursor.getInt((int) id)));
                // String man = cursor.getString((int)id);
                //System.out.print(man);
                new AlertDialog.Builder(MainActivity.this).setTitle("You want to delete").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.execSQL("DELETE FROM contacts WHERE _id = " + mMsgId);
                        adapterUpdate();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });
        Button btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddContacts.class);
                startActivityForResult(intent, 0);
            }
        });
    }
    private Cursor readAllData(){
        String[] columms = {
                COL_ID , COL_NAME , COL_PHONE_NUMBER
        };
        Cursor cursor = db.query(TABLE_NAME,columms,null,null,null,null,null);
        return cursor;
    }
    private void adapterUpdate(){
        Cursor cursor = readAllData();
        adapter.changeCursor(cursor);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
