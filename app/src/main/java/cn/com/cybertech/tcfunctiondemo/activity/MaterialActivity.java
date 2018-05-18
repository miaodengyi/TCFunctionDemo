package cn.com.cybertech.tcfunctiondemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import cn.com.cybertech.tcfunctiondemo.R;

/**
 * material-design spinner
 * Created by miao on 2017/12/28.
 */

public class MaterialActivity extends AppCompatActivity
{
    private MaterialSpinner spinner;
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("material-design spinner");
        setContentView(R.layout.activity_material_spinner);
        spinner = (MaterialSpinner) findViewById(R.id.material_spinner);
        textView = (TextView) findViewById(R.id.tv_value);
        spinner.setItems("LC 鹿城","LW 龙湾","OH 瓯海","RA 瑞安","YQ 乐清 ","YJ 永嘉","CN 苍南","PY 平阳","TS 泰顺",
                "CH 文成","DT 洞头","JK 经济开发区");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Toast.makeText(MaterialActivity.this,"Clicked: "+item,Toast.LENGTH_SHORT).show();
                textView.setText(item.substring(0,2));
            }
        });
        textView.setText(spinner.getText().toString().substring(0,2));
    }
}
