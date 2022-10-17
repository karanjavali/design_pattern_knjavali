import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

public class Login {

    public  HashMap<String, String> getFileData(String filePath) throws IOException {
        HashMap<String,String> userInfo = new HashMap<String,String>();
        String fileData = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] lineData = fileData.split("\n");
        for(int i=0;i<lineData.length;i++) {
            String[] credentials = lineData[i].split(":");
            System.out.println("Username :- "+credentials[0]+", Password :- "+credentials[1]);
            userInfo.put(credentials[0].trim(),credentials[1].trim());
        }
        return userInfo;
    }

    public boolean login() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("What are you logging in as ? ");
        System.out.println("1. Buyer");
        System.out.println("2. Seller");
        int input = sc.nextInt();
        String fileName = "";
        switch (input) {
            case 1:
                System.out.println("You have chosen Buyer login. Please complete login process.");
                fileName = "BuyerInfo.txt";
                // buyer login
                break;
            case 2:
                System.out.println("You have chosen Seller login. Please complete login process.");
                fileName = "SellerInfo.txt";
                break;
            default:
                System.out.println("Invalid input.");
        }

        String basePath = new File("").getAbsolutePath();
        String path = new File("src/"+fileName).getAbsolutePath();
        System.out.println("Enter username");
        String username = sc.next();
        System.out.println("Enter password");
        String password = sc.next();
        // if login successful
        return checkCredentials(username,password,path);
    }

    public boolean checkCredentials(String username, String password, String path) throws IOException {
//        HashMap<String,String> info = new HashMap<String, String>();
        HashMap<String,String> info = getFileData(path);
        if(!info.containsKey(username)) {
            System.out.println("Invalid username");
            return false;
        }

        else if(!info.get(username).equals(password.trim())) {
            System.out.println("Actual value :- "+info.get(username));
            System.out.println("entered :- "+password);
            System.out.println("Invalid password");
            return false;
        }

        else{
            System.out.println("Login successful!");
            return true;
        }

    }

}
