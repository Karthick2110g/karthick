
import com.codeborne.selenide.Configuration;
import com.codoid.products.exception.FilloException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import java.io.FileInputStream;
import java.io.IOException;
import static com.codeborne.selenide.Selenide.*;

public class CurrencyRate_Test {

    @Test
    public void add() throws FilloException, IOException {

        //System.out.println(System.getProperty("user.dir")+"\\build\\downloads\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\build\\downloads\\chromedriver.exe");
        Configuration.browser = "chrome";
        Configuration.timeout = 5000;

        //Open the URL
        open("https://luminor.ee/currency-rates");


        //Page Objects
        CurrencyRates_Page page_CurrencyRate = new CurrencyRates_Page();
        page_CurrencyRate.page_Load();

        //Path of the excel file - Need to give all the test data combination
        FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\CurrencyRate_Data.xlsx");

        //Creating a workbook
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int lastRow = sheet.getLastRowNum();

        for(int i=1;i<=lastRow;i++)
        {
            if(i!=1) {
                //Reloading the page again to give the next set of inputs

                open("https://luminor.ee/currency-rates");
                page_CurrencyRate.page_Load();
            }
            Row row = sheet.getRow(i);
            String iSellCurrency =null;
            String iSellCurrency_Value =null;

            iSellCurrency = sheet.getRow(i).getCell(0).getStringCellValue();

            iSellCurrency_Value = sheet.getRow(i).getCell(1).getStringCellValue();

            page_CurrencyRate.setISellCurrency(iSellCurrency);
            page_CurrencyRate.setISellCurrencyValue(iSellCurrency_Value);
            page_CurrencyRate.getIBuyValue(i,iSellCurrency,iSellCurrency_Value);
            page_CurrencyRate.getScreenshot(i);

        }



    }
}