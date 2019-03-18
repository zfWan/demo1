package com.example.demo1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PersonalInfoManActivity extends Activity {
    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtPhone;
    private EditText edtEmail;
    private RadioGroup rgGender;
    private RadioButton rbMale;
    private CheckBox cbTravel;
    private CheckBox cbMusic;
    private CheckBox cbFood;
    private Button button_reset;

    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 利用布局资源文件设置用户界面
        setContentView(R.layout.personal_info_management);

        // 通过资源标识获取控件实例
        edtUsername = (EditText) findViewById(R.id.edt_username);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        edtPhone = (EditText) findViewById(R.id.edt_phone);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        rgGender = (RadioGroup) findViewById(R.id.rg_gender);
        rbMale = (RadioButton) findViewById(R.id.rb_male);
        cbTravel = (CheckBox) findViewById(R.id.cb_travel);
        cbMusic = (CheckBox) findViewById(R.id.cb_music);
        cbFood = (CheckBox) findViewById(R.id.cb_food);

        button_reset = findViewById(R.id.btn_reset);
        button_reset.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("你是谁——+*******************？");
                edtUsername.setText("");
                edtPassword.setText("");
                edtPhone.setText("");
                edtEmail.setText("");
                rbMale.setChecked(true);
                cbTravel.setChecked(false);
                cbMusic.setChecked(false);
                cbFood.setChecked(false);
            }
        });
    }

    /**
     * 提交事件处理方法
     *
     * @param view
     */
    public void doSubmit(View view) {
        /* 获取用户设置的信息 */
        // 获取用户名
        String username = edtUsername.getText().toString().trim();
        // 获取密码
        String password = edtPassword.getText().toString().trim();
        // 获取电话
        String phone = edtPhone.getText().toString().trim();
        // 获取邮箱
        String email = edtEmail.getText().toString().trim();
        // 获取性别
        gender = ((RadioButton) findViewById(rgGender.getCheckedRadioButtonId())).getText().toString();
        // 获取兴趣
        StringBuilder builder = new StringBuilder();
        if (cbTravel.isChecked()) {
            builder.append(cbTravel.getText().toString() + " ");
        }
        if (cbMusic.isChecked()) {
            builder.append(cbMusic.getText().toString() + " ");
        }
        if (cbFood.isChecked()) {
            builder.append(cbFood.getText().toString());
        }
        String hobby = builder.toString().trim();

        // 在当前窗口显示注册信息
        String regInfo = username + "\n" + password + "\n" + phone + "\n" + email + "\n" + gender + "\n" + hobby;
        Toast.makeText(this, regInfo, Toast.LENGTH_SHORT).show();

        // 创建意图，从MainActivity跳转到ResultActivity    第二个参数为跳转页面
        Intent intent = new Intent(this, null);
        // 创建数据包封装用户要提交的数据
        Bundle data = new Bundle();
        data.putString("username", username);
        data.putString("password", password);
        data.putString("phone", phone);
        data.putString("email", email);
        data.putString("gender", gender);
        data.putString("hobby", hobby);
        // 将数据包作为意图的附加数据
        intent.putExtras(data);
        // 按意图启动组件
        startActivity(intent);
    }

    /**
     * 重置事件处理方法
     *
     * @param view
     */
    public void doReset(View view) {
        edtUsername.setText("");
        edtPassword.setText("");
        edtPhone.setText("");
        edtEmail.setText("");
        rbMale.setChecked(true);
        cbTravel.setChecked(false);
        cbMusic.setChecked(false);
        cbFood.setChecked(false);
    }
}
