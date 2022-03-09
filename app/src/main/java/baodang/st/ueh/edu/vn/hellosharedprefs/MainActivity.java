package baodang.st.ueh.edu.vn.hellosharedprefs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final String sharedPreFile = "baodang.st.ueh.edu.vn.hellosharedprefs123";
    TextView numTextView;
    Button blackButton, redButton, blueButton, greenButton, countButton;
    int currentColor;
    int currentNum;
    private final View.OnClickListener changeColorClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button choosenButton = (Button) view;
            String choosenColor = choosenButton.getText().toString();
            switch (choosenColor) {
                case "Black":
                    currentColor = R.color.black;
                    numTextView.setTextColor(getResources().getColor(R.color.white));
                    updateNumTextView(currentColor, currentNum);
                    break;
                case "Red":
                    currentColor = R.color.red;
                    updateNumTextView(currentColor, currentNum);
                    break;
                case "Blue":
                    currentColor = R.color.blue;
                    updateNumTextView(currentColor, currentNum);
                    break;
                case "Green":
                    currentColor = R.color.green;
                    updateNumTextView(currentColor, currentNum);
                    break;
                default:
                    currentColor = R.color.white;
                    updateNumTextView(currentColor, currentNum);
            }

        }
    };
    private final View.OnClickListener changeNumClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            currentNum++;

            updateNumTextView(currentColor, currentNum);

        }
    };
    SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numTextView = findViewById(R.id.numView);
        redButton = findViewById(R.id.redbutton);
        blackButton = findViewById(R.id.blackbutton);
        blueButton = findViewById(R.id.bluebutton);
        greenButton = findViewById(R.id.greenbutton);
        countButton = findViewById(R.id.countbutton);

        redButton.setOnClickListener(changeColorClick);
        blackButton.setOnClickListener(changeColorClick);
        blueButton.setOnClickListener(changeColorClick);
        greenButton.setOnClickListener(changeColorClick);
        countButton.setOnClickListener(changeNumClick);

        currentColor = R.color.white;
        currentNum = 0;

        mPreferences = getSharedPreferences(sharedPreFile, MODE_PRIVATE);

        if (mPreferences != null) {

            currentColor = mPreferences.contains("numCount") ? mPreferences.getInt("colorId", 1) : 0;
            currentNum = mPreferences.contains("colorId") ? mPreferences.getInt("numCount", 1) : R.color.white;
            updateNumTextView(currentColor, currentNum);

        }

    }

    private void updateNumTextView(int idColor, int num) {
        numTextView.setBackgroundColor(getResources().getColor(idColor));
        numTextView.setText(String.valueOf(num));
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferenceEditor = mPreferences.edit();
        preferenceEditor.putInt("colorId", currentColor);
        preferenceEditor.putInt("numCount", currentNum);
        preferenceEditor.apply();
    }
}