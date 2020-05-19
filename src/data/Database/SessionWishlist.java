package data.Database;

import data.Course.RegistrationRequest;
import data.Exception.NotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SessionWishlist implements Database {
    private final static SessionWishlist swl = new SessionWishlist();
    private ArrayList<RegistrationRequest> wl;

    private SessionWishlist(){
        wl = new ArrayList<>();
        try{
            loadWishList();
        }
        catch(FileNotFoundException ex){
            System.out.println("SESSION WISHLIST FILE NOT FOUND");
        }
    }

    public static SessionWishlist getInstance() { return swl; }

    public ArrayList<RegistrationRequest> getWishlist() { return wl; }

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
