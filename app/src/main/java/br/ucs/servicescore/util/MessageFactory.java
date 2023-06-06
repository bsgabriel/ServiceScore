package br.ucs.servicescore.util;

import android.app.Activity;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import br.ucs.servicescore.interfaces.MessageCallback;

public class MessageFactory {

    public static void showInputDialog(Activity activity, String title, final MessageCallback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);

        final EditText input = new EditText(activity);
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String userInput = input.getText().toString();
            if (callback != null) {
                callback.onConfirm(userInput);
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> {
            dialog.cancel();
            if (callback != null) {
                callback.onCancel();
            }
        });

        builder.show();
    }

}
