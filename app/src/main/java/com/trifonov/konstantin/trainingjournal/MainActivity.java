package com.trifonov.konstantin.trainingjournal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements CalendarFragment.OnCalendarSelectedListener {
    private Journal journal = Journal.getInstance();
    private WorkoutDialogFragment exerciseDialog = new WorkoutDialogFragment();
    private AddRepetitionDialogFragment addRepetitionDialog = new AddRepetitionDialogFragment();
    private ChangeRepetitionDialogFragment changeRepetitionDialog = new ChangeRepetitionDialogFragment();
    private LevelDialogFragment levelDialog = new LevelDialogFragment();
    private GoalDialogFragment goalDialogFragment = new GoalDialogFragment();

    AdapterView.OnItemSelectedListener addExerciseItemListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if (adapterView.getSelectedItem() == "+") {
                exerciseDialog.show(getSupportFragmentManager(), "ExerciseDialog");
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    };

    View.OnClickListener addRepeatButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int minValue = 0;
            int maxValue = 99;
            addRepetitionDialog.setMinNumberPicker(minValue);
            addRepetitionDialog.setMaxNumberPicker(maxValue);
            addRepetitionDialog.show(getFragmentManager(), "AddRepetitionDialog");
        }
    };

    View.OnClickListener repeatViewListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            TextView repeatsView = (TextView) view;
            int minValue = 0;
            int maxValue = 99;
            int value = getIntegerFromTextView(repeatsView, minValue, maxValue);
            changeRepetitionDialog.setView(view);
            changeRepetitionDialog.setValue(value);
            changeRepetitionDialog.setMinValue(minValue);
            changeRepetitionDialog.setMaxValue(maxValue);
            changeRepetitionDialog.show(getFragmentManager(), "RepetitionDialog");
        }
    };

    View.OnClickListener goalViewListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            goalDialogFragment.show(getFragmentManager(), "GoalDialog");
        }
    };

    View.OnClickListener levelViewListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            TextView levelView = (TextView) view;
            int minValue = 0;
            int maxValue = 10;
            int value = getIntegerFromTextView(levelView, minValue, maxValue);
            levelDialog.setView(view);
            levelDialog.setValue(value);
            levelDialog.setMinValue(minValue);
            levelDialog.setMaxValue(maxValue);
            levelDialog.show(getFragmentManager(), "LevelDialog");
        }
    };

    // TODO remove all final variables
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final CSVFile csvFile = new CSVFile();
        final EntryParser entryParser = new EntryParser();
        LinkedList<Entry> entries;
        try {
            List<String[]> strings = csvFile.read("Doc", "test");
            entries = entryParser.getEntries(strings);
        } catch (IOException e) {
            entries = new LinkedList<>();
        }
        this.journal.write(entries);
        // TODO separate instructions
        final WorkoutView workoutView = new WorkoutView((Spinner) findViewById(R.id.exerciseSpinner));
        final LevelView levelView = new LevelView((TextView) findViewById(R.id.levelView));
        final GoalView goalView = new GoalView((TextView) findViewById(R.id.goalView));
        final RepetitionView repetitionView = new RepetitionView((LinearLayout) findViewById(R.id.repetitionLayout));
        final NoteView noteView = new NoteView((EditText) findViewById(R.id.noteView));
        workoutView.setAddItemListener(this.addExerciseItemListener);
        levelView.setEditTextListener(this.levelViewListener);
        goalView.setEditTextListener(this.goalViewListener);
        repetitionView.setAddButtonListener(this.addRepeatButtonListener);
        repetitionView.setRepeatViewListener(this.repeatViewListener);
        this.exerciseDialog.set(workoutView);
        this.goalDialogFragment.setWidget(goalView);
        this.addRepetitionDialog.set(repetitionView);
        this.journal.register(workoutView);
        this.journal.register(levelView);
        this.journal.register(goalView);
        this.journal.register(repetitionView);
        this.journal.register(noteView);
        Button writeButton = (Button) findViewById(R.id.writeButton);
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Entry entry = new Entry();
                String workout = workoutView.getSelectedExercise();
                if (workout.equals("+")) {
                    return;
                }
                entry.setWorkout(workout);
                entry.setDate(journal.getCurrentDate());
                entry.setLevel(levelView.getLevel());
                entry.setGoal(goalView.getGoal());
                entry.setSet(repetitionView.getRepetition());
                entry.setNote(noteView.getNote());
                journal.add(entry); // for running app
                String string = entryParser.getString(entry);
                try {
                    csvFile.append("Doc", "test", string);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onDaySelected(Calendar calendar) {
        if (calendar == null) {
            return;
        }
        this.journal.setCurrentDate(calendar);
        this.journal.notifyObservers();
    }

    // get integer value from text view
    private int getIntegerFromTextView(TextView textVeiw, int min, int max) {
        int integer;
        String string = textVeiw.getText().toString();
        try {
            integer = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            integer = 0;
        }
        if(min > integer && integer > max) {
            integer = 0;
        }
        return integer;
    }
}
