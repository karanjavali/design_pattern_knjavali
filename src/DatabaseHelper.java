import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseHelper {
    private static DatabaseHelper single_instance = null;
    private DatabaseHelper() {}

    public static DatabaseHelper getInstance()
    {
        if (single_instance == null)
            single_instance = new DatabaseHelper();

        return single_instance;
    }
    public String getPath(String fileName) {
        String basePath = new File("").getAbsolutePath();
        String path = new File("src/"+fileName).getAbsolutePath();
        return path;
    }

    public HashMap<String, String> getFileData(String fileName) throws IOException {
        String path = getPath(fileName);
        HashMap<String,String> info = new HashMap<String,String>();
        String fileData = new String(Files.readAllBytes(Paths.get(path)));
        String[] lineData = fileData.split("\n");
        for(int i=0;i<lineData.length;i++) {
                String[] keyValuePair = lineData[i].split(":");
                info.put(keyValuePair[0].trim(),keyValuePair[1].trim());
            }

        return info;
    }

    public ArrayList<String[]> getAllFileData(String fileName) throws IOException {
        String path = getPath(fileName);
        ArrayList<String[]> allInfo = new ArrayList<String[]>();
        String fileData = new String(Files.readAllBytes(Paths.get(path)));
        String[] lineData = fileData.split("\n");
        for(int i=0;i<lineData.length;i++){

                String[] keyValuePair = lineData[i].split(":");
                allInfo.add(keyValuePair);

            }
        return allInfo;
    }


    public ArrayList<String> getValues(String key, String fileName) throws IOException {
        String path = getPath(fileName);
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

    public void addContentToFile(String content, String fileName) throws IOException {
        String path = getPath(fileName);
        Files.write(Paths.get(Paths.get(path).toUri()), content.getBytes(), StandardOpenOption.APPEND);
    }

    public void removeLine(String lineContent,String fileName) throws IOException
    {
        String path = getPath(fileName);
        Path path1 = Paths.get(path);
        String fileData = new String(Files.readAllBytes(path1));
        String[] lineData = fileData.split("\n");
        List<String> list = new ArrayList<String>(Arrays.asList(lineData));
        list.remove(lineContent);
        lineData = list.toArray(new String[0]);
        String content = "";
        for(String line:lineData) {
                content += line + "\n";
        }
        content.trim();
        Files.write(Paths.get(path1.toUri()), content.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
    }
}
