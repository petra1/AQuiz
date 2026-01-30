package ch.ritter1.apps.aquiz;

import android.content.Intent;
import android.os.Bundle;import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import ch.ritter1.apps.aquiz.databinding.ActivityQuizBinding;

public class QuizActivity extends AppCompatActivity {
    private ActivityQuizBinding binding;
    private DatabaseHelper dbHelper;
    private List<Question> questionList;
    private int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen.installSplashScreen(this);
        EdgeToEdge.enable(this);

        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbHelper = new DatabaseHelper(this);
        questionList = dbHelper.getAllQuestions();

        if (questionList != null && !questionList.isEmpty()) {
            showQuestion();
        }

        binding.btnSubmit.setOnClickListener(v -> {
            int selectedId = binding.rgAnswers.getCheckedRadioButtonId();

            if (selectedId == -1) {
                Toast.makeText(QuizActivity.this, R.string.toast_no_answer, Toast.LENGTH_SHORT).show();
                return;
            }

            int userAnswer = -1;
            if (selectedId == binding.rbAnswer1.getId()) {
                userAnswer = 0;
            } else if (selectedId == binding.rbAnswer2.getId()) {
                userAnswer = 1;
            } else if (selectedId == binding.rbAnswer3.getId()) {
                userAnswer = 2;
            } else if (selectedId == binding.rbAnswer4.getId()) {
                userAnswer = 3;
            }

            Question currentQuestion = questionList.get(currentQuestionIndex);
            currentQuestion.setUserAnswerIndex(userAnswer);

            currentQuestionIndex++;

            if (currentQuestionIndex < questionList.size()) {
                showQuestion();
            } else {
                binding.btnSubmit.setEnabled(false);
                binding.btnSubmit.setText(getString(R.string.quiz_finished));

                Intent intent = new Intent(QuizActivity.this, ResultsActivity.class);
                intent.putParcelableArrayListExtra("EXTRA_QUESTIONS", new ArrayList<>(questionList));
                startActivity(intent);
            }
        });
    }

    // --- THIS METHOD IS NOW UPDATED ---
    private void showQuestion() {
        // Clear the previous selection
        binding.rgAnswers.clearCheck();

        // Get the current question
        Question currentQuestion = questionList.get(currentQuestionIndex);

        // Set the question progress text
        int currentQuestionNumber = currentQuestionIndex + 1;
        int totalQuestions = questionList.size();
        String progressText = getString(R.string.question_progress_text, currentQuestionNumber, totalQuestions);
        binding.tvQuestionProgress.setText(progressText);

        // Set the question and answer texts
        binding.tvQuestionText.setText(currentQuestion.getQuestion());
        binding.rbAnswer1.setText(currentQuestion.getAnswer1());
        binding.rbAnswer2.setText(currentQuestion.getAnswer2());
        binding.rbAnswer3.setText(currentQuestion.getAnswer3());
        binding.rbAnswer4.setText(currentQuestion.getAnswer4());
    }
}