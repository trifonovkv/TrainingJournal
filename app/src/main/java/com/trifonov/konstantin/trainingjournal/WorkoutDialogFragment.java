package com.trifonov.konstantin.trainingjournal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class WorkoutDialogFragment extends DialogFragment {
    private WorkoutView workoutView;
    private View root;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        this.root = inflater.inflate(R.layout.dialog_exercise, null);

        builder.setView(root)
                .setTitle(R.string.new_exercise_title)
                .setPositiveButton(R.string.enter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        addExercise();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }

    public void set(WorkoutView workoutView) {
        this.workoutView = workoutView;
    }

    private void addExercise() {
        EditText exercise = this.root.findViewById(R.id.exerciseEditText);
        this.workoutView.add(exercise.getText().toString());
    }
}
