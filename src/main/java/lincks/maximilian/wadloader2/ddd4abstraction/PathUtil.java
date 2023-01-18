package lincks.maximilian.wadloader2.ddd4abstraction;

import java.nio.file.Path;

public class PathUtil {
    public static String fileNameWithoutExtension(Path path){
        String fileName = path.getFileName().toString();
        //-1 if '.' is not part of the fileName
        int lastIndex = fileName.lastIndexOf('.');
        return lastIndex < 0 ? fileName : fileName.substring(0,lastIndex);
    }

    public static String getExtension(Path path){
        String fileName = path.getFileName().toString();
        //-1 if '.' is not part of the fileName
        int lastIndex = fileName.lastIndexOf('.');
        return lastIndex < 0 ? "" : fileName.substring(lastIndex);
    }
}
