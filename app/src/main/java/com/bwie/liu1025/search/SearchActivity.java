package com.bwie.liu1025.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bwie.liu1025.R;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText etSearch;
    private Button btnSearch;
    private MyFluidView mfvFluid;
    private TextView txtContent;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        etSearch = findViewById(R.id.et_search);
        btnSearch = findViewById(R.id.btn_search);
        mfvFluid = findViewById(R.id.mfv_fluid);
        list = new ArrayList<>();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = etSearch.getText().toString();
                View item = View.inflate(SearchActivity.this,R.layout.fluid_layout,null);
                txtContent = item.findViewById(R.id.txt_content);
                list.add(s);
                for (int i = 0 ; i <list.size();i++) {
                    txtContent.setText(list.get(i));
                }
                mfvFluid.addView(item);
            }
        });
    }
}
