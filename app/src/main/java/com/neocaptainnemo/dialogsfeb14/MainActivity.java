package com.neocaptainnemo.dialogsfeb14;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentResultListener;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    public static final String CHANNEL_ID = "CHANNEL_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationChannelCompat channelCompat = new NotificationChannelCompat.Builder(CHANNEL_ID, NotificationCompat.PRIORITY_DEFAULT)
                .setName("Channel Name")
                .build();

        NotificationManagerCompat.from(this)
                .createNotificationChannel(channelCompat);


        getSupportFragmentManager()
                .setFragmentResultListener(SimpleAlertDialogFragment.REQUEST_KEY, this, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        int button = result.getInt(SimpleAlertDialogFragment.ARG_BUTTON);

                        switch (button) {
                            case DialogInterface.BUTTON_POSITIVE:
                                Toast.makeText(MainActivity.this, "positive", Toast.LENGTH_LONG).show();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                Toast.makeText(MainActivity.this, "negative", Toast.LENGTH_LONG).show();
                                break;

                            case DialogInterface.BUTTON_NEUTRAL:
                                Toast.makeText(MainActivity.this, "neutral", Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                });

        findViewById(R.id.toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "Toast", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.snack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Snack", Snackbar.LENGTH_INDEFINITE).show();
            }
        });

        findViewById(R.id.snack_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Snack", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Snackbar.make(view, "Action For Snack", Snackbar.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        findViewById(R.id.alert_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Title")
                        .setMessage("Message")
                        .setIcon(R.drawable.ic_launcher_background)
                        .setPositiveButton("positive", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "positive", Toast.LENGTH_LONG).show();

                            }
                        })
                        .setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "Neutral", Toast.LENGTH_LONG).show();

                            }
                        })
                        .setNegativeButton("Negative", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "Negative", Toast.LENGTH_LONG).show();

                            }
                        })
                        .setCancelable(false)
                        .show();
            }
        });

        findViewById(R.id.alert_dialog_custom).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                View customTitle = getLayoutInflater().inflate(R.layout.custom_dialog_title, null);
                customTitle.<TextView>findViewById(R.id.title).setText("My Custom Title");

                View customView = getLayoutInflater().inflate(R.layout.custom_dialog_view, null);
                EditText editText = customView.findViewById(R.id.edit);

                new AlertDialog.Builder(MainActivity.this)
                        .setCustomTitle(customTitle)
                        .setView(customView)
                        .setPositiveButton("positive", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, editText.getText().toString(), Toast.LENGTH_LONG).show();

                            }
                        })
                        .setCancelable(false)
                        .show();
            }
        });

        findViewById(R.id.alert_dialog_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleAlertDialogFragment.newInstance("My Title Here", "My Message Here").show(getSupportFragmentManager(), "SimpleAlertDialogFragment");
            }
        });

        findViewById(R.id.custom_dialog_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomPreCustomDialogFragment.newInstance().show(getSupportFragmentManager(), "CustomPreCustomDialogFragment");
            }
        });

        findViewById(R.id.bottom_sheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyBottomSheetDialogFragment.newInstance().show(getSupportFragmentManager(), "MyBottomSheetDialogFragment");
            }
        });

        findViewById(R.id.notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, MainActivity.class);

                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                Notification notification = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                        .setContentTitle("Content Title")
                        .setContentText("Content Text")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentIntent(pendingIntent)
                        .build();

                NotificationManagerCompat.from(MainActivity.this)
                        .notify(1, notification);

            }
        });

        findViewById(R.id.dialog_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String[] items = {"1", "2", "3", "4", "5"};

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Title")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, items[i], Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
            }
        });

        findViewById(R.id.single_choice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String[] items = {"1", "2", "3", "4", "5"};

                final int[] selectedIndex = {-1};

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Title")
                        .setSingleChoiceItems(items, selectedIndex[0], new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                selectedIndex[0] = i;
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, items[selectedIndex[0]], Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }

        });

        findViewById(R.id.multiple_choice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String[] items = {"1", "2", "3", "4", "5"};

                boolean[] selected = {false, false, false, false, false};

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Title")
                        .setMultiChoiceItems(items, selected, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                selected[i] = b;
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                StringBuilder stringBuilder = new StringBuilder();

                                for (int j = 0; j < items.length; j++) {
                                    if (selected[j]) {
                                        stringBuilder.append(items[j]);
                                        stringBuilder.append(", ");
                                    }
                                }


                                Toast.makeText(MainActivity.this, stringBuilder.toString(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }

        });

    }
}