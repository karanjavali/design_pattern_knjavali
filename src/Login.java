import java.io.*;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Login {

    Scanner sc = new Scanner(System.in);
    DatabaseHelper db = new DatabaseHelper();

    public String login(String userType) throws IOException {
        Scanner sc = new Scanner(System.in);


        String fileName = userType+"Info.txt";
        System.out.println("Enter username");
        String username = sc.next();
        System.out.println("Enter password");
        String password = sc.next();
        // if login successful
        return checkCredentials(username,password,fileName);
    }
    public String[] userLogin() throws IOException {

        System.out.println("What are you logging in as ? ");
        System.out.println("1. Buyer");
        System.out.println("2. Seller");
        int loginChoice = sc.nextInt();
        String user[] = new String[2];
        switch (loginChoice) {
            case 1:
                System.out.println("You have chosen Buyer login. Please complete login process.");
                user[0] = "Buyer";
                // buyer login
                break;
            case 2:
                System.out.println("You have chosen Seller login. Please complete login process.");
                user[0] = "Seller";
                break;
            default:
                System.out.println("Invalid input.");
        }

        String loginOutput = login(user[0]);
        if(loginOutput == "Invalid_User") {
            user[1] = "Invalid_User";
            return user;
        }
        else {
            user[1] = loginOutput;
            return user;
        }
    }
    public String checkCredentials(String username, String password, String fileName) throws IOException {
//        HashMap<String,String> info = new HashMap<String, String>();
        HashMap<String,String> info = db.getFileData(fileName);
        if(!info.containsKey(username)) {
            System.out.println("Invalid username. Login failed.");
            return "Invalid_User";
        }

        else if(!info.get(username).equals(password.trim())) {
            System.out.println("Invalid password. Login failed.");
            return "Invalid_User";
        }

        else{
            System.out.println("Login successful!");
            return username;
        }

    }

}
