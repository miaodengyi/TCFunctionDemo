package cn.com.cybertech.tcfunctiondemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * fragment有关的
 * Created by miao on 2018/1/24.
 */

public class FragmentActivity extends AppCompatActivity
{
    private Button btn_fragment_finish_activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("fragment有关的");
        setContentView(R.layout.activity_fragment);
        btn_fragment_finish_activity = (Button) findViewById(R.id.btn_fragment_finish_activity);
        btn_fragment_finish_activity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }
}
