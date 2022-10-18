import java.io.*;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Login {

    DatabaseHelper db = new DatabaseHelper();

    public boolean login(String userType) throws IOException {
        Scanner sc = new Scanner(System.in);


        String basePath = new File("").getAbsolutePath();
        String path = new File("src/"+userType+"Info.txt").getAbsolutePath();
        System.out.println("Enter username");
        String username = sc.next();
        System.out.println("Enter password");
        String password = sc.next();
        // if login successful
        return checkCredentials(username,password,path);
    }

    public boolean checkCredentials(String username, String password, String path) throws IOException {
//        HashMap<String,String> info = new HashMap<String, String>();
        HashMap<String,String> info = db.getFileData(path);
        if(!info.containsKey(username)) {
            System.out.println("Invalid username. Login failed.");
            return false;
        }

        else if(!info.get(username).equals(password.trim())) {
            System.out.println("Invalid password. Login failed.");
            return false;
        }

        else{
            System.out.println("Login successful!");
            return true;
        }

    }

}
