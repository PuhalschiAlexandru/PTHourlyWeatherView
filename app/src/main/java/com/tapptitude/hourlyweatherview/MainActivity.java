package com.tapptitude.hourlyweatherview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tapptitude.hourlyweatherview.ui.CustomDiagram;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.am_cd_diagram_of_values)
    CustomDiagram mCustomDiagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mCustomDiagram.setData(getData(24, 0, 15));
    }

    private int[] getData(int valueCount, int startInterval, int endInterval) {
        int[] returnValues = new int[valueCount];
        for (int i = 0; i < returnValues.length; i++) {
            returnValues[i] = startInterval + (int) (Math.random() * (endInterval - startInterval));
        }
        return returnValues;
    }
    
}
