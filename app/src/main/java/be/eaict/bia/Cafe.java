package be.eaict.bia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wimge on 13/12/2017.
 */

public class Cafe {

    public Cafe() {

    }

    public Cafe(int id, String n, Double r) {
        ID = id;
        Naam = n;
        Rating = r;
    }

    private int ID;
    public String Naam;
    public Double Rating;
    public List<Bier> _lijst = new ArrayList<Bier>();

    public void AddBier(Bier b) {
        _lijst.add(b);
    }

    public int getID(){
        return ID;
    }
}
