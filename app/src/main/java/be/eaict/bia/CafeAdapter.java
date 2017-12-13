package be.eaict.bia;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by wimge on 13/12/2017.
 */

public class CafeAdapter extends ArrayAdapter<Cafe> {

    public CafeAdapter(Context context, List<Cafe> cafes) {
        super(context, -1, cafes);
    }
}
