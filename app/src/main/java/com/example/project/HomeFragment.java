package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {

    TextView billTitle_textView, billDate_textView, billTotalAmount_textView;

    FloatingActionButton floatingActionButton;

    RecyclerView recyclerView;

    public static RecyclerView.Adapter adapter;

    Context context;

    public HomeFragment(Context context) {
        // Required empty constructor;
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.bill_listView);
        adapter = new CustomAdapter(context);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        floatingActionButton = view.findViewById(R.id.createBill_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddBill.class);
                startActivity(intent);
            }
        });

        billTitle_textView = view.findViewById(R.id.billTitle_textView);
        billDate_textView = view.findViewById(R.id.dateAdd_textView);
        billTotalAmount_textView = view.findViewById(R.id.totalAmountAdd_textView);

        return view;
    }

}
