package com.bawei.mydemoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 请输入用户名
     */
    private EditText mMyphone;
    /**
     * 请输入用户密码
     */
    private EditText mMypass;
    /**
     * 注册
     */
    private Button mBtnRegest;
    /**
     * 登录
     */
    private Button mBtnLogin;
    private  String phone;
    private  String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mMyphone = (EditText) findViewById(R.id.myphone);
        mMypass = (EditText) findViewById(R.id.mypass);
        mBtnRegest = (Button) findViewById(R.id.btn_regest);
        mBtnRegest.setOnClickListener(this);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_regest:
                String phone = mMyphone.getText().toString(); //拿到用户输入的值
                String pass = mMypass.getText().toString();//拿到密码
                if (TextUtils.isEmpty(phone) || phone.length() == 0){
                    Toast.makeText(this, "手机号不能为空哦", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pass.length()<6){
                    Toast.makeText(this, "密码长度至少为6位", Toast.LENGTH_SHORT).show();
                    return;
                }


                break;
            case R.id.btn_login:
                /* phone = mMyphone.getText().toString(); //拿到用户输入的值
                 pass = mMypass.getText().toString();//拿到密码
                if (TextUtils.isEmpty(phone) || phone.length() == 0){
                    Toast.makeText(this, "手机号不能为空哦", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pass.length()<6){
                    Toast.makeText(this, "密码长度至少为6位", Toast.LENGTH_SHORT).show();
                    return;
                }*/

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        //okhttp网络请求
                        OkHttpClient okHttpClient = new OkHttpClient();
                        Request request = new Request.Builder().url("http://apicloud.mob.com/v1/weather/type?key=22ecf6c32440e").build();
                        Call call = okHttpClient.newCall(request);

                        Response response = null;
                        try {
                            response = call.execute();
                            if (response.isSuccessful()){
                                ResponseBody responseBody = response.body();
                                String jsonstr = responseBody.string();
                                Log.i("网络请求get同步","request"+jsonstr);
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                }).start();




                break;
        }
    }
}
