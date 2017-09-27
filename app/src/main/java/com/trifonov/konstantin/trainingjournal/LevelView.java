package com.trifonov.konstantin.trainingjournal;

import android.view.View;
import android.widget.TextView;

public class LevelView implements Observer {
    private TextView textView;

    public LevelView(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void update() {
        Journal journal = Journal.getInstance();
        Entry entry = journal.getEntry();
        this.textView.setText(entry.getLevel());
    }

    public void setEditTextListener(View.OnClickListener listener) {
        this.textView.setOnClickListener(listener);
    }

    public String getLevel() {
        return this.textView.getText().toString();
    }
}
