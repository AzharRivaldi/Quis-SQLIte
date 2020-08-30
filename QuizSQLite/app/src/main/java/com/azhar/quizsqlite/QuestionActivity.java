package com.azhar.quizsqlite;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.azhar.quizsqlite.R;
import com.azhar.quizsqlite.QuestionAdapter;
import com.azhar.quizsqlite.QuizDBHelper;
import com.azhar.quizsqlite.Question;

import java.util.ArrayList;
import java.util.Objects;

import static com.azhar.quizsqlite.CategoryAdapter.CATEGORY_COLOR;
import static com.azhar.quizsqlite.CategoryAdapter.CATEGORY_ID;

/**
 * Created by Azhar Rivaldi on 10/07/2019.
 */

public class QuestionActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private QuestionAdapter mAdapter;
    private ArrayList<Question> mQuestionList;
    private QuizDBHelper mDbHelper;

    private ConstraintLayout mParentLayout;
    private TextView mScoreTextView;
    private TextView mRemaningQuestionsTextView;
    private int mTotalQuestions;
    private int mScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle categoryBundle = null;
        if (getIntent() != null) {
            categoryBundle = getIntent().getExtras();
        }

        mParentLayout = findViewById(R.id.question_layout);
        if (categoryBundle != null) {
            String hexColor = String.format("#%06X", (0xFFFFFF & categoryBundle.getInt(CATEGORY_COLOR)));
            hexColor = "#44"+hexColor.substring(1);
            mParentLayout.setBackgroundColor(Color.parseColor(hexColor));
        }

        mScoreTextView = findViewById(R.id.score);
        mRemaningQuestionsTextView = findViewById(R.id.remaining_questions);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);

        mDbHelper = new QuizDBHelper(this, categoryBundle);
        if (categoryBundle != null) {
            mQuestionList = mDbHelper.getAllQuestions(categoryBundle.getString(CATEGORY_ID));
            mTotalQuestions = mQuestionList.size();
            mScore = 0;
            displayScore();
        }
        mAdapter = new QuestionAdapter(this, mQuestionList, categoryBundle.getString(CATEGORY_ID));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    public void displayScore() {
        String scoreString = "Score " + mScore + "/" + mTotalQuestions;
        mScoreTextView.setText(scoreString);
        mRemaningQuestionsTextView.setText("Remaining: " + mTotalQuestions--);
    }

    public void updateScore() {
        mScore++;
    }
}
