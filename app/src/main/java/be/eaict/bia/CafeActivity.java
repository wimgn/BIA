package be.eaict.bia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CafeActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe);

        DatabaseReference myRef = database.getReference("Cafes");

        final String TAG = "Database interaction: ";

        List<Cafe> cafes = new ArrayList<Cafe>();

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                List<Bier> bieren = new ArrayList<Bier>();
                for (DataSnapshot cafe: dataSnapshot.getChildren()) {
                    Cafe value = cafe.getValue(Cafe.class);
                    //Log.d(TAG, "Value is: " + value.Naam);
                    if (value.getID() == getIntent().getIntExtra("cafeID",-1)) {
                        ((TextView)findViewById(R.id.Naam)).setText(value.getName());
                        ((TextView)findViewById(R.id.Rating)).setText("Rating: " + value.getRating());

                    }
                }
                //((ListView) findViewById(R.id.cafe_list_view)).setAdapter(new CafeAdapter(SearchActivity.this,afspraken));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }
}
