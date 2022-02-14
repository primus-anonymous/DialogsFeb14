package com.neocaptainnemo.dialogsfeb14;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class SimpleAlertDialogFragment extends DialogFragment {

    public static SimpleAlertDialogFragment newInstance(String title, String message) {

        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_MESSAGE, message);

        SimpleAlertDialogFragment  fragment = new SimpleAlertDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String REQUEST_KEY = "SimpleAlertDialogFragment_REQUEST_KEY";

    private static final String ARG_TITLE = "ARG_TITLE";
    private static final String ARG_MESSAGE = "ARG_MESSAGE";

    public static final String ARG_BUTTON = "ARG_BUTTON";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        DialogInterface.OnClickListener clickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Bundle bundle = new Bundle();
                bundle.putInt(ARG_BUTTON, i);

                getParentFragmentManager()
                        .setFragmentResult(REQUEST_KEY, bundle);
            }
        };

        return new AlertDialog.Builder(requireContext())
                .setTitle(requireArguments().getString(ARG_TITLE))
                .setMessage(requireArguments().getString(ARG_MESSAGE))
                .setIcon(R.drawable.ic_launcher_background)
                .setPositiveButton("positive", clickListener)
                .setNeutralButton("Neutral", clickListener)
                .setNegativeButton("Negative", clickListener)
                .create();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCancelable(false);
    }
}
