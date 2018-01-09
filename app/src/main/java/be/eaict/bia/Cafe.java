package be.eaict.bia;

import android.nfc.tech.NfcA;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wimge on 13/12/2017.
 */

public class Cafe {

    public Cafe() {

    }

    public Cafe(int id, String n, Double r, Double Lan, Double Lon) {
        this.id = id;
        name = n;
        rating = r;
        latitude = Lan;
        longitude = Lon;
    }

    private int id;
    private String name;
    private Double rating;
    private double latitude;
    private double longitude;

    public List<Bier> Lijst = new ArrayList<Bier>();

    public void AddBier(Bier b) {
        Lijst.add(b);
    }

    public int getID(){
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getRating() {
        return rating;
    }

    public  Double getLatitude(){return latitude;}

    public  Double getLongitude(){return  longitude;}
}
