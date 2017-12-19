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

    public Cafe(int id, String n, Double r) {
        id = id;
        name = n;
        rating = r;
    }

    private int id;
    private String name;
    private Double rating;

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
}
