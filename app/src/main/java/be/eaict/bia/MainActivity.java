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
            l.add(new Cafe(17,"Bla bla",4.5));
            /*l.add(new Cafe(0,"Sals Cafe",3.8));
            l.add(new Cafe(1,"De Kroeg",4.2));
            l.add(new Cafe(2,"De Prof",3.7));
            l.add(new Cafe(3,"Barbier",4.3));
            l.add(new Cafe(4,"De Kat",4.5));
            l.add(new Cafe(5,"'t Klokske",4.1));
            l.add(new Cafe(6,"'t Bolleke",4.5));
            l.add(new Cafe(7,"Stoop",4.0));
            l.add(new Cafe(8,"Den Engel",4.3));
            l.add(new Cafe(9,"Soho",4.0));
            l.add(new Cafe(10,"Hopper",3.8));
            l.add(new Cafe(11,"Zuidcafe",3.7));
            l.add(new Cafe(12,"Revista",4.2));
            l.add(new Cafe(13,"Nick's Cafe",4.2));
            l.add(new Cafe(14,"Cafe Local",3.6));
            l.add(new Cafe(15,"De Baron",4.1));
            l.add(new Cafe(16,"De Nieuwe Linde",4.2));
            */

            Bier Stella = new Bier("Stella",2.5);
            Bier Jupiler = new Bier("Jupiler",1.6);
            Bier Leffe_Blond = new Bier("Leffe Blond",3.5);
            Bier Tripel_Karmeliet = new Bier("Tripel Karmeliet",3.5);
            Bier Leffe_Donker = new Bier("Leffe Donker",3.5);
            Bier Westmalle_Dubbel = new Bier("Westmalle Dubbel",3.5);
            Bier Westmalle_Tripel = new Bier("Westmalle Tripel",3.5);
            Bier Rochefort = new Bier("Rochefort",3.5);
            Bier Chimay_Rood = new Bier("Chimay Rood",3.5);
            Bier Chimay_Blauw = new Bier("Chimay Blauw",3.5);
            Bier Duvel = new Bier("Duvel",3.5);
            Bier Kasteelbier_Donker = new Bier("Kasteelbier Donker",3.5);

            c.AddBier(Stella);
            c.AddBier(Jupiler);
            c.AddBier(Leffe_Blond);
            c.AddBier(Leffe_Donker);

            myRef.push().setValue(c);
        }


    }

    public void OpenSearch(View v) {
        Intent i = new Intent(MainActivity.this,SearchActivity.class);

        startActivity(i);
    }

    public void MapsCheck(View v)
    {
        Intent i = new Intent(MainActivity.this,MapsActivity.class);
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
