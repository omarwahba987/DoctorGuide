package com.example.mahfouz.doctorgp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by MAHFOUZ on 13/2/2018.
 */

public class CustomeListAdapter extends ArrayAdapter<Doctor>{

    Context context;
    int resource;
    ArrayList<Doctor> doctorList;

    public CustomeListAdapter(@NonNull Context context, @LayoutRes int resource,@NonNull ArrayList<Doctor> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.doctorList = objects;
    }

    @Nullable
    @Override
    public Doctor getItem(int position) {
        return doctorList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(resource ,null);
        viewHolder holder = new viewHolder();

        holder.docimg = (CircularImageView) convertView.findViewById(R.id.doctorimg);
        holder.docname = (TextView) convertView.findViewById(R.id.docname);
        holder.docspec = (TextView) convertView.findViewById(R.id.docspecc);
        holder.docnum = (TextView) convertView.findViewById(R.id.docnum);
        holder.docacadm = (TextView) convertView.findViewById(R.id.docacadm);
       // holder.direct = (Button) convertView.findViewById(R.id.btndirection);



        holder.docname.setText(doctorList.get(position).getDoctorName());
        holder.docspec.setText(doctorList.get(position).getSpeciality());
        holder.docnum.setText(doctorList.get(position).getPhoneNumber());
        holder.docacadm.setText(doctorList.get(position).getAcademicInfo());
       // holder.direct.setText("get");

        if (!doctorList.get(position).getPhotoUrl().equals("null")) {
            Picasso.with(context).load(doctorList.get(position).getPhotoUrl()).into(holder.docimg);
        }else {
            holder.docimg.setImageResource(R.mipmap.ic_launcher);
        }


        return convertView;
    }

    class  viewHolder {
        CircularImageView docimg;
        TextView docname;
        TextView docspec;
        TextView docnum;
        TextView docacadm;
        //Button direct;
    }
}
