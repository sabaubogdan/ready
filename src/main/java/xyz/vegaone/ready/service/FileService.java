package xyz.vegaone.ready.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.vegaone.ready.domain.FileEsEntity;
import xyz.vegaone.ready.dto.FileDto;
import xyz.vegaone.ready.repo.FileEsRepo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static xyz.vegaone.ready.service.ReadyConstants.DOC_FILE_FORMAT;
import static xyz.vegaone.ready.service.ReadyConstants.PDF_FILE_FORMAT;
import static xyz.vegaone.ready.service.ReadyConstants.TXT_FILE_FORMAT;

@Service
@Slf4j
public class FileService {

    private FileEsRepo fileEsRepo;

    @Autowired
    public FileService(FileEsRepo fileEsRepo) {
        this.fileEsRepo = fileEsRepo;
    }

    public Integer discoverAndIndexFiles(String discoveryPath){

        Map<String, List<String>> discoveredFilesMap = discoverFiles(discoveryPath);
        List<String> pdfList = discoveredFilesMap.get(PDF_FILE_FORMAT);

        pdfList.forEach(pdf -> {
            List<FileEsEntity> fileEsEntityList = new ArrayList<>();
            try {
                fileEsEntityList = contentReaderPdf(pdf);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileEsRepo.saveAll(fileEsEntityList);
        });

        return pdfList.size();
    }

    private Map<String, List<String>> discoverFiles(String directoryPath) {
        List<String> txtList = new ArrayList();
        List<String> docList = new ArrayList();
        List<String> pdfList = new ArrayList();

        try (Stream<Path> paths = Files.walk(Paths.get(directoryPath), Integer.MAX_VALUE)) {
            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    if (filePath.toString().endsWith(TXT_FILE_FORMAT)) {
                        txtList.add(filePath.toString());
                    } else if (filePath.toString().endsWith(DOC_FILE_FORMAT)) {
                        docList.add(filePath.toString());
                    } else if (filePath.toString().endsWith(PDF_FILE_FORMAT)) {
                        pdfList.add(filePath.toString());
                    }
                }
            });
        } catch (IOException e) {
//            log.error("Error while discovering files", e);
        }

        Map<String, List<String>> filePathsFound = new HashMap<>();
        filePathsFound.put(TXT_FILE_FORMAT, txtList);
        filePathsFound.put(DOC_FILE_FORMAT, docList);
        filePathsFound.put(PDF_FILE_FORMAT, pdfList);

        return filePathsFound;

    }

    private List<FileEsEntity> contentReaderPdf(String path) throws IOException {

        File file = new File(path);

        PDDocument document = PDDocument.load(file);
        PDFTextStripper pdfTextStripper = new PDFTextStripper();

        int page = 1;
        List<FileEsEntity> fileEsEntityList = new ArrayList<>();

        while (page <= document.getNumberOfPages()) {
            FileEsEntity fileEsEntity = new FileEsEntity();
            fileEsEntity.setPathOfTheFile(path);
            fileEsEntity.setNameOfTheFile(file.getName());
            fileEsEntity.setPageNumber(page);

            pdfTextStripper.setStartPage(page);
            pdfTextStripper.setEndPage(page);
            String pageContent = pdfTextStripper.getText(document);
            fileEsEntity.setPageContent(pageContent);
            fileEsEntityList.add(fileEsEntity);
            page++;
        }

        document.close();

        return fileEsEntityList;
    }

    public Map<Integer, String> contentReaderTxt(String path) throws IOException {
        return null;
    }

    public List<FileDto> contentReaderTxtList(List<String> pathList) throws IOException {
        return null;
    }
}
