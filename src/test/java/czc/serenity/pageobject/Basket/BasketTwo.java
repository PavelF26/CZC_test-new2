package czc.serenity.pageobject.Basket;
import lombok.SneakyThrows;
import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.Random;

import static jxl.biff.FormatRecord.logger;


public class BasketTwo extends UIInteractions { // extends UIInteractions bere to jako kniznici

    // Osobní odběr – Česká pošta
    @FindBy (css ="[class=\"popup-content\"] H1")
    private List<WebElement> header;

   // @FindBy (css = "[class=\"pup-filters\"] H1")
    //private List<WebElement> personalCollectionH1;


    //dojde k vyberu doprav Randomu
@FindBy(css = "[class=\"del-pay-frm__items\"] [class=\"frm__radio-icon icon--ico-radiobutton\"]")
    public List<WebElement> chooseRandomDelivery;

    //
@FindBy (css = "[class=\"pup-points__item\"] button:nth-child(2) ")
List<WebElement> clickBranch;

    // DPD doprava CR
@FindBy (css = "[class=popup-content] [value=WE_DO]  + svg")
List<WebElement> DPDClick;

    // Ceska posta zadani PSC
@FindBy (css = "[id=frm-post-name]")
List<WebElement> pushPscInput;


    // Zakliknuti pole vlozeneho PSC
@FindBy(css = "[class=\"btn btn-secondary submit right\"]")
List<WebElement> enterPost;


    // Vybrani kuryra po Praze
@FindBy( css = "[class=popup-content] [id=del-pay-frm__delivery-items] svg")
List<WebElement> pragueCourierClick;












    @Step("Vybere se nahodny dopravce")
    @SneakyThrows //pocka Thread.sleep(1000); toto je potřeba tam dávat jinak to nefunguje.
    public void chooseRandomDelivery() {

        Random generator = new Random();
        WebElement randomTransport = chooseRandomDelivery.get(generator.nextInt(chooseRandomDelivery.size()));
        randomTransport.click();

         String chooseRandomDelivery = randomTransport.getText();

         //Osobní odběr
         if (randomTransport.isDisplayed()) {
             logger.info("Naše prodejny");
                    Thread.sleep(4000);
             if (header.get(0).getText().equals("Osobní odběr")){
                  clickBranch.get(0).click();
             }
                    Thread.sleep(4000);
         } else if (randomTransport.isDisplayed()) {
             logger.info("Vydejni mista");
             Thread.sleep(4000);
            } if (header.get(0).getText().equals("Osobní odběr")){
                   clickBranch.get(0).click();
                   Thread.sleep(4000);
         } else if (randomTransport.isDisplayed()){
            logger.info("Doprava DPD");
            Thread.sleep(4000);
             if (header.get(0).getText().equals("Doručení až domů")){
                // Thread.sleep(4000);
                 DPDClick.get(0).click();
             }
         } else if (randomTransport.isDisplayed()){
             logger.info("Random Ceska posta");
             Thread.sleep(4000);
        } if (randomTransport.isDisplayed()){
                logger.info("Ceska posta PSC");
                pushPscInput.get(0).sendKeys("61600");
             Thread.sleep(4000);
                pushPscInput.get(0).sendKeys(Keys.ENTER);
            Thread.sleep(4000);
            enterPost.get(0).click();
        } else if (randomTransport.isDisplayed()){
              logger.info("Kuryr po Praze");
                Thread.sleep(4000);
          if (header.get(0).getText().equals("Expresní doručení po Praze")){
              WebElement selectedCourier = null;
              Random random = new Random();
              Thread.sleep(4000);
              selectedCourier = pragueCourierClick.get(random.nextInt(pragueCourierClick.size()));
              selectedCourier.click();

         }
        }


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }














    }









    }








