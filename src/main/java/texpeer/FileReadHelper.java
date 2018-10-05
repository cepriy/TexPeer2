package texpeer;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReadHelper {
    public String convertTextFileToString(String fileName) {
        try {
            File file = Paths.get(ClassLoader.getSystemResource(fileName).toURI()).toFile();
            FileInputStream fis = new FileInputStream(file);
            if (FilenameUtils.getExtension(fileName).equals("docx")) {
                XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
                XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
                return extractor.getText();
            }
            if (FilenameUtils.getExtension(fileName).equals("doc")) {
                StringBuilder content = new StringBuilder();
                HWPFDocument document = new HWPFDocument(fis);
                WordExtractor we = new WordExtractor(document);
                String[] paragraphs = we.getParagraphText();
                for (String para : paragraphs) {
                    content.append(para);
                }
                fis.close();
                return content.toString();
            }
            throw new RuntimeException("Incorrect file extension");
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
//        try (Stream<String> stream
//                     = Files.lines(Paths.get(ClassLoader.getSystemResource(fileName).toURI()))) {
//
//            return stream.collect(Collectors.joining(" "));
//        } catch (IOException | URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
    }
}
