package com.ewe.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity  implements  View.OnClickListener{
    TextView signal, answer;
    MaterialButton C, bracket1, bracket2, divison, minus, x, equals, one, two, three, four, five, six;
    MaterialButton seven, eight, nine, plusorminus,ac,addition, percentage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        answer = findViewById(R.id.answer);
        signal = findViewById(R.id.signal);

        assignId(C, R.id.C);

        assignId(bracket1, R.id.bracket1);
        assignId(bracket2, R.id.bracket2);
        assignId(minus, R.id.minus);
        assignId(addition, R.id.additon);
        assignId(x, R.id.x);
        assignId(equals, R.id.equals);
        assignId(one, R.id.one);
        assignId(two, R.id.two);
        assignId(three, R.id.three);
        assignId(four, R.id.four);
        assignId(five, R.id.five);
        assignId(six, R.id.six);
        assignId(seven, R.id.seven);
        assignId(eight, R.id.eight);
        assignId(nine, R.id.nine);
        assignId(plusorminus, R.id.plusorminus);
        assignId(percentage, R.id.percentage);
        assignId(divison, R.id.divison);
        assignId(ac, R.id.ac);




    }

    void assignId(MaterialButton btn, int Id) {
        btn = findViewById(Id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
            MaterialButton button = (MaterialButton) view;
            String buttonText = button.getText().toString();
            String dataToCalculate = signal.getText().toString();


            if (buttonText.equals("AC")) {
                signal.setText("");
                answer.setText("0");
                return;
            }
            if (buttonText.equals("=")) {
                signal.setText(answer.getText());
                return;


            } else {
                dataToCalculate = dataToCalculate + buttonText;
            }
            signal.setText(dataToCalculate);

            String finalResult = getResult(dataToCalculate);


            if(!finalResult.equals("Err")){
                answer.setText(finalResult);
            }

        }
    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

}








