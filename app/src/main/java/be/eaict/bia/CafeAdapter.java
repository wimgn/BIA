package be.eaict.bia;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wimge on 13/12/2017.
 */

public class CafeAdapter extends ArrayAdapter<Cafe> {

    public CafeAdapter(Context context, List<Cafe> cafes) {
        super(context, -1, cafes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(
                R.layout.cafe_list_item, null);

        TextView naam = (TextView)view.findViewById(R.id.cafe_list_naam);
        naam.setText(getItem(position).Naam.toString());

        TextView detail = (TextView)view.findViewById(R.id.cafe_list_detail);
        detail.setText("XXX m");


        return view;
    }
}
