package com.azhar.quizsqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azhar.quizsqlite.Question;
import com.azhar.quizsqlite.QuestionActivity;
import com.azhar.quizsqlite.R;
import com.azhar.quizsqlite.ToggleButtonGroupTableLayout;

import java.util.ArrayList;

/**
 * Created by Azhar Rivaldi on 10/07/2019.
 */

public class QuestionAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<Question> mQuestionList;
    private String mCategoryLabel;

    public QuestionAdapter(Context context, ArrayList<Question> questionList, String category) {
        this.mContext = context;
        this.mQuestionList = questionList;
        this.mCategoryLabel = category;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mCategoryLabel = mCategoryLabel.length() > 2 ?
                (mCategoryLabel.substring(0, 1).toUpperCase() + mCategoryLabel.substring(1)) :
                mCategoryLabel.toUpperCase();
        ((QuestionActivity) mContext).setTitle(mCategoryLabel);
        View questionView = LayoutInflater.from(mContext)
                .inflate(R.layout.question_card_layout, parent, false);
        return new QuestionViewHolder(questionView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Question question = mQuestionList.get(position);
        final QuestionViewHolder questionViewHolder = (QuestionViewHolder) holder;
        questionViewHolder.mQuestion.setText(question.getmQuestion());
        questionViewHolder.mRb1.setText(question.getmOption1());
        questionViewHolder.mRb2.setText(question.getmOption2());
        questionViewHolder.mRb3.setText(question.getmOption3());
        questionViewHolder.mRb4.setText(question.getmOption4());

        ArrayList<RadioButton> radioButtons = questionViewHolder.mTableLayout.getChildren();
        for (final RadioButton rb : radioButtons) {
            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    questionViewHolder.mTableLayout.checkAnswer(rb, question.getmAnswer(), mContext);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mQuestionList.size();
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder {

        TextView mQuestion;
        RadioButton mRb1;
        RadioButton mRb2;
        RadioButton mRb3;
        RadioButton mRb4;

        ToggleButtonGroupTableLayout mTableLayout;

        QuestionViewHolder(View itemView) {
            super(itemView);
            mQuestion = itemView.findViewById(R.id.question);

            mRb1 = itemView.findViewById(R.id.rb1);
            mRb2 = itemView.findViewById(R.id.rb2);
            mRb3 = itemView.findViewById(R.id.rb3);
            mRb4 = itemView.findViewById(R.id.rb4);

            mTableLayout = itemView.findViewById(R.id.table_layout);
        }
    }

}
