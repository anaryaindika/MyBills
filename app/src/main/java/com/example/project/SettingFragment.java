package com.example.project;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Set;

public class SettingFragment extends Fragment {

    // Inisiasi variabel layout
    private EditText currentPassword_editText, newPassword_editText, addPersonName_editText;
    private Button changePassword_button, addPerson_button;

    // Inisiasi variabel masukan user
    private String currentPassword, newPassword, addPersonName;

    // Inisiasi variabel untuk cek masukan user
    private View focus = null;
    private boolean cancel = false;

    // Inisialisasi activity context
    private Context context;

    public SettingFragment(Context context) {
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        // Get layout
        currentPassword_editText = view.findViewById(R.id.currentPassword_editText);
        newPassword_editText = view.findViewById(R.id.newPassword_editText);
        addPersonName_editText = view.findViewById(R.id.friendName_editText);
        changePassword_button = view.findViewById(R.id.changePassword_button);
        addPerson_button = view.findViewById(R.id.addFriend_button);

        // Listener click
        changePassword_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });

        addPerson_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFriend();
            }
        });
        return view;
    }

    private void changePassword() {
        // Get user input
        currentPassword = currentPassword_editText.getText().toString();
        newPassword = newPassword_editText.getText().toString();

        if (TextUtils.isEmpty(currentPassword)) {
            currentPassword_editText.setError("This field is required");
            focus = currentPassword_editText;
            cancel = true;
        } else if (!checkCurrentPassword(currentPassword)) {
            currentPassword_editText.setError("Password incorrect");
            focus = newPassword_editText;
            cancel = true;
        }

        if (TextUtils.isEmpty(newPassword)) {
            newPassword_editText.setError("This field is required");
            focus = newPassword_editText;
            cancel = true;
        }

        if (cancel) {
            focus.requestFocus();
        } else {
            Preferences.getUserAccount().get(0).setPassword(newPassword);
            Preferences.setUserSharedPreferences(context);
            Toast toast = Toast.makeText(context, "Password changed to: " + Preferences.getUserAccount().get(0).getPassword(), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private boolean checkCurrentPassword(String currentPassword) {
        return currentPassword.equals(Preferences.getUserAccount().get(0).getPassword());
    }

    private void addFriend() {
        // Get user input
        addPersonName = addPersonName_editText.getText().toString();

        if (TextUtils.isEmpty(addPersonName)) {
            addPersonName_editText.setError("This field is required");
            focus = currentPassword_editText;
            cancel = true;
        }

        if (cancel) {
            focus.requestFocus();
        } else {
            // Inisiasi variabel untuk preferensi teman
            Preferences.insertFriend(addPersonName);
            Preferences.setFriendsSharedPreferences(context);

            Toast toast = Toast.makeText(context, Preferences.getFriends().get(Preferences.getMyBills().size() - 1).getName() + " added !", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
