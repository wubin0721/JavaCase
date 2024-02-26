package com.example.costumizeviewactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.costumizeviewactivity.View.LetterSideBar;
import com.example.costumizeviewactivity.View.StepsView;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;


public class CostiMiseMainActivity extends AppCompatActivity implements
        LetterSideBar.LetterTouchListen {

    private StepView stepView;

    private TextView letterTv;
    private LetterSideBar letterBar;

    private StepsView stepsView2;
    private final String[] speedLabels = {"发布", "接单", "服务", "完成"};
    private final String[] timeLabels = {"", "", "", ""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costi_mise_main);

        letterTv = this.findViewById(R.id.letter_tv);
        letterBar = this.findViewById(R.id.letterbar);
        letterBar.setOnLetterTouchListen(this);

        //无忧帮帮的 stepsView
        stepsView2 = this.findViewById(R.id.step_view2);
        stepsView2.setCompletedPosition(1 % speedLabels.length)
                .setSpeedLabels(speedLabels)
                .setTimeLables(timeLabels)
                .setBarColorIndicator(getResources().getColor(R.color.gray60))
                .setProgressColorIndicator(getResources().getColor(R.color.orange))
                .setLabelColorIndicator(getResources().getColor(R.color.orange))
                .drawView();

        //github的 stepsView
        stepView = this.findViewById(R.id.step_view);
        stepView.getState()
                .animationType(StepView.ANIMATION_CIRCLE)
                .selectedCircleRadius(60)
                .selectedCircleColor(Color.GREEN)
                .selectedTextColor(Color.BLACK)
                .selectedStepNumberColor(Color.RED)
                .steps(new ArrayList<String>() {{
                    add("验证手机");
                    add("设置密码");
                    add("注册成功");
                }})
                .stepsNumber(4)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
//                .stepLineWidth(18)
//                .textSize(18)
//                .stepNumberTextSize(18)
//                .typeface(ResourcesCompat.getFont(context, R.font.roboto_italic))
                .commit();


//        stepView.done(false);
//        stepView.go(1, true);

        stepView.setOnStepClickListener(new StepView.OnStepClickListener() {
            @Override
            public void onStepClick(int step) {
                switch (step){
                    case 0:
                        stepView.go(1, true);
                        break;
                    case 1:
                        stepView.go(2, true);
                        break;
                    case 2:
                        stepView.done(true);
                        break;
                    default:
                        break;
                }
            }
        });

    }


    @Override
    public void touch(CharSequence letter,boolean isTouch) {
        if(isTouch){
            letterTv.setVisibility(View.VISIBLE);
            letterTv.setText(letter);
        }else{
            letterTv.setVisibility(View.GONE);
        }

    }
}