package be.eaict.bia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference myRef = database.getReference("Cafes");

        final String TAG = "Database interaction: ";

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot cafe: dataSnapshot.getChildren()) {
                    Cafe value = cafe.getValue(Cafe.class);
                    Log.d(TAG, "Value is: " + value.Naam);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

    public void PushToFirebase(View v) {
        // Write a message to the database

        DatabaseReference myRef = database.getReference("Cafes");

        Cafe c = new Cafe("De Kroeg", 4.5);

        Bier b = new Bier("Stella",1.60);
        c.AddBier(b);

        //blablabla

        myRef.push().setValue(c);
    }
}