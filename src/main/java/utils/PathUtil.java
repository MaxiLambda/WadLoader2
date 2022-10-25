package utils;

import java.nio.file.Path;

public class PathUtil {
    public static String fileNameWithoutExtension(Path path){
        String fileName = path.getFileName().toString();
        return fileName.substring(0,fileName.lastIndexOf('.'));
    }
}
