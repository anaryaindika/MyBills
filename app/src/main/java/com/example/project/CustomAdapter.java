package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter <CustomAdapter.CustomViewHolder> {

    LayoutInflater layoutInflater;
    Context context;

    public CustomAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create view from the layout
        View itemView = layoutInflater.inflate(R.layout.bill_rowview, parent, false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        // Retrieve the data for that position
        final Bills current = Preferences.getMyBills().get(position);

        // Add the data to the view
        holder.billTitle_textView.setText(current.getBillTitle());
        holder.totalAmount_textView.setText(current.getBillTotalAmount());
        holder.billDate_textView.setText(current.getBillDateDay() + "/" + current.getBillDateMonth() + "/" + current.getBillDateYear());
        holder.bill_cardView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                MenuItem delete = menu.add(Menu.NONE, 1, 1, "Delete");
                MenuItem edit = menu.add(Menu.NONE, 2, 2, "Edit");
                delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        removeItem(position);
                        return true;
                    }
                });
                edit.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent = new Intent(context, editBill.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("Bill ID", position);
                        context.startActivity(intent);
                        return true;
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        // Return the number of data items to display
        return Preferences.getMyBills().size();
    }

    public void removeItem(int position) {
        Preferences.getMyBills().remove(position);
        Preferences.setArrayListSharedPreferences(context);
        notifyDataSetChanged();
        Toast toast = Toast.makeText(context, "Bill removed !", Toast.LENGTH_SHORT);
        toast.show();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView billTitle_textView, billDate_textView, totalAmount_textView;
        CardView bill_cardView;
        public CustomViewHolder(View itemView) {
            super(itemView);

            // Get the layout
            billTitle_textView = itemView.findViewById(R.id.billTitle_textView);
            billDate_textView = itemView.findViewById(R.id.billDate_textView);
            totalAmount_textView = itemView.findViewById(R.id.billTotalAmount_textView);
            bill_cardView = itemView.findViewById(R.id.bill_cardView);
        }
    }
}
