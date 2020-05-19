package data.Database;

import data.Course.RegistrationRequest;
import data.Exception.NotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * {@code SessionWishlist} defines a list of RegistrationRequests
 * It is used by the {@code Scheduler} to create a schedule
 * @see Database
 * @see app.Algorithms.Scheduler
 * @see RegistrationRequest
 * @author Lucas Demchik
 * @version 0.1
 */
public class SessionWishlist implements Database {
    private final static SessionWishlist swl = new SessionWishlist();
    private ArrayList<RegistrationRequest> wl;

    /**
     * Initializes the database from a file
     */
    private SessionWishlist(){
        wl = new ArrayList<>();
        try{
            loadWishList();
        }
        catch(FileNotFoundException ex){
            System.out.println("SESSION WISHLIST FILE NOT FOUND");
        }
    }

    /**
     * @return a reference to the SessionWishlist Instance
     */
    public static SessionWishlist getInstance() { return swl; }

    /**
     * @return a reference to the ArrayList
     */
    public ArrayList<RegistrationRequest> getWishlist() { return wl; }

    /**
     * Loads wishlist with {@code RegistrationRequests} by reading from an input file
     * @throws FileNotFoundException if the file cannot be found
     * @see RegistrationRequest
     */
    private void loadWishList() throws FileNotFoundException {
        File file = new File("src\\data\\Input\\sessionwishlist.txt");
        Scanner input = new Scanner(file);

        String[] s;
        try {
            while (input.hasNextLine()) {
                s = input.nextLine().split(",");
                wl.add(new RegistrationRequest(s));
            }
        }
        catch(NotFoundException ex){
            System.out.println(ex.getMessage());
        }
        input.close();
    }
}
