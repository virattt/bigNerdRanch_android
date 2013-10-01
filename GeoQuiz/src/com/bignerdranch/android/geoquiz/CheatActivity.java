package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {
	
	public static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
	public static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown";
	private static final String ANSWER_SHOWN = "Answer shown";
	
	private boolean mAnswerIsTrue;
	private boolean mIsCheater;
	
	private TextView mAnswerTextView;
	private Button mShowAnswer;
	
	private void setAnswerShownResult(boolean isAnswerShown) {
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		setResult(RESULT_OK, data);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
		
		// answer will not be shown until 
		// user presses the button
		

		if (savedInstanceState != null)
		{
			mIsCheater = savedInstanceState.getBoolean(ANSWER_SHOWN, false);
		}
		
		setAnswerShownResult(mIsCheater);
		
		
		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		
		mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
		
		mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
		mShowAnswer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mAnswerIsTrue) {
					mAnswerTextView.setText(R.string.true_button);
				}
				else {
					mAnswerTextView.setText(R.string.false_button);
				}
				mIsCheater = true;
				setAnswerShownResult(mIsCheater);
			}
		});
	}
	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);

		savedInstanceState.putBoolean(ANSWER_SHOWN, mIsCheater);
	}
}
