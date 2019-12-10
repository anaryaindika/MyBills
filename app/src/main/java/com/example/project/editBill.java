package com.example.project;

import android.app.ActionBar;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class editBill extends AppCompatActivity {

    Button editBill_button;
    EditText billTitle_editText, billTotalAmount_editText, itemAmount_editText;
    DatePicker billDate_datePicker;
    Integer billId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_bill);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar);

        billId = getIntent().getExtras().getInt("Bill ID");

        billTitle_editText = findViewById(R.id.billTitleEdit_editText);
        billTitle_editText.setText(Preferences.getMyBills().get(billId).getBillTitle());
        billTotalAmount_editText = findViewById(R.id.billTotalAmountEdit_editText);
        billTotalAmount_editText.setText(Preferences.getMyBills().get(billId).getBillTotalAmount());
        itemAmount_editText = findViewById(R.id.itemAmountEdit_editText);
        itemAmount_editText.setText(Preferences.getMyBills().get(billId).getBillItemAmount());
        billDate_datePicker = findViewById(R.id.billDateEdit_datePicker);

        editBill_button = findViewById(R.id.editBill_button);

        editBill_button.setOnClickListener(new View.OnClickListener() {
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
                    Preferences.editItem(billId, billTitle, billTotalAmount, billItemAmount, billDateDay, billDateMonth, billDateYear);
                    Preferences.setArrayListSharedPreferences(getBaseContext());

                    startActivity(new Intent(getApplicationContext(), SecondActivity.class));
                    finish();

                    sendNotification("MyBills", "Bill edited !");
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
