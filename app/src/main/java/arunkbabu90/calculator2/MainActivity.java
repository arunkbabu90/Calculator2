package arunkbabu90.calculator2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText mIOEditText;
    private double cValue = 0;
    private int mLastIndex = 0;
    private int mLastOperation;
    private boolean mIsFirstTime = true;

    public final int ADD_OPERATION = 10;
    public final int SUB_OPERATION = 11;
    public final int MUL_OPERATION = 12;
    public final int DIV_OPERATION = 13;
    public final int NULL_OPERATION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIOEditText = findViewById(R.id.et_calc_display);
    }

    public void onEqualClick(View view) {
        // Equal Button Pressed
        String expression = mIOEditText.getText().toString();
        calculate(expression);
    }

    private void calculate(String expression) {
        String value1;
        for (int i = 0; i < expression.length(); i++) {
            switch (expression.charAt(i)) {
                case '+':
                    value1 = expression.substring(mLastIndex, i);
                    if (mIsFirstTime) {
                        cValue = Double.parseDouble(value1);
                    } else {
                        cValue = Double.parseDouble(value1) + cValue;
                    }
                    mIsFirstTime = false;
                    mLastIndex = i;
                    mLastOperation = ADD_OPERATION;
                    break;
                case '-':
                    value1 = expression.substring(mLastIndex, i);
                    if (mIsFirstTime) {
                        cValue = Double.parseDouble(value1);
                    } else {
                        cValue = Double.parseDouble(value1) - cValue;
                    }
                    mIsFirstTime = false;
                    mLastIndex = i;
                    mLastOperation = SUB_OPERATION;
                    break;
                case '*':
                    value1 = expression.substring(mLastIndex, i);
                    if (mIsFirstTime) {
                        cValue = Double.parseDouble(value1);
                    } else {
                        cValue = Double.parseDouble(value1) * cValue;
                    }
                    mIsFirstTime = false;
                    mLastIndex = i;
                    mLastOperation = MUL_OPERATION;
                    break;
                case '/':
                    value1 = expression.substring(mLastIndex, i);
                    if (mIsFirstTime) {
                        cValue = Double.parseDouble(value1);
                    } else {
                        cValue = Double.parseDouble(value1) / cValue;
                    }
                    mIsFirstTime = false;
                    mLastIndex = i;
                    mLastOperation = DIV_OPERATION;
                    break;
            }
        }

        // Calculate with the trailing value
        double res = 0;
        switch (mLastOperation) {
            case ADD_OPERATION:
                res = cValue + Double.valueOf(expression.substring(mLastIndex + 1));
                break;
            case SUB_OPERATION:
                res = cValue - Double.valueOf(expression.substring(mLastIndex + 1));
                break;
            case MUL_OPERATION:
                res = cValue * Double.valueOf(expression.substring(mLastIndex + 1));
                break;
            case DIV_OPERATION:
                res = cValue / Double.valueOf(expression.substring(mLastIndex + 1));
                break;
            default:
                res = Double.parseDouble(expression);
        }

        String result = new DecimalFormat("#.##").format(res);
        // Remove fractional part if fractional part is 0
        long integerPart = (long) res;
        double fractionalPart = res - integerPart;
        if (fractionalPart == 0)
            result = String.valueOf((long) res);

        mIOEditText.setText(result);

        // Reset all values
        mLastOperation = NULL_OPERATION;
        mIsFirstTime = true;
        mLastIndex = 0;
        cValue = 0;
    }
}