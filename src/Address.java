public class   Address {
    String city;
    String street;
    long postalCode;
    String country;
    Address(String country, String city,String street, long postalCode){
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
