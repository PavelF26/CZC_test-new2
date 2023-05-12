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
    @FindBy(css = "[class=\"popup-content\"] H1")
    private List<WebElement> header;


    //dojde k vyberu doprav Randomu
    @FindBy(css = "[class=\"del-pay-frm__items\"] [class=\"frm__radio-icon icon--ico-radiobutton\"]")
    public List<WebElement> chooseRandomDelivery;

    //
    @FindBy(css = "[class=\"pup-points__item\"] button:nth-child(2) ")
    List<WebElement> clickBranch;

    // DPD doprava CR
    @FindBy(css = "[class=popup-content] [value=WE_DO]  + svg")
    List<WebElement> DPDClick;

    // Ceska posta zadani PSC
    @FindBy(css = "[id=frm-post-name]")
    List<WebElement> pushPscInput;


    // Zakliknuti pole vlozeneho PSC
    @FindBy(css = "[class=\"btn btn-secondary submit right\"]")
    List<WebElement> enterPost;


    // Vybrani kuryra po Praze
    @FindBy(css = "[class=\"frm__control frm__radio-control del-pay-frm__item\"] svg")
    List<WebElement> pragueCourierClick;
    private Random generator;


    @Step("Vybere se nahodny dopravce")
    @SneakyThrows
    public boolean chooseRandomDeliver() {



        Random generator = new Random();




        WebElement randomTransport = chooseRandomDelivery.get(generator.nextInt(chooseRandomDelivery.size()));
        randomTransport.click();



        //Osobní odběr
        if (randomTransport.isDisplayed()) {
            logger.info("Naše prodejny");
            if (header.get(0).getText().equals("Osobní odběr")) {
                clickBranch.get(0).click();

            } else if (randomTransport.isDisplayed()) {
                logger.info("Vydejni mista");
            }
            if (header.get(0).getText().equals("Osobní odběr")) {
              //  Thread.sleep(1000);
                clickBranch.get(0).click();
            } else if (randomTransport.isDisplayed()) {
                logger.info("Doprava DPD");
                if (header.get(0).getText().equals("Doručení až domů")) {
                //    Thread.sleep(1000);
                    DPDClick.get(0).click();
                }
            } else if (randomTransport.isDisplayed()) {
                logger.info("Random Ceska posta");
               // Thread.sleep(4000);
            }
            if (randomTransport.isDisplayed()) {
                logger.info("Ceska posta PSC");
                pushPscInput.get(0).sendKeys("61600");
               // Thread.sleep(1000);
                pushPscInput.get(0).sendKeys(Keys.ENTER);
                enterPost.get(0).click();
            } else if (randomTransport.isDisplayed()) {
                logger.info("Kuryr po Praze");
               // Thread.sleep(1000);

                if (header.get(0).getText().equals("Expresní doručení po Praze")) {
                    pragueCourierClick.get(0).click();
                    logger.info("Vybrala se doprava po Praze");

                   /* WebElement selectedCourier = null;
                    Random random = new Random();

                    selectedCourier = pragueCourierClick.get(random.nextInt(pragueCourierClick.size()));
                    selectedCourier.click();*/

                } else {
                    logger.info("!!Nebylo možné najít dopravu!!");
                }

            }

        }

        return true;
    }
}








