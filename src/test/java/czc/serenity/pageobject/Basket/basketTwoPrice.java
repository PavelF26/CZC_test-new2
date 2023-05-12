package czc.serenity.pageobject.Basket;

import lombok.SneakyThrows;
import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;


public class basketTwoPrice extends UIInteractions {



@FindBy(css = "#del-pay-frm__payment-items .del-pay-frm__item:not([class=\"frm__control frm__radio-control del-pay-frm__item del-pay-frm__item--disabled hidable\"]) [class=\"frm__radio-icon icon--ico-radiobutton\"]")
   private List<WebElement> randomPrice;

//@FindBy (xpath = "/html/body/div[4]/div/div[2]/button")
  // private List<WebElement> h1HomeCredit;







    @Step("Výběr platby")
    @SneakyThrows
    public void chooseRandomPrice() {
        //Thread.sleep(5000);

        if (!randomPrice.isEmpty()) {
            System.out.println("Random platba se vybere");
            Random generator = new Random();
            WebElement RandomProduct = randomPrice.get(generator.nextInt(randomPrice.size()));
            RandomProduct.click();
        } else {
            System.out.println("Random platba se nevybere");
        }









    }



}

