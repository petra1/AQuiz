package ch.ritter1.apps.aquiz;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

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
        int totalQuestions = 0;
        if (questionList != null) {
            totalQuestions = questionList.size();
            for (Question question : questionList) {
                if (question.getUserAnswerIndex() == question.getCorrectAnswerIndex()) {
                    correctAnswers++;
                }
            }
        }


        String summaryText = getString(R.string.results_summary, correctAnswers, totalQuestions);


        binding.tvSummary.setText(summaryText);

        binding.rvResults.setLayoutManager(new LinearLayoutManager(this));


        ResultsAdapter adapter = new ResultsAdapter(questionList);
        binding.rvResults.setAdapter(adapter);

        binding.btnExit.setOnClickListener(v -> finishAffinity());
        binding.btnRestart.setOnClickListener(v -> {
            Intent intent = new Intent(this, QuizActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
            finish();
        });

    }

}

