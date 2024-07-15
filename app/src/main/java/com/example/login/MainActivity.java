package com.example.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class MainActivity extends AppCompatActivity {
      EditText edit,edit1,edit2;
      TextView textview,textView1;
      Button button;

      private static  final  String url = "http://20.0.0.10/MyPhpApi.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        textview = findViewById(R.id.showid);
        textView1 = findViewById(R.id.textview);
        edit=findViewById(R.id.usernameid);
        edit1=findViewById(R.id.emailid);
        edit2=findViewById(R.id.password);

        button = findViewById(R.id.register);




}
    public void registerclick(View view) {


        String name = edit.getText().toString().trim();
        String email = edit1.getText().toString().trim();
        String password = edit2.getText().toString().trim();

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("loading");
        progressDialog.setMessage("Loading....Please...wait....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(),"respose",Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.hide();
                Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {

                Map<String,String> mad = new HashMap<String,String>();
                mad.put("key_name",name);
                mad.put("key_email",email);
                mad.put("key_password",password);

                return mad;



            }
        };

        requestQueue.add(stringRequest);



    }
}




