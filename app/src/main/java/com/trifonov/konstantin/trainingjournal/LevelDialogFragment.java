package com.trifonov.konstantin.trainingjournal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Locale;

public class LevelDialogFragment extends DialogFragment {
    private View root;
    private NumberPicker picker;
    private int maxValue;
    private int minValue;
    private int value;
    private View view;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        this.root = inflater.inflate(R.layout.dialog_repetition, null);
        this.picker = root.findViewById(R.id.repeatPicker);
        this.picker.setMaxValue(this.maxValue);
        this.picker.setMinValue(this.minValue);
        this.picker.setValue(this.value);
        builder.setView(root)
                .setTitle(R.string.set_level_title)
                .setPositiveButton(R.string.enter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String string = String.format(Locale.getDefault(), "%d", picker.getValue());
                        ((TextView) view).setText(string);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setView(View view) {
        this.view = view;
    }
}
