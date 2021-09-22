import org.openqa.selenium.By;
import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CurrencyRates_Page {

    By sell_Label = By.xpath("//label[text()='I sell']/parent::div//input");
    By sell_Currency_TextBox = By.xpath("//label[text()='I sell']/parent::div//input");
    By sell_CurrencyValue = By.xpath("(//label[text()='I sell']/parent::div//span[@class='currency-select-value'])[1]");
    By buy_Currency_TextBox = By.xpath("//label[text()='I buy']/parent::div//input");
    By buy_CurrencyValue = By.xpath("(//label[text()='I buy']/parent::div//span[@class='currency-select-value'])[1]");
    String buyCurrency =null;
    String buyCurrencyValue=null;

    public void page_Load()
    {
        
            $(sell_Label).shouldBe(visible, Duration.ofSeconds(45));
        
    }

    public void setISellCurrency(String curren)
    {
        $(sell_Currency_TextBox).clear();
        $(sell_Currency_TextBox).setValue(curren);
    }

    public void setISellCurrencyValue(String value)
    {
        $(sell_CurrencyValue).click();
        $(By.xpath("//button[contains(@class,'currency-select-button') and text()='"+value+"']")).click();
        sleep(5000);
    }

    public void getIBuyValue(int value,String value1, String value2)
    {
        buyCurrency = $(buy_Currency_TextBox).getAttribute("value");
        buyCurrencyValue = $(buy_CurrencyValue).getText();
        System.out.println("Scenario : "+value+"  I Sell Value: "+value1+" "+value2+"  I Buy Value: "+buyCurrency+" "+buyCurrencyValue);
    }
    public void getScreenshot(int i)
    {
        String pgFileName = screenshot("text_"+i);
    }



}
