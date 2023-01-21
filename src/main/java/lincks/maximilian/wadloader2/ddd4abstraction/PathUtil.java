package lincks.maximilian.wadloader2.ddd4abstraction;

import lombok.RequiredArgsConstructor;

import java.nio.file.Path;

@RequiredArgsConstructor
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

    public static String getFileName(String path){
        return fileNameWithoutExtension(Path.of(path).getFileName());
    }
}
