package com.example.demo1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.timqi.sectorprogressview.ColorfulRingProgressView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private View first,second,third;
    public com.timqi.sectorprogressview.ColorfulRingProgressView pilao;
    public com.timqi.sectorprogressview.ColorfulRingProgressView zonghe;
    TextView pilaotext;
    TextView zonghetext;
    float pilaonum=40;

//底部菜单栏监听
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
        pilao=(ColorfulRingProgressView) findViewById(R.id.zanshi);
        zonghe=(ColorfulRingProgressView)findViewById(R.id.zanshi2);
        pilaotext=(TextView)findViewById(R.id.pilao);
        zonghetext=(TextView)findViewById(R.id.zonghe);
//     底部导航栏

        chart();
        pilao.setPercent(pilaonum);
        pilaotext.setText(Float.toString(pilaonum));
        zonghe.setPercent(89);
        zonghetext.setText("80");

//     底部导航栏
        first = findViewById(R.id.first_main);
        second = findViewById(R.id.second_main);
        third = findViewById(R.id.third_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Button xinlv=(Button) findViewById(R.id.xinlv);
        StringBuilder xinlvzonghe=new StringBuilder();
        xinlvzonghe.append("心率");
        xinlvzonghe.append(Float.toString(pilaonum));
        xinlv.setText(xinlvzonghe);
        xinlv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast=Toast.makeText(MainActivity.this,"您已经绘制心率图",Toast.LENGTH_SHORT);
////                toast.setGravity(Gravity.LEFT,50,0);
                toast.show();
                chartxinlv();
            }
        });


        RecyclerView swipeLayout =  (RecyclerView)findViewById(R.id.sample1);
        List<String> list=new ArrayList<>();
        for(int i=0;i<5;i++){
            list.add("多喝热水"+(i+1));
        }
        list.add("喝什么喝睡觉了");
        SwipeLayout swipeLayout2=(SwipeLayout)findViewById(R.id.swipe_layout) ;
        MyRecyclerViewAdapter myRecyclerViewAdapter=new MyRecyclerViewAdapter(this,list);
        swipeLayout.setLayoutManager(new LinearLayoutManager(this));
        swipeLayout.setAdapter(myRecyclerViewAdapter);
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
            final EditText nick_name_modify = view.findViewById(R.id.i8_nick_name_modify);
            final EditText password_modify = view.findViewById(R.id.i8_pass_modify);
            final EditText again_password_modify = view.findViewById(R.id.i8_pass_again_modify);
            final EditText age_modify = view.findViewById(R.id.i8_age_modify);
            final EditText height_modify = view.findViewById(R.id.i8_height_modify);
            final EditText weight_modify = view.findViewById(R.id.i8_weight_modify);
            final EditText car_plate_modify = view.findViewById(R.id.i8_car_plate_modify);
            final EditText car_age_modify = view.findViewById(R.id.i8_car_age_modify);

            Button button_reset = view.findViewById(R.id.i8_reset_modify);
            Button button_submit = view.findViewById(R.id.i8_confirm_modify);

//控件点击监听

//            重置按钮
            button_reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nick_name_modify.setText("");
                    password_modify.setText("");
                    again_password_modify.setText("");
                    age_modify.setText("");
                    height_modify.setText("");
                    weight_modify.setText("");
                    car_plate_modify.setText("");
                    car_age_modify.setText("");
                }
            });
//              提交按钮
            button_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /* 获取用户设置的信息 */
                    // 获取昵称
                    String nick_name_m = nick_name_modify.getText().toString().trim();
                    // 获取密码
                    String password_m = password_modify.getText().toString().trim();
                    // 获取年龄
                    String age_m = age_modify.getText().toString().trim();
                    // 获取身高
                    String height_m = height_modify.getText().toString().trim();
                    // 获取体重
                    String weight_m = weight_modify.getText().toString().trim();
                    // 获取车牌号
                    String car_plate_m = car_plate_modify.getText().toString().trim();
                    // 获取车龄
                    String car_age_m = car_age_modify.getText().toString().trim();

                    // 创建意图，从MainActivity跳转到ResultActivity    第二个参数为跳转页面
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    // 创建数据包封装用户要提交的数据
                    Bundle data = new Bundle();
                    data.putString("nickname", nick_name_m);
//                  。。。。。。
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



//    图表相关
    public void chart(){
        LineChart lineChart = (LineChart) findViewById(R.id.chart);
        ArrayList<Entry> entries = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            float val = (float) (Math.random() * 2) - 3;
            entries.add(new Entry(i, val));
        }
        //List<Entry> entries = new ArrayList<>();


        List<Entry> entries2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {

            float val2 = (float) (Math.random() * 10) - 30;
            entries2.add(new Entry(i, val2));
        }

        //给这条线起个名字，并对文字颜色、大小做一些设置
        LineDataSet dataSet = new LineDataSet(entries, "数据一");
        dataSet.setColor(Color.BLUE);//线的颜色
        //格式化值
        dataSet.setValueFormatter(new IValueFormatter() {

            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return value + "元";
            }
        });
        //数据二
        LineDataSet dataSet2 = new LineDataSet(entries2, "数据二");
        dataSet2.setColor(Color.GREEN);//线的颜色

        //装入集合
        List<ILineDataSet> iLineDataSets = new ArrayList<>();
        iLineDataSets.add(dataSet);
        iLineDataSets.add(dataSet2);

        //如果是只显示一条线，直接传dataSet就可以了
        LineData lineData = new LineData(iLineDataSets);
        //设置边框是否绘制，边框线的粗细
        //chart.setDrawBorders(true);
        //chart.setBorderWidth(1);

        /*图表的缩放*/
        //图表是否允许Y轴缩放
        lineChart.setScaleYEnabled(false);
        //chart.setScaleEnabled(true);   // 两个轴上的缩放,X,Y分别默认为true
        // chart.setScaleXEnabled(true);  // X轴上的缩放,默认true
        // chart.setScaleYEnabled(true);  // Y轴上的缩放,默认true
        // chart.setPinchZoom(true);  // X,Y轴同时缩放
        // chart.setDoubleTapToZoomEnabled(true); // 双击缩放,默认true

        /*设置右下角的文本描述,文字大小、颜色、内容等*/
        Description description = new Description();
        description.setText("产量表");
        description.setEnabled(true);//是否显示右下角文字信息
        lineChart.setNoDataText("暂无数据"); // 没有数据时显示的内容
        lineChart.setDescription(description);

        /*轴与网格线*/
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawLabels(true);//x轴上的数值是否显示
        xAxis.setDrawAxisLine(true);//是否绘制X轴
        xAxis.setDrawGridLines(false);//是否绘制X轴的网格线
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//X轴的位置
        lineChart.getAxisRight().setEnabled(false);//取消y轴的右侧
        lineChart.getAxisLeft().setDrawGridLines(false);//是否绘制Y轴的网格线

        /*设置点击数据点后的弹窗*/
        //传入一个自定义的布局
        //final MarkerView markerView = new MarkerView(MainActivity.this, R.layout.first_main);
        //lineChart.setMarker(markerView);
        lineChart.setData(lineData);//装载数据
        lineChart.invalidate();
        //final TextView tv_X = markerView.findViewById(R.id.tv_X);
        //final TextView tv_Y = markerView.findViewById(R.id.tv_Y);
        //设置数据点点击事件，这里是更新弹窗中的信息
//        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
//            @Override
//            public void onValueSelected(Entry e, Highlight h) {
//                //tv_X.setText(e.getX() + "");
//                tv_Y.setText(e.getY() + "");
//            }
//
//            @Override
//            public void onNothingSelected() {
//                tv_Y.setText(" ");
//
//            }
//        } );
    }
    public void chartxinlv(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //builder.setIcon(R.drawable.ceshi1);
        builder.setTitle("绘制图片");
        //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        final View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.chart, null);
        final LineChart lineChart ;
        lineChart= (LineChart) view.findViewById(R.id.chart2);
        final ArrayList<Entry> entries = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            //float val = (float) (Math.random() * 2) + 10;
            entries.add(new Entry(i, i+1));
        }


        final List<Entry> entries2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {

            float val2 = (float) (Math.random() * 10) + 90;
            entries2.add(new Entry(i, val2));
        }

        //给这条线起个名字，并对文字颜色、大小做一些设置
        LineDataSet dataSet = new LineDataSet(entries, "数据一");
        dataSet.setColor(Color.BLUE);//线的颜色
        //格式化值
        dataSet.setValueFormatter(new IValueFormatter() {

            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return value + "元";
            }
        });
        //数据二
        LineDataSet dataSet2 = new LineDataSet(entries2, "数据二");
        dataSet2.setColor(Color.GREEN);//线的颜色

        //装入集合
        List<ILineDataSet> iLineDataSets = new ArrayList<>();
        iLineDataSets.add(dataSet);
        iLineDataSets.add(dataSet2);

        //如果是只显示一条线，直接传dataSet就可以了
        LineData lineData = new LineData(iLineDataSets);
        //设置边框是否绘制，边框线的粗细
        //chart.setDrawBorders(true);
        //chart.setBorderWidth(1);

        /*图表的缩放*/
        //图表是否允许Y轴缩放
        lineChart.setScaleYEnabled(false);
        //chart.setScaleEnabled(true);   // 两个轴上的缩放,X,Y分别默认为true
        // chart.setScaleXEnabled(true);  // X轴上的缩放,默认true
        // chart.setScaleYEnabled(true);  // Y轴上的缩放,默认true
        // chart.setPinchZoom(true);  // X,Y轴同时缩放
        // chart.setDoubleTapToZoomEnabled(true); // 双击缩放,默认true

        /*设置右下角的文本描述,文字大小、颜色、内容等*/
        Description description = new Description();
        description.setText("产量表");
        description.setEnabled(false);//是否显示右下角文字信息
        lineChart.setNoDataText("暂无数据"); // 没有数据时显示的内容
        lineChart.setDescription(description);

        /*轴与网格线*/
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawLabels(true);//x轴上的数值是否显示
        xAxis.setDrawAxisLine(true);//是否绘制X轴
        xAxis.setDrawGridLines(false);//是否绘制X轴的网格线
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//X轴的位置
        lineChart.getAxisRight().setEnabled(false);//取消y轴的右侧
        lineChart.getAxisLeft().setDrawGridLines(false);//是否绘制Y轴的网格线

        /*设置点击数据点后的弹窗*/
        //传入一个自定义的布局
        //final MarkerView markerView = new MarkerView(MainActivity.this, R.layout.activity_main);
        //lineChart.setMarker(markerView);
        lineChart.setData(lineData);//装载数据
        lineChart.invalidate();
        //    设置我们自己定义的布局文件作为弹出框的Content
        builder.setView(view);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

                //    将输入的用户名和密码打印出来
                Toast.makeText(MainActivity.this, "end" , Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });
        builder.show();
    }

    public class MyRecyclerViewAdapter extends RecyclerSwipeAdapter<MyRecyclerViewAdapter.MyViewHolder> {
        private Context context;
        private List<String> list;
        public MyRecyclerViewAdapter(Context context, List<String> list) {
            this.context = context;
            this.list = list;
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder viewHolder, final int position) {
            viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
            viewHolder.surface.setText(list.get(position));
            viewHolder.bottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    notifyDataSetChanged();
                }
            });
            viewHolder.mark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public int getSwipeLayoutResourceId(int position) {
            return position;
        }

        public  class MyViewHolder extends RecyclerView.ViewHolder{
            private SwipeLayout swipeLayout;
            private TextView bottom;
            private TextView surface;
            private TextView mark;
            public LinearLayout surf;
            public MyViewHolder(View itemView) {
                super(itemView);
                swipeLayout=(SwipeLayout)itemView.findViewById(R.id.swipe_layout);
                bottom=(TextView)itemView.findViewById(R.id.bottom);
                surface=(TextView)itemView.findViewById(R.id.surface);
                mark=(TextView)itemView.findViewById(R.id.mark);


            }
        }
    }
}
