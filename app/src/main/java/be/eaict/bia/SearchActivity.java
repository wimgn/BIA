package be.eaict.bia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setTitle("Search");

        DatabaseReference myRef = database.getReference("Cafes");

        final String TAG = "Database interaction: ";

        List<Cafe> afspraken = new ArrayList<Cafe>();

        ListView cafeListView = (ListView) findViewById(R.id.cafe_list_view);
        cafeListView.setAdapter(new CafeAdapter(this, afspraken));
        cafeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                /*Intent i = new Intent(MainActivity.this, DetailActivity.class);
                Appointment afspraak = afspraken.get(position);
                i.putExtra("afspraakID",afspraak.getId());
                startActivity(i);*/
                //TODO door gaan naar cafe detail
            }
        });

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                List<Cafe> afspraken = new ArrayList<Cafe>();
                for (DataSnapshot cafe: dataSnapshot.getChildren()) {
                    Cafe value = cafe.getValue(Cafe.class);
                    Log.d(TAG, "Value is: " + value.Naam);
                    afspraken.add(value);
                }
                ((ListView) findViewById(R.id.cafe_list_view)).setAdapter(new CafeAdapter(SearchActivity.this,afspraken));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }
}
