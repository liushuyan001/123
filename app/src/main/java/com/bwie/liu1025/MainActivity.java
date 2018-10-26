package com.bwie.liu1025;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics dm = new DisplayMetrics();
        //123
        //执行动画的控件
        txtBackground = findViewById(R.id.txt_background);
        //图片平移还能弹回  如果没有txtBackground.getTranslationX()则控件不会弹回来
        ObjectAnimator translationX = ObjectAnimator.ofFloat(txtBackground,"translationX",450f);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(txtBackground,"translationY",580f);
        //动画组合
        //分支
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(translationX).with(translationY);
        //执行动画的时间
        animatorSet.setDuration(3000);
        //执行动画
        animatorSet.start();

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
