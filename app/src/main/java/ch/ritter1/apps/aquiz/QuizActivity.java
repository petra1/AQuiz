package ch.ritter1.apps.aquiz;

import android.content.Intent;
import android.os.Bundle;

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
    private List<Question>  questionList;
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
            int userAnswer = -1;
            int selectedId = binding.radioGroup.getCheckedRadioButtonId();
            if (selectedId == binding.radioBt1.getId()) {
                userAnswer = 0;
            } else if (selectedId == binding.radioBt2.getId()) {
                userAnswer = 1;
            } else if (selectedId == binding.radioBt3.getId()) {
                userAnswer = 2;
            } else if (selectedId == binding.radioBt4.getId()) {
                userAnswer = 3;
            }
            Question currentQuestion = questionList.get(currentQuestionIndex);
            currentQuestion.setUserAnswerIndex(userAnswer);






            currentQuestionIndex++;

            if (currentQuestionIndex < questionList.size()) {
                showQuestion();
            } else {
                Intent intent = new Intent(QuizActivity.this, ResultsActivity.class);
                intent.putParcelableArrayListExtra("EXTRA_QUESTIONS", new ArrayList<>(questionList));
                startActivity(intent);
                binding.btnSubmit.setEnabled(false);
                binding.btnSubmit.setText("Quiz finished");
            }

        });

    }

    private void showQuestion() {
        binding.radioGroup.clearCheck();
        Question currentQuestion = questionList.get(currentQuestionIndex);
        binding.textViewQuestions.setText(currentQuestion.getQuestion());
        binding.radioBt1.setText(currentQuestion.getAnswer1());
        binding.radioBt2.setText(currentQuestion.getAnswer2());
        binding.radioBt3.setText(currentQuestion.getAnswer3());
        binding.radioBt4.setText(currentQuestion.getAnswer4());
    }


}