package com.example.demo1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class AccountActivity extends Activity implements View.OnClickListener{
    private EditText accountLoginName;
    private EditText accountLoginPassword;
    private Button loginBtn;
    private TextView registerAccountBtn;
    private ProgressBar progressBar;
    private LinearLayout llLogin;
    private ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);

        //第一：默认初始化
        //Bmob.initialize(this, "7a69e455af36e9d2814f4016ece2e3c9");

        initView();
        initData();
        initListener();
    }

    private void initView() {
        accountLoginName = (EditText) findViewById(R.id.i8_accountLogin_name);
        accountLoginPassword = (EditText) findViewById(R.id.i8_accountLogin_password);
        loginBtn = (Button) findViewById(R.id.i8_accountLogin_toLogin);
        registerAccountBtn = (TextView) findViewById(R.id.register_account_btn);
        progressBar = (ProgressBar) findViewById(R.id.pb);
        llLogin = (LinearLayout) findViewById(R.id.ll_login);
        toggleButton = findViewById(R.id.i8_usercenter_autoLogin);
    }

    private void initData() {

    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        llLogin.setVisibility(View.GONE);
    }

    private void hiddenProgressBar() {
        progressBar.setVisibility(View.GONE);
        llLogin.setVisibility(View.VISIBLE);
    }

    private void initListener() {
        loginBtn.setOnClickListener(this);
        registerAccountBtn.setOnClickListener(this);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    //选择状态 显示明文--设置为可见的密码
                    accountLoginPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    //默认状态显示密码--设置文本 要一起写才能起作用 InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
                    accountLoginPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.i8_accountLogin_toLogin:
                String name = accountLoginName.getText().toString().trim();//获取用户名输入信息并去空格
                String password = accountLoginPassword.getText().toString().trim();

                //查询用户是否存在

                if (name.isEmpty()) {
                    Toast.makeText(AccountActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.isEmpty()) {
                    Toast.makeText(AccountActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                doLogin(name, password);
                break;
            case R.id.register_account_btn:
                //跳转到注册界面
                Intent intent = new Intent(AccountActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * @param msg 打印信息
     */
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    private void doLogin(final String name, final String password) {

        // 验证用户名与密码

        Intent intent = new Intent(AccountActivity.this, InfoCompleteActivity.class);
        startActivity(intent);
        finish();
    }
}
