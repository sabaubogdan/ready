package xyz.vegaone.ready.dto;

public class FileEsDto {

    private String nameOfTheFile;

    private Long pageNumber;

    private String pageContent;

    private String pathOfTheFile;

    public String getNameOfTheFile() {
        return nameOfTheFile;
    }

    public void setNameOfTheFile(String nameOfTheFile) {
        this.nameOfTheFile = nameOfTheFile;
    }

    public Long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Long pageNumber) {
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
