package czc.serenity.pageobject.Basket;

import lombok.SneakyThrows;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static jxl.biff.FormatRecord.logger;


public class backCheck {




    @FindBy(xpath = "/html/body/div[2]/div[2]/div[1]/div/form/div[1]/div[1]/div/label/span/div[1]/div[1]")
    List<WebElement> checkTransport;

    @FindBy(xpath = "/html/body/div[2]/div[2]/div[1]/div/form/div[1]/div[2]/div/label/span/div[1]/div")
    List<WebElement> checkPayment;

    @SneakyThrows
    public boolean checkTransportOne(){
          // Thread.sleep(10000);
           if (!checkTransport.isEmpty()) {
               logger.info("Požadovaná doprava byla zvolena: " + checkTransport.get(0).getText());
               return true;
           } else {
               logger.info("Požadovaná doprava Nebyla zvolena!!▬▬♦");
               return false;
           }



    }

    @SneakyThrows
    public boolean checkTransportPayment(){
      // Thread.sleep(10000);
        if (!checkPayment.isEmpty()) {
            logger.info("Požadovaná platba byla zvolena: " + checkPayment.get(0).getText());
            return true;
        } else {
            logger.info("Požadovaná doprava Nebyla zvolena!!▬▬♦");
            return false;
        }



    }


}
