package ch.ritter1.apps.aquiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ch.ritter1.apps.aquiz.databinding.ResultItemBinding;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ViewHolder> {
    private final List<Question> questionList;

    public ResultsAdapter(List<Question> questionList) {
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ResultItemBinding binding = ResultItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Question question = questionList.get(position);


        holder.bind(question, position);
    }

    @Override
    public int getItemCount() {
        if (questionList != null) {
            return questionList.size();
        }
        return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ResultItemBinding binding;

        public ViewHolder(ResultItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


        public void bind(Question question, int position) {


            binding.tvQuestionNumber.setText(String.valueOf(position + 1));
            binding.tvResultQuestion.setText(question.getQuestion());



            String userAnswerText = getAnswerStringByIndex(question, question.getUserAnswerIndex());
            String correctAnswerText = getAnswerStringByIndex(question, question.getCorrectAnswerIndex());


            binding.tvUserAnswer.setText("Your Answer: " + userAnswerText);
            binding.tvCorrectAnswer.setText("Correct Answer: " + correctAnswerText);



            Context context = binding.getRoot().getContext();
            if (question.getUserAnswerIndex() == question.getCorrectAnswerIndex()) {
                binding.tvQuestionNumber.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_correct_answer));
            } else {
                binding.tvQuestionNumber.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_wrong_answer));
            }
        }


        private String getAnswerStringByIndex(Question question, int index) {
            switch (index) {
                case 0: return question.getAnswer1();
                case 1: return question.getAnswer2();
                case 2: return question.getAnswer3();
                case 3: return question.getAnswer4();
                default: return "No answer";            }
        }
    }
}