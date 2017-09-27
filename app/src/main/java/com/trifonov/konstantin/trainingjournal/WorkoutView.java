package com.trifonov.konstantin.trainingjournal;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WorkoutView implements Observer {
    private Spinner spinner;
    private Journal journal = Journal.getInstance();
    private ArrayAdapter<String> adapter;

    public WorkoutView(Spinner spinner) {
        this.spinner = spinner;
        List<Entry> entries = this.journal.getAll();
        List<String> exercises = new ArrayList<>(this.getExercises(entries));
        this.populate(exercises);
    }

    @Override
    public void update() {
        Entry entry = this.journal.getEntry();
        this.spinner.setSelection(this.adapter.getPosition(entry.getWorkout()));
    }

    public void setAddItemListener(AdapterView.OnItemSelectedListener listener) {
        this.spinner.setOnItemSelectedListener(listener);
    }

    public void add(String exercise) {
        this.adapter.insert(exercise, 0);
        this.adapter.notifyDataSetChanged();
        this.spinner.setSelection(this.adapter.getPosition(exercise));
    }

    public String getSelectedExercise() {
        return (String) this.spinner.getSelectedItem();
    }

    private void populate(List<String> strings) {
        this.adapter = new ArrayAdapter<String>(this.spinner.getContext(), R.layout.row_exercise,
                R.id.exerciseTextView, strings);
        this.adapter.add("+");
        this.spinner.setAdapter(this.adapter);
        this.spinner.setSelection(this.adapter.getPosition("+"));
    }

    private Set<String> getExercises(List<Entry> entries) {
        Set<String> exercises = new HashSet<>();
        for (Entry e : entries) {
            exercises.add(e.getWorkout());
        }
        return exercises;
    }
}
