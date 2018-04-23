package xyz.vegaone.ready.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "file", type = "FILE")
public class FileEsEntity {

    @Id
    private String id;

    private String nameOfTheFile;

    private Integer pageNumber;

    private String pageContent;

    private String pathOfTheFile;

    public String getNameOfTheFile() {
        return nameOfTheFile;
    }

    public void setNameOfTheFile(String nameOfTheFile) {
        this.nameOfTheFile = nameOfTheFile;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageContent() {
        return pageContent;
    }

    public void setPageContent(String pageContent) {
        this.pageContent = pageContent;
    }

    public String getPathOfTheFile() {
        return pathOfTheFile;
    }

    public void setPathOfTheFile(String pathOfTheFile) {
        this.pathOfTheFile = pathOfTheFile;
    }
}
