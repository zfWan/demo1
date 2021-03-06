package com.example.demo1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class RegisterActivity extends Activity implements View.OnClickListener {
    private EditText accountRegisterName;
    private EditText accountRegisterPassword;
    private EditText accountRegisterAgainPassword;
    private EditText nickName;
    private ToggleButton toggleButton;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_form);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        accountRegisterName = (EditText) findViewById(R.id.i8_accountRegister_name);
        accountRegisterPassword = (EditText) findViewById(R.id.i8_accountRegister_password);
        accountRegisterAgainPassword = findViewById(R.id.i8_accountRegister_again_password);
        registerBtn = (Button) findViewById(R.id.i8_accountRegistern_toRegister);
        toggleButton = findViewById(R.id.i8_usercenter_autoLogin);
        nickName = findViewById(R.id.i8_accountRegister_nickName);
    }

    private void initData() {

    }

    private void initListener() {
        registerBtn.setOnClickListener(this);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(!isChecked){
                    //选择状态 显示明文--设置为可见的密码
                    accountRegisterPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    accountRegisterAgainPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    //默认状态显示密码--设置文本 要一起写才能起作用 InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
                    accountRegisterPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    accountRegisterAgainPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        String name = accountRegisterName.getText().toString().trim();//获取用户名输入信息并去空格
        String registerNickName = nickName.getText().toString().trim();
        String password = accountRegisterPassword.getText().toString().trim();
        String againPassword = accountRegisterAgainPassword.getText().toString().trim();
        switch (v.getId()) {
            case R.id.i8_accountRegistern_toRegister:
                doRegister(name, registerNickName, password, againPassword);
                break;
            default:
                break;
        }
    }



    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    private void doRegister(final String name, String nickName, String password, String againPassword) {

        //查询用户是否存在
        if (name.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (nickName.isEmpty()){
            Toast.makeText(RegisterActivity.this, "昵称不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (againPassword.isEmpty()){
            Toast.makeText(RegisterActivity.this, "请确认密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.equals(againPassword)){
            Toast.makeText(RegisterActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
            return;
        }

        showToast("注册成功!");
        Intent intent = new Intent(RegisterActivity.this, AccountActivity.class);
        startActivity(intent);
        finish();
    }
}
