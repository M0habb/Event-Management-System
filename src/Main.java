import java.io.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException {
        Date DOB = new Date(2006, 07, 23);
        Date DOB2 = new Date(2006, 06, 16);
        Attendee test = new Attendee("Mohab", "123", Gender.MALE, DOB, 1555731627);
        Attendee test2 = new Attendee("Mohamed", "213", Gender.MALE, DOB2, 5232624);
    }
}