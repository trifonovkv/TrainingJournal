package com.trifonov.konstantin.trainingjournal;

import android.widget.EditText;

public class NoteView implements Observer, Widget {
    private EditText editText;

    public NoteView(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void update() {
        Journal journal = Journal.getInstance();
        Entry entry = journal.getEntry();
        this.setText(entry.getNote());
    }

    @Override
    public void setText(String string) {
        this.editText.setText(string);
    }

    public String getNote() {
        return this.editText.getText().toString();
    }
}
