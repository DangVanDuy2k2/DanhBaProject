package com.example.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.example.danhbaproject.R;
import com.example.model.Contact;

import java.util.List;

public class ContactAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Contact> listContact;

    public ContactAdapter(Context context, int layout, List<Contact> listContact) {
        this.context = context;
        this.layout = layout;
        this.listContact = listContact;
    }

    @Override
    public int getCount() {
        return listContact.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //view hiển thị trên một dòng của lay out
        view = inflater.inflate(layout,null);

        TextView txtName = view.<TextView>findViewById(R.id.txtName);
        TextView txtPhone = view.<TextView>findViewById(R.id.txtPhone);
        ImageButton btnCall = view.<ImageButton>findViewById(R.id.btnCall);
        ImageButton btnSms = view.<ImageButton>findViewById(R.id.btnSms);
        ImageButton btnDetail = view.<ImageButton>findViewById(R.id.btnDetail);

        Contact contact = listContact.get(i);
        txtName.setText(contact.getName());
        txtPhone.setText(contact.getPhone());

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyCall(contact);
            }
        });

        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLySms(contact);
            }
        });

        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyDetail(contact);
            }
        });

        return view;
    }

    private void xuLyCall(Contact contact) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+contact.getPhone()));
        //Kiểm tra xem đã cấp quyền gọi điện thoại hay chưa
        if(ActivityCompat.checkSelfPermission(this.context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            //Nếu chưa cấp quyên thì đưa ra requestPermission
            ActivityCompat.requestPermissions((Activity) this.context,new String[] {Manifest.permission.CALL_PHONE},1);
            return;
        }
        this.context.startActivity(intent);
    }

    private void xuLySms(Contact contact) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:"+contact.getPhone()));
        this.context.startActivity(intent);
    }

    private void xuLyDetail(Contact contact) {
        this.listContact.remove(contact);
        this.notifyDataSetChanged();
    }
}
