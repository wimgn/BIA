package be.eaict.bia;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wimge on 13/12/2017.
 */

public class CafeAdapter extends ArrayAdapter<Cafe> implements Filterable {

    private Context context;
    private List<Cafe> cafeList;
    private LayoutInflater inflater;
    List<Cafe> mStringFilterList;
    ValueFilter valueFilter;

    public CafeAdapter(Context context, List<Cafe> cafes) {

        super(context, -1, cafes);

        this.context = context;
        this.cafeList = cafes;
        mStringFilterList = cafes;
    }

    @Override
    public int getCount() {
        return cafeList.size();
    }

    @Override
    public Cafe getItem(int i) {
        return cafeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        /*LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(
                R.layout.cafe_list_item, null);

        TextView naam = (TextView)view.findViewById(R.id.cafe_list_naam);
        naam.setText(getItem(i).getName().toString());

        TextView detail = (TextView)view.findViewById(R.id.cafe_list_detail);
        detail.setText("XXX m");


        return v;*/

        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null) {
            view = inflater.inflate(R.layout.cafe_list_item, null);
        }

        TextView txtName = (TextView) view.findViewById(R.id.cafe_list_naam);
        TextView txtDetail = (TextView) view.findViewById(R.id.cafe_list_detail);

        Cafe c = cafeList.get(i);
        String name = c.getName();
        Double rating = c.getRating();

        txtName.setText(name);
        txtDetail.setText(rating.toString());
        return view;

    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<Cafe> filterList = new ArrayList<Cafe>();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ((mStringFilterList.get(i).getName().toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {

                        Cafe cafe = new Cafe(
                                mStringFilterList.get(i).getID(),
                                mStringFilterList.get(i).getName(),
                                mStringFilterList.get(i).getRating(),
                                mStringFilterList.get(i).getLatitude(),
                                mStringFilterList.get(i).getLongitude());

                        filterList.add(cafe);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            cafeList = (ArrayList<Cafe>) results.values;
            notifyDataSetChanged();
        }

    }

}
