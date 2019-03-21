package com.example.demo1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class InfoCompleteActivity extends Activity {
    private Button btn_enter_index;
    private EditText edt_age,
                      edt_height,
                      edt_weight,
                      edt_car_plate,
                      edt_car_age,
                      edt_contact,
                      edt_message;
    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.first_register_info_complete);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        btn_enter_index = findViewById(R.id.i8_enter_index);
        edt_age = findViewById(R.id.i8_age);
        edt_height = findViewById(R.id.i8_height);
        edt_weight = findViewById(R.id.i8_weight);
        edt_car_plate = findViewById(R.id.i8_car_plate);
        edt_car_age = findViewById(R.id.i8_car_age);
        edt_contact = findViewById(R.id.i8_contact);
        edt_message = findViewById(R.id.i8_message);
    }

    private void initData() {
        edt_age.setText("18");
        edt_height.setText("175");
        edt_weight.setText("70");
        edt_car_plate.setText("湘A12345");
        edt_car_age.setText("2");
        edt_contact.setText("15274847415");
        edt_message.setText("我遇到紧急情况，请帮我报警！！");
    }

    private void initListener() {
        btn_enter_index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoCompleteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
