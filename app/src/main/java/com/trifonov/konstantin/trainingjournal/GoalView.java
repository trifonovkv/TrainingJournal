package com.trifonov.konstantin.trainingjournal;

import android.view.View;
import android.widget.TextView;

public class GoalView implements Observer, Widget {
    private TextView textView;

    public GoalView(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void update() {
        Journal journal = Journal.getInstance();
        Entry entry = journal.getEntry();
        this.setText(entry.getGoal());
    }

    @Override
    public void setText(String string) {
        this.textView.setText(string);
    }

    public void setEditTextListener(View.OnClickListener listener) {
        this.textView.setOnClickListener(listener);
    }

    public String getGoal() {
        return this.textView.getText().toString();
    }
}
