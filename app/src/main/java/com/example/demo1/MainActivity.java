package com.example.demo1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private View first,second,third;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    first.setVisibility(View.VISIBLE);
                    second.setVisibility(View.GONE);
                    third.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_dashboard:
                    first.setVisibility(View.GONE);
                    second.setVisibility(View.VISIBLE);
                    third.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_notifications:
                    first.setVisibility(View.GONE);
                    second.setVisibility(View.GONE);
                    third.setVisibility(View.VISIBLE);
                    return true;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        可删

        String str =getIntent().getStringExtra("username");
        TextView textView =findViewById(R.id.textView_health_32);
        textView.setText(str);


//     底部导航栏
        first = findViewById(R.id.first_main);
        second = findViewById(R.id.second_main);
        third = findViewById(R.id.third_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            // 弹出修改对话框
            new AlertDialog.Builder(this)
                    .setTitle("请输入紧急求助电话：")
                    .setIcon(android.R.drawable.ic_menu_call)
                    .setView(new EditText(this))
                    .setPositiveButton("确定", null)
                    .setNegativeButton("取消", null)
                    .show();
        } else if (id == R.id.nav_gallery) {
            new AlertDialog.Builder(this)
                    .setTitle("请输入求助短信内容：")
                    .setIcon(android.R.drawable.ic_menu_set_as)
                    .setView(new EditText(this))
                    .setPositiveButton("确定", null)
                    .setNegativeButton("取消", null)
                    .show();
        } else if (id == R.id.nav_slideshow) {
            //    指定下拉列表的显示数据
            final String[] settings = {"手动报警", "自动报警", "智能报警"};
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_menu_manage)
                    .setTitle("请选择你的设置：")
                    .setItems(settings, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Toast.makeText(MainActivity.this, "设置为：" + settings[which], Toast.LENGTH_SHORT).show();
                        }
                    })
                    .show();
        } else if (id == R.id.nav_manage) {

//            获取当前view对象，用来获取personal_info_management中的控件
            LayoutInflater inflater = LayoutInflater.from(getBaseContext());
            final View view = inflater.inflate(R.layout.personal_info_management, null);

//          获取控件
            final EditText edtUsername = view.findViewById(R.id.edt_username);
            final EditText edtPassword = view.findViewById(R.id.edt_password);
            final EditText edtPhone = view.findViewById(R.id.edt_phone);
            final EditText edtEmail = view.findViewById(R.id.edt_email);
            final RadioGroup rgGender = view.findViewById(R.id.rg_gender);
            final RadioButton rbMale = view.findViewById(R.id.rb_male);
            final CheckBox cbTravel = view.findViewById(R.id.cb_travel);
            final CheckBox cbMusic = view.findViewById(R.id.cb_music);
            final CheckBox cbFood = view.findViewById(R.id.cb_food);
            Button button_reset = view.findViewById(R.id.btn_reset);
            Button button_submit = view.findViewById(R.id.btn_submit);

//控件点击监听

//            重置按钮
            button_reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
//              提交按钮
            button_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
                    String gender = ((RadioButton) view.findViewById(rgGender.getCheckedRadioButtonId())).getText().toString();
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

                    // 创建意图，从MainActivity跳转到ResultActivity    第二个参数为跳转页面
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
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
            });

//            dialog弹出
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_menu_manage)
                    .setTitle("个人信息管理：")
                    .setView(view)
                    .show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
