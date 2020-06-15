package com.example.portable_ventilator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView temperature_text;
    private TextView humidity_text;
    private TextView pressure_text;
    private Vibrator vibrator;
    private Handler handler1 = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temperature_text = (TextView)findViewById(R.id.temperature_text);
        humidity_text = (TextView)findViewById(R.id.humidity_text);
        pressure_text = (TextView)findViewById(R.id.pressure_text);
        vibrator = (Vibrator)this.getSystemService(VIBRATOR_SERVICE);
        //运行在UI主线程
        //handler1.postDelayed(task,1000);//延迟调用
        handler1.post(task);//立即调用
    }

    private Runnable task = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            handler1.postDelayed(this,30*1000);//设置延迟时间，此处是30秒
            //修改各项指标
            //使用Math.random()返回一个0到1之间的double值
            String sTempFormat = getResources().getString(R.string.temperature);
            String sT = String.format(sTempFormat,31+Math.random()*7);
            temperature_text.setText(sT);
            String sHumFormat = getResources().getString(R.string.humidity);
            String sH = String.format(sHumFormat,(int)(69+Math.random()*12));
            humidity_text.setText(sH);
            String sPreFormat = getResources().getString(R.string.pressure);
            String sP = String.format(sPreFormat,94+Math.random()*7);
            pressure_text.setText(sP);
            //当指标异常时报警
            float t = Float.parseFloat(temperature_text.getText().toString().replace("温度：", "").replace(" ℃", ""));
            int h = Integer.parseInt(humidity_text.getText().toString().replace("湿度：","").replace("% RH",""));
            float p = Float.parseFloat(pressure_text.getText().toString().replace("气压：","").replace(" kPa",""));
            //患者吸入气体温度应保持在32～37℃，气压95kpa~100kpa和湿度70%~80%
            if((t<32||t>37)||(h<70||h>80)||(p<95||p>100)){
                //Toast提醒+手机震动，实现报警
                Toast.makeText(MainActivity.this,"指标异常！",Toast.LENGTH_SHORT).show();
                vibrator.vibrate(1000);//震动1秒
            }
        }
    };
}