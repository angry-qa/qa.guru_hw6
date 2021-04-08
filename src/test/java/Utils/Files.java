package Utils;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Files {
    private static final String PATH_TO_FILES = "./src/test/resources/";
    private static final String PASSWORD = "123456";

    public static File getFile(String source) {
        return new File(PATH_TO_FILES + source);
    }

    public static String getTextDoc(String source) {
        String result = "";

        try (FileInputStream fis = new FileInputStream(getFile(source));
             XWPFDocument document = new XWPFDocument(fis);
             XWPFWordExtractor extractor = new XWPFWordExtractor(document)) {

            result = extractor.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getTextPdf(String source) throws IOException {
        return new PDF(getFile(source)).text;
    }

    public static XLS getXls(String source) {
        return new XLS(getFile(source));
    }

    public static String getTextTxt(String source) throws IOException {
        return FileUtils.readFileToString(getFile(source), StandardCharsets.UTF_8);
    }

    public static void unzip(String source) throws ZipException {
        ZipFile zipFile = new ZipFile(getFile(source));
        if (zipFile.isEncrypted()) {
            zipFile.setPassword(PASSWORD);
        }
        zipFile.extractAll(PATH_TO_FILES);
    }

    public static void deleteFile(String source) {
        new File(PATH_TO_FILES + source).delete();
    }
}
