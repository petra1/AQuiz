package ch.ritter1.apps.aquiz;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuizActivity extends AppCompatActivity {
    TextView textViewQuestions;
    RadioGroup radioGroup;
    RadioButton radio_bt_1;
    RadioButton radio_bt_2;
    RadioButton radio_bt_3;
    RadioButton radio_bt_4;
    Button buttonNext;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textViewQuestions = findViewById(R.id.textViewQuestions);
        radioGroup = findViewById(R.id.radioGroup);
        radio_bt_1 = findViewById(R.id.radio_bt_1);
        radio_bt_2 = findViewById(R.id.radio_bt_2);
        radio_bt_3 = findViewById(R.id.radio_bt_3);
        radio_bt_4 = findViewById(R.id.radio_bt_4);



    }

}