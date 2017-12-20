package be.eaict.bia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    List<Cafe> l = new ArrayList<Cafe>();
    int nextID = 0;
    List<String> currentKeys = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("BIA");

        DatabaseReference myRef = database.getReference("Cafes");

        final String TAG = "Database interaction: ";


        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                l = new ArrayList<Cafe>();

                for (DataSnapshot cafe: dataSnapshot.getChildren()) {
                    Cafe value = cafe.getValue(Cafe.class);
                    Log.d(TAG, "Value is: " + value.getName());
                    //Log.d(TAG, cafe.getKey());
                    l.add(value);
                }
                int temp = 0;
                if(l.size() > 0) {
                    temp = l.get(l.size()-1).getID();
                    nextID = temp + 1;
                }
                ((TextView)findViewById(R.id.main_test)).setText(Integer.toString(nextID));

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

        for (int i = 0; i < 5; i++) {
            int temp = nextID + i;
            Cafe c = new Cafe(temp,"De Kroeg " + temp, 4.5);

            Bier b = new Bier("Stella",1.60);
            c.AddBier(b);

            myRef.push().setValue(c);
        }


    }

    public void OpenSearch(View v) {
        Intent i = new Intent(MainActivity.this,SearchActivity.class);

        startActivity(i);
    }

    public void KeyCheck(View v) {
        DatabaseReference myRef = database.getReference("Cafes");

        final String TAG = "Key checks: ";

        myRef.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        currentKeys = new ArrayList<String>();

                        for (DataSnapshot cafe: dataSnapshot.getChildren()) {
                            Cafe value = cafe.getValue(Cafe.class);
                            //Log.d(TAG,cafe.getKey());
                            currentKeys.add(cafe.getKey());
                        }

                        TextView txtKey = ((TextView)findViewById(R.id.main_keyTest));
                        if(currentKeys.size() > 0) {
                            txtKey.setText(currentKeys.get(currentKeys.size()-1));
                        } else {
                            txtKey.setText("");
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                        Log.w(TAG, "Failed to read value.", databaseError.toException());
                    }
                });


    }
}
