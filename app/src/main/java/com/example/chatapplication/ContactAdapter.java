package com.example.chatapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.BaseAdapter;
import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ContactModel> contacts;

    public ContactAdapter(Context context, ArrayList<ContactModel> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_contact, parent, false);
        }

        TextView tvName = convertView.findViewById(R.id.tv_contact_name);
        TextView tvPhone = convertView.findViewById(R.id.tv_contact_phone);

        ContactModel contact = contacts.get(position);
        tvName.setText(contact.getName());
        contact.getPhoneNumber();

        return convertView;
    }
}

