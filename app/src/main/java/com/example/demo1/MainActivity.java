package com.example.demo1;

import android.content.DialogInterface;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_menu_manage)
                    .setTitle("个人信息管理：")
                    .setView(R.layout.personal_info_management)
                    .setPositiveButton("完成",null)
                    .setNegativeButton("取消",null)
                    .show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
