package be.eaict.bia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    CafeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

       // getSupportActionBar().setTitle("Search");

        DatabaseReference myRef = database.getReference("Cafes");

        final String TAG = "Database interaction: ";

        final List<Cafe> cafes = new ArrayList<Cafe>();

        ListView cafeListView = (ListView) findViewById(R.id.cafe_list_view);
        adapter = new CafeAdapter(this, cafes);
        cafeListView.setAdapter(adapter);
        cafeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(SearchActivity.this, CafeActivity.class);
                Cafe c = (Cafe)adapterView.getItemAtPosition(position);
                i.putExtra("cafeID",c.getID());
                startActivity(i);
            }
        });

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                List<Cafe> c = new ArrayList<Cafe>();
                for (DataSnapshot cafe: dataSnapshot.getChildren()) {
                    Cafe value = cafe.getValue(Cafe.class);
                    Log.d(TAG, "Value is: " + value.getName());
                    c.add(value);
                }

                adapter = new CafeAdapter(SearchActivity.this,c);
                ((ListView) findViewById(R.id.cafe_list_view)).setAdapter(adapter);
                ((SearchView) findViewById(R.id.search_view_cafe)).setOnQueryTextListener(SearchActivity.this);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        ((SearchView) findViewById(R.id.search_view_cafe)).setOnQueryTextListener(SearchActivity.this);

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        adapter.getFilter().filter(s.trim());
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.getFilter().filter(s.trim());
        return false;
    }
}
