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
            userInfo.put(credentials[0].trim(),credentials[1].trim());
        }
        return userInfo;
    }

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
        HashMap<String,String> info = getFileData(path);
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
