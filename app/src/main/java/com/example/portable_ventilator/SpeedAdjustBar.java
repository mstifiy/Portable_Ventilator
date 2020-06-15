package com.example.portable_ventilator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SpeedAdjustBar extends LinearLayout {
    private int speed = 0;  //初始化涡轮机转速为0
    private ImageView[] speeds = new ImageView[10]; //ImageView数组
    private Integer[] speeds_id = new Integer[]{R.id.speed_0, R.id.speed_1, R.id.speed_2, R.id.speed_3,
            R.id.speed_4, R.id.speed_5, R.id.speed_6, R.id.speed_7, R.id.speed_8, R.id.speed_9};

    public SpeedAdjustBar(final Context context, AttributeSet attrs){
        super(context, attrs);
        //动态加载布局
        LayoutInflater.from(context).inflate(R.layout.adjust_bar, this);
        ImageButton add_button = (ImageButton)findViewById(R.id.add_button);
        ImageButton subtract_button = (ImageButton)findViewById(R.id.subtract_button);
        final TextView speed_text = (TextView)findViewById(R.id.show_speed);
        final String sSpeedFormat = getResources().getString(R.string.speed_text);
        String s1 = String.format(sSpeedFormat, speed);
        speed_text.setText(s1);
        //为数组赋值
        for(int i = 0; i < 10; i++){
            speeds[i] = (ImageView)findViewById(speeds_id[i]);
        }
        add_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(speed < 10){
                    speed = speed + 1;
                    show_speed(speed);
                    String s1 = String.format(sSpeedFormat, speed);
                    speed_text.setText(s1);
                }
                else {
                    Toast.makeText(context, "当前已是最大转速", Toast.LENGTH_SHORT).show();
                }
            }
        });
        subtract_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(speed > 0){
                    speed = speed -1;
                    show_speed(speed);
                    String s1 = String.format(sSpeedFormat, speed);
                    speed_text.setText(s1);
                }
                else {
                    Toast.makeText(context, "涡轮机已停止旋转", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void show_speed(int s){
        int i;
        //图形化显示转速
        for(i = 0; i < 10; i++){
            speeds[i].setImageResource(R.drawable.speed_element_1);
        }
        for(i = 0; i < s; i++){
            speeds[i].setImageResource(R.drawable.speed_element_2);
        }
    }

}
