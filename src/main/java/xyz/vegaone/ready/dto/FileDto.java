package xyz.vegaone.ready.dto;

import java.util.Map;

public class FileDto {

    private String path;

    private String fileName;

    private Map<Integer, String> fileContent;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<Integer, String> getFileContent() {
        return fileContent;
    }

    public void setFileContent(Map<Integer, String> fileContent) {
        this.fileContent = fileContent;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "FileDto{" +
                "path='" + path + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileContent=" + fileContent +
                '}';
    }
}
