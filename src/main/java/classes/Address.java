package classes;
// user's personal address
public class Address {
    public String city;
    public String street;
    public long postalCode;
    public String country;
    public Address(String country, String city, String street, long postalCode){
        this.country = country;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return street + ", " + city + ", " + country + ". " + postalCode;
    }
}
