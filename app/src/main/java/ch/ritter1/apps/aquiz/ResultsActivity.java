package ch.ritter1.apps.aquiz;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import ch.ritter1.apps.aquiz.databinding.ActivityResultsBinding;

public class ResultsActivity extends AppCompatActivity {

    private ActivityResultsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        binding = ActivityResultsBinding.inflate(getLayoutInflater());


        setContentView(binding.getRoot());


        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        List<Question> questionList = getIntent().getParcelableArrayListExtra("EXTRA_QUESTIONS");


        int correctAnswers = 0;
        int wrongAnswers = 0;
        if (questionList != null) {
            for (Question question : questionList) {
                if (question.getUserAnswerIndex() == question.getCorrectAnswerIndex()) {
                    correctAnswers++;
                } else {
                    wrongAnswers++;
                }
            }
        }


        binding.tvCorrectAnswers.setText("Richtige Antworten: " + correctAnswers);
        binding.tvWrongAnswers.setText("Falsche Antworten: " + wrongAnswers);
    }
}
