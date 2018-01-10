package be.eaict.bia;

/**
 * Created by wimge on 13/12/2017.
 */

public class Bier {

    public Bier() {

    }

    public Bier(String n, Double p) {
        _naam = n;
        _prijs = p;
    }

    public String _naam;
    public Double _prijs;

    public String getName (){return _naam;}

}
