package kr.saintdev.dcalarmapp.views.alert;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;


public class AlertFunctions {
    public static void openAlert(Context context, String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }

    public static void openAlert(Context context, int title, int message) {
        String msgS = context.getResources().getString(message);
        String titleS = context.getResources().getString(title);
        openAlert(context, titleS, msgS);
    }

    public static ProgressDialog openProgress(Context context, String msg) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(msg);
        dialog.setCancelable(false);
        dialog.show();

        return dialog;
    }

    public static ProgressDialog openProgress(Context context, int msg){
        String msgS = context.getResources().getString(msg);
        return openProgress(context, msgS);
    }

    interface OnAlertConfirmClickListener {
        void onPositive();
        void onNegative();
    }

    public static void openConfirm(Context context, String title, String msg, String positiveText, String negativeText, final OnAlertConfirmClickListener listener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                listener.onPositive();
            }
        });
        alertDialog.setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                listener.onPositive();
            }
        });

        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.show();
    }

    public static void openConfirm(Context context, int title, int msg, int positiveText, int negativeText, OnAlertConfirmClickListener listener) {
        String titleS = context.getResources().getString(title);
        String msgS = context.getResources().getString(msg);
        String pS = context.getResources().getString(positiveText);
        String nS = context.getResources().getString(negativeText);

        openConfirm(context, titleS, msgS, pS, nS, listener);
    }
}
