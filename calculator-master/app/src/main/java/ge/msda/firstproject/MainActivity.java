package ge.msda.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;

    private double operand = 0;
    private String operation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);

    }

    public void numberClick(View view) {

        if (!(view instanceof TextView)) {
            return;
        }

        TextView clicked = (TextView) view;

        String number = clicked.getText().toString();
        String res = resultTextView.getText().toString();

        if (Objects.equals(res, "0")) {
            res = "";
        }

        String result = res + number;

        resultTextView.setText(result);

    }

    public void operationClick(View view) {

        if (!(view instanceof TextView)) {
            return;
        }

        if (!TextUtils.isEmpty(resultTextView.getText())) {
            operand = Double.parseDouble(resultTextView.getText().toString());
        }

        resultTextView.setText("");

        operation = ((TextView) view).getText().toString();

    }

    public void equalsClick(View view) {

        String secOperandText = resultTextView.getText().toString();
        double secOperand = 0.0;

        if (!TextUtils.isEmpty(secOperandText)) {
            secOperand = Double.parseDouble(secOperandText);
        }

        switch (this.operation) {
            case "+":
                resultTextView.setText(String.valueOf(operand + secOperand));
                break;
            case "-":
                resultTextView.setText(String.valueOf(operand - secOperand));
                break;
            case "*":
                resultTextView.setText(String.valueOf(operand * secOperand));
                break;
            case "/":
                resultTextView.setText(String.valueOf(operand / secOperand));
                break;
//                დავამატე :) აქ
            case "pow":
                resultTextView.setText(String.valueOf(Math.pow(operand, secOperand)));
                break;
        }

    }

    //                დავამატე :) აქ
    public void clear(View view) {
         resultTextView.setText("");
         operand = 0;
         operation = "";
    }

}