package com.bwie.liu1025;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.bwie.liu1025.cart.view.CartFragment;
import com.bwie.liu1025.main.view.fragment.MainFragment;
import com.bwie.liu1025.sort.view.SortFragment;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton rbMain;
    private RadioButton rbSort;
    private RadioButton rbCart;
    private MainFragment mainFragment;
    private FragmentManager manager;
    private SortFragment sortFragment;
    private CartFragment cartFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rbMain = findViewById(R.id.rb_main);
        rbSort = findViewById(R.id.rb_sort);
        rbCart = findViewById(R.id.rb_cart);
        mainFragment = new MainFragment();
        sortFragment = new SortFragment();
        cartFragment = new CartFragment();
        manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.fl_home,mainFragment)
                .add(R.id.fl_home,sortFragment)
                .add(R.id.fl_home,cartFragment)
                .hide(sortFragment)
                .hide(cartFragment)
                .commit();

        rbCart.setOnClickListener(this);
        rbMain.setOnClickListener(this);
        rbSort.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_main:
                manager.beginTransaction().show(mainFragment).hide(sortFragment).hide(cartFragment).commit();
                break;
            case R.id.rb_sort:
                manager.beginTransaction().hide(mainFragment).show(sortFragment).hide(cartFragment).commit();
                break;
            case R.id.rb_cart:
                manager.beginTransaction().hide(mainFragment).hide(sortFragment).show(cartFragment).commit();
                break;
        }
    }
}
