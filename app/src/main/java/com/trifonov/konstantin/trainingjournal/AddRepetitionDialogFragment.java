package com.trifonov.konstantin.trainingjournal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

public class AddRepetitionDialogFragment extends DialogFragment {
    private RepetitionView repetitionView;
    private NumberPicker repeats;
    private static final int defaultValue = 0;
    private int maxValue;
    private int minValue;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View root = inflater.inflate(R.layout.dialog_repetition, null);
        this.repeats = root.findViewById(R.id.repeatPicker);
        this.repeats.setMaxValue(this.maxValue);
        this.repeats.setMinValue(this.minValue);
        this.repeats.setValue(defaultValue);
        builder.setView(root)
                .setTitle(R.string.new_repetition_title)
                .setPositiveButton(R.string.enter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        addRepetition(repeats.getValue());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }

    public void set(RepetitionView repetitionView) {
        this.repetitionView = repetitionView;
    }

    public void setMaxNumberPicker(int value) {
        this.maxValue = value;
    }

    public void setMinNumberPicker(int value) {
        this.minValue = value;
    }

    private void addRepetition(int repeats) {
        this.repetitionView.add(repeats);
    }
}
