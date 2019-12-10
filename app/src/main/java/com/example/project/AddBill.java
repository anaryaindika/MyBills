package com.example.project;

import android.app.ActionBar;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AddBill extends AppCompatActivity {

    Button addBill_button;
    EditText billTitle_editText, billTotalAmount_editText, itemAmount_editText;
    DatePicker billDate_datePicker;
    RecyclerView recyclerView;
    public static RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_bill);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar);

        Preferences.getFriendsSharedPreferences(getBaseContext());

        addBill_button = findViewById(R.id.addBill_button);

        billTitle_editText = findViewById(R.id.billTitleAdd_editText);
        billTotalAmount_editText = findViewById(R.id.billTotalAmountAdd_editText);
        itemAmount_editText = findViewById(R.id.itemAmountAdd_editText);
        billDate_datePicker = findViewById(R.id.billDateAdd_datePicker);

        addBill_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                billTitle_editText.setError(null);
                billTotalAmount_editText.setError(null);
                itemAmount_editText.setError(null);

                View view = null;
                boolean isEmpty = false;

                String billTitle = billTitle_editText.getText().toString();
                String billTotalAmount = billTotalAmount_editText.getText().toString();
                String billItemAmount = itemAmount_editText.getText().toString();
                String billDateDay = Integer.toString(billDate_datePicker.getDayOfMonth());
                String billDateMonth = Integer.toString(billDate_datePicker.getMonth());
                String billDateYear = Integer.toString(billDate_datePicker.getYear());

                if (TextUtils.isEmpty(billTitle)) {
                    billTitle_editText.setError("This field is required");
                    view = billTitle_editText;
                    isEmpty = true;
                } else if (TextUtils.isEmpty(billTotalAmount)) {
                    billTotalAmount_editText.setError("This field is required");
                    view = billTotalAmount_editText;
                    isEmpty = true;
                } else if (TextUtils.isEmpty(billItemAmount)) {
                    itemAmount_editText.setError("This field is required");
                    view = itemAmount_editText;
                    isEmpty = true;
                }

                if (isEmpty) {
                    view.requestFocus();
                } else {
                    Preferences.insertItem(billTitle, billTotalAmount, billItemAmount, billDateDay, billDateMonth, billDateYear);
                    Preferences.setArrayListSharedPreferences(getBaseContext());

                    startActivity(new Intent(getApplicationContext(), SecondActivity.class));
                    finish();

                    sendNotification("MyBills", "Bill added !");
                }
            }
        });
    }

    private void sendNotification(String title, String message) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getBaseContext());
        mBuilder.setSmallIcon(R.drawable.ic_action_user);
        mBuilder.setContentTitle(title);
        mBuilder.setContentText(message);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(001, mBuilder.build());
    }
}
