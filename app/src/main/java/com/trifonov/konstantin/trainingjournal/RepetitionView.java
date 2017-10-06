package com.trifonov.konstantin.trainingjournal;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class RepetitionView implements Observer {
    private LinearLayout repeatsView;
    private View.OnClickListener repeatViewListener;

    RepetitionView(LinearLayout repeatsView) {
        this.repeatsView = repeatsView;
    }

    @Override
    public void update() {
        this.clean();
        Journal journal = Journal.getInstance();
        Entry entry = journal.getEntry();
        List<Integer> repeats = this.parse(entry.getSet());
        this.populate(repeats);
    }

    void setAddButtonListener(View.OnClickListener listener) {
        ImageButton repeatAddButton = this.repeatsView.findViewById(R.id.repeatAddButton);
        repeatAddButton.setOnClickListener(listener);
    }

    void setRepeatViewListener(View.OnClickListener repeatViewListener) {
        this.repeatViewListener = repeatViewListener;
    }


    public void add(int repeat) {
        TextView repeatView = this.create(repeat);
        repeatView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(8, 0, 8, 0);
        params.weight = 1.0f;
        params.gravity = Gravity.CENTER;
        repeatView.setLayoutParams(params);
        repeatView.setTextColor(Color.parseColor("#40596b"));
        repeatView.setOnClickListener(this.repeatViewListener);
        LinearLayout repeatsLayout = this.repeatsView.findViewById(R.id.repeatsLayout);
        repeatsLayout.addView(repeatView);
    }

    String getRepetition() {
        StringBuilder string = new StringBuilder();
        LinearLayout repeatsLayout = this.repeatsView.findViewById(R.id.repeatsLayout);
        for (int i = 0; i < repeatsLayout.getChildCount(); i++) {
            TextView textView = (TextView) repeatsLayout.getChildAt(i);
            string.append(textView.getText().toString());
            string.append(" ");
        }
        return string.toString();
    }

    private TextView create(int repeat) {
        TextView repeatView = new TextView(this.repeatsView.getContext());
        repeatView.setText(String.format(Locale.getDefault(), "%d", repeat));
        return repeatView;
    }

    private void populate(List<Integer> repeats) {
        for (int repeat : repeats) {
            this.add(repeat);
        }
    }

    private void clean() {
        LinearLayout repeatsLayout = this.repeatsView.findViewById(R.id.repeatsLayout);
        if (repeatsLayout.getChildCount() > 0) {
            repeatsLayout.removeAllViews();
        }
    }

    private List<Integer> parse(String string) {
        List<Integer> integers = new ArrayList<>();
        if (string != null) {
            String[] strings = string.split(" ");
            for (String s : strings) {
                integers.add(Integer.valueOf(s));
            }
        }
        return integers;
    }
}
