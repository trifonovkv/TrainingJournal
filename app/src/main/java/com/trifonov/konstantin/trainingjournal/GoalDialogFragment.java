package com.trifonov.konstantin.trainingjournal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class GoalDialogFragment extends DialogFragment {
    private View root;
    private EditText editText;
    private Widget widget;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        this.root = inflater.inflate(R.layout.dialog_enter_text, null);
        this.editText = root.findViewById(R.id.editText);
        builder.setView(root)
                .setTitle(R.string.set_goal_title)
                .setPositiveButton(R.string.enter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        widget.setText(editText.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }

    public void setWidget(Widget widget) {
        this.widget= widget;
    }
}
