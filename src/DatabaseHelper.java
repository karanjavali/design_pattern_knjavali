import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper {

    public HashMap<String, String> getFileData(String filePath) throws IOException {
        HashMap<String,String> info = new HashMap<String,String>();
        String fileData = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] lineData = fileData.split("\n");
        for(int i=0;i<lineData.length;i++) {
            String[] keyValuePair = lineData[i].split(":");
            info.put(keyValuePair[0].trim(),keyValuePair[1].trim());
        }
        return info;
    }

    public ArrayList<String> getValues(String key, String file) throws IOException {
        String basePath = new File("").getAbsolutePath();
        String path = new File("src/"+file).getAbsolutePath();
        HashMap<String,String> info = new HashMap<String,String>();
        ArrayList<String> values = new ArrayList<String>();
        String fileData = new String(Files.readAllBytes(Paths.get(path)));
        String[] lineData = fileData.split("\n");
        for(int i=0;i<lineData.length;i++) {
            String[] keyValuePair = lineData[i].split(":");
            if ( keyValuePair[0].trim().equals(key)) {
                values.add(keyValuePair[1].trim());
            }
        }
        return values;
    }
}
