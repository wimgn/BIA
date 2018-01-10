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

        //getSupportActionBar().setTitle("BIA");

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

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

    public void push(View v) {
        // Write a message to the database

        DatabaseReference myRef = database.getReference("Cafes");

        l = new ArrayList<Cafe>();

        l.add(new Cafe(0,"Sals Cafe",3.8,51.21982,4.401116));  //Lat = 51.219182 Lon = 4.401116
        l.add(new Cafe(1,"De Kroeg",4.2,51.219262,4.401123));
        l.add(new Cafe(2,"De Prof",3.7,51.223632,4.411556));      //Lat = 51.223632 Lon = 4,411556
        l.add(new Cafe(3,"Barbier",4.3,51.223765,4.407107));      //Lat = 51.223765 Lon = 4.407107
        l.add(new Cafe(4,"De Kat",4.5,51.221494,4.403621));       //Lat = 51.221494 Lon = 4.403621
        l.add(new Cafe(5,"'t Klokske",4.1,51.219796,4.401689));   //Lat = 51.219796 Lon = 4.401689
        l.add(new Cafe(6,"'t Bolleke",4.5,51.219538,4.401099));   //Lat = 51.219538 Lon = 4.401099
        l.add(new Cafe(7,"Stoop",4.0,51.219598,4.401949));        //Lat = 51.219598 Lon = 4.401949
        l.add(new Cafe(8,"Den Engel",4.3,51.221634,4.399603));    //Lat = 51.221634 Lon = 4.399603
        l.add(new Cafe(9,"Soho",4.0,51.217796,4.394916));         //Lat = 51.217796 Lon = 4.394916
        l.add(new Cafe(10,"Hopper",3.8,51.208311,4.392332));      //Lat = 51.208311 Lon = 4.392332
        l.add(new Cafe(11,"Zuidcafe",3.7,51.209886,4.394763));    //Lat = 51.209886 Lon = 4.394763
        l.add(new Cafe(12,"Revista",4.2,51.209594,4.394832));     //Lat = 51.209594 Lon = 4.394832
        l.add(new Cafe(13,"Nick's Cafe",4.2,51.211324,4.390874)); //Lat = 51.211324 Lon = 4.390874
        l.add(new Cafe(14,"Cafe Local",3.6,51.210660,4.389640));  //Lat = 51.210660 Lon = 4.389640
        l.add(new Cafe(15,"De Baron",4.1,51.210628,4.397924));    //Lat = 51.210628 Lon = 4.397924
        l.add(new Cafe(16,"De Nieuwe Linde",4.2,51.206249,4.389700));  //Lat = 51.206249 Lon = 4.389700


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

        for (int i = 0; i < l.size(); i++) {
            l.get(i).AddBier(Stella);
            l.get(i).AddBier(Jupiler);
            l.get(i).AddBier(Leffe_Blond);
            l.get(i).AddBier(Leffe_Donker);
            l.get(i).AddBier(Tripel_Karmeliet);
            l.get(i).AddBier(Westmalle_Tripel);
            l.get(i).AddBier(Westmalle_Dubbel);
            l.get(i).AddBier(Rochefort);
            l.get(i).AddBier(Chimay_Blauw);
            l.get(i).AddBier(Chimay_Rood);
            l.get(i).AddBier(Duvel);
            l.get(i).AddBier(Kasteelbier_Donker);
            myRef.push().setValue(l.get(i));
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
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                        Log.w(TAG, "Failed to read value.", databaseError.toException());
                    }
                });


    }
}
