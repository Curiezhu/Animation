package com.kejian.mike.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewAnimator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private FrameLayout frameLayout;
    private ImageView rocket;
    private ImageView dog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout frameLayout = new FrameLayout(this);
        ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        FrameLayout.LayoutParams la = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        la.gravity = Gravity.BOTTOM | Gravity.CENTER;

        rocket = new ImageView(this);
        rocket.setMaxHeight(400);
        rocket.setMaxWidth(400);
        rocket.setAdjustViewBounds(true);
        rocket.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.rocket));

        dog = new ImageView(this);
        dog.setMaxWidth(100);
        dog.setMaxHeight(100);
        dog.setAdjustViewBounds(true);
        dog.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.dog));

        rocket.setOnClickListener(this);
        dog.setOnClickListener(this);

        frameLayout.addView(rocket,la);
        frameLayout.addView(dog,la);

        setContentView(frameLayout,layoutParams);

    }

    @Override
    public void onClick(View view){

        WindowManager windowManager = (WindowManager)getSystemService(WINDOW_SERVICE);
        int h = windowManager.getDefaultDisplay().getHeight();
        ValueAnimator fly = ValueAnimator.ofFloat(0,-h);

        fly.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                float value = (float)valueAnimator.getAnimatedValue();
                rocket.setTranslationY(value);
                dog.setTranslationY(value);
            }
        });

        fly.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                Toast.makeText(getApplicationContext(),"开始飞行",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        ObjectAnimator ro = ObjectAnimator.ofFloat(dog,"rotation",0,360);

        AnimatorSet set = new AnimatorSet();

        set.play(fly).with(ro);
        set.setDuration(2000);
        set.start();

    }


}
