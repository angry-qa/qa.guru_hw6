package tests;

import com.codeborne.xlstest.XLS;
import net.lingala.zip4j.exception.ZipException;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static Utils.Files.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class FilesTests {
    String  expectedValue = "Hello, Selenide!",
            docFile = "doc.doc",
            docxFile = "docx.docx",
            xlsFile = "xls.xlsx",
            zipFile = "zip.zip",
            unzipFile = "unzip.txt",
            pdfFile = "pdf.pdf";

    @Test
    void docTest() {
        String doc = getTextDoc(docFile);
        assertThat(doc, containsString(expectedValue));
    }

    @Test
    void docxTest() {
        String docx = getTextDoc(docxFile);
        assertThat(docx, containsString(expectedValue));
    }

    @Test
    void pdfTest() throws IOException {
        String pdf = getTextPdf(pdfFile);
        assertThat(pdf, containsString(expectedValue));
    }

    @Test
    void xlsTest() {
        XLS xls = getXls(xlsFile);
        assertThat(xls, XLS.containsText(expectedValue));
    }

    @Test
    void zipTest() throws ZipException, IOException {
        unzip(zipFile);
        String actualData = getTextTxt(unzipFile);
        assertThat(actualData, containsString(expectedValue));
        deleteFile(unzipFile);
    }

}
