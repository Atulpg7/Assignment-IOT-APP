package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    EditText c_id,d_id,m_id,m_name,s_ip;
    Button btn_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Setting Toolbar
        getSupportActionBar().setTitle("Configure Settings");



        getReferences();

        checkOldSettings();

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cid = c_id.getText().toString();
                String did = d_id.getText().toString();
                String mid = m_id.getText().toString();
                String mname = m_name.getText().toString();
                String sip = s_ip.getText().toString();

                if(checkLen(cid) || checkLen(did) || checkLen(mid) || checkLen(mname) || checkLen(sip)){
                    Toast.makeText(SettingsActivity.this, "All fields are required !", Toast.LENGTH_SHORT).show();
                }else{

                    MyPrefs.editor= getSharedPreferences(MyPrefs.MY_PREF_NAME,MODE_PRIVATE).edit();
                    MyPrefs.editor.putString("c_id",cid);
                    MyPrefs.editor.putString("d_id",did);
                    MyPrefs.editor.putString("m_id",mid);
                    MyPrefs.editor.putString("m_name",mname);
                    MyPrefs.editor.putString("s_ip",sip);
                    MyPrefs.editor.apply();

                    Toast.makeText(SettingsActivity.this, "Settings Saved", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }

    private void checkOldSettings() {

        MyPrefs.prefs = getSharedPreferences(MyPrefs.MY_PREF_NAME,MODE_PRIVATE);
        String cid = MyPrefs.prefs.getString("c_id","");
        String did = MyPrefs.prefs.getString("d_id","");
        String mid = MyPrefs.prefs.getString("m_id","");
        String mname = MyPrefs.prefs.getString("m_name","");
        String sip = MyPrefs.prefs.getString("s_ip","");

        //Toast.makeText(this, ""+cid+"\n"+did, Toast.LENGTH_SHORT).show();

        if (!checkLen(cid)){
            c_id.setText(cid);
        }

        if (!checkLen(did)){
            d_id.setText(did);
        }

        if (!checkLen(mid)){
            m_id.setText(mid);
        }

        if (!checkLen(mname)){
            m_name.setText(mname);
        }

        if (!checkLen(sip)){
            s_ip.setText(sip);
        }

    }

    private boolean checkLen(String s) {
        return s.equals("");
    }

    private void getReferences() {

        c_id = findViewById(R.id.c_id);
        d_id = findViewById(R.id.d_id);
        m_id = findViewById(R.id.m_id);
        m_name = findViewById(R.id.m_name);
        s_ip = findViewById(R.id.s_ip);
        btn_submit = findViewById(R.id.btn_submit);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SettingsActivity.this,MainActivity.class));
        finish();
    }
}