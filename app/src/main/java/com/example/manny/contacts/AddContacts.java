package com.example.manny.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContacts extends AppCompatActivity {

    private EditText mInputName;
    private EditText mInputTel;
    private Button mButtonConfirm;
    private Button mButtonCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        mInputName = (EditText) findViewById(R.id.edit_text_name);
        mInputTel = (EditText) findViewById(R.id.edit_text_tel);

        mButtonConfirm = (Button) findViewById(R.id.btn_confirm);
        mButtonCancel = (Button) findViewById(R.id.btn_cancel);

        mButtonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msgName = String.valueOf(mInputName.getText().toString());
                String msgTel = String.valueOf(mInputTel.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("name",msgName);
                intent.putExtra("tel",msgTel);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_contact, menu);
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
