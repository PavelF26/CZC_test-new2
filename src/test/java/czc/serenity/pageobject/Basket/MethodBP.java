package czc.serenity.pageobject.Basket;

import lombok.SneakyThrows;
import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MethodBP extends UIInteractions { // extends UIInteractions bere to jako kniznici


    //Vybere si jeden produkt a klikne na nej.
    @FindBy(css ="[id=\"content\"] [class=\"btn btn-buy\"] ")
    List<WebElement> click;


    // Pujde do kosiku
    @FindBy(css ="[class=\"buy-mode__content\"] [class=\"btn btn-primary\"] ")
    List<WebElement> ClickNext;

    //Klik na tlacitko predkosik
    @FindBy(css ="[class=\"basket-controls op-controls clearfix\"] [class=\"btn btn-primary btn--md\"] ")
    List<WebElement> ClickToBeforeBasket;

    //Klik na tlacitko smerujici na dopravu a platbu v predkosiku
    @FindBy(css ="[class=\"basket-controls op-controls clearfix\"] [class=\"btn btn-primary btn--md\"] ")
    List<WebElement> ClickToShippingAndPayment;

    @FindBy (css = "#del-pay-frm__payment-items > button")
    List<WebElement> morePrize;






    @Step("fungovalo overeni vybrani produktu")
    @SneakyThrows
    public void setClick() {
        if (!click.isEmpty() && click.get(0).isDisplayed()) {
            System.out.println("ted se klidne na: " + click.get(0).getText());
            click.get(0).click(); //vzbere hned prvni to je 0
            System.out.println("Produkt PC nalezen a vybran");
        } else {
            System.out.println("Nenalezen produkt");

        }
    }



    @Step("Dojde ke kliku na tlacitko pokracovat do kosiku")
    public boolean ClickNext() {
        if (!ClickNext.isEmpty()) {
            ClickNext.get(0).click();
            return true;
        } else
            System.out.println("Nenalezeno tlacitko do kosiku");

            return false;

    }

    @Step("Dojde ke kliku na tlacitko pokracovat k vyberu dopravy")
    public boolean ClickToBeforeBasket() {
        if (!ClickToBeforeBasket.isEmpty()) {
            ClickToBeforeBasket.get(0).click();
            return true;
        } else
            System.out.println("Nenalezeno tlacitko do predkosiku");

        return false;

    }

    @Step("Dojde ke kliku na tlacitko pokracovat k vyberu dopravy a platby")
    public boolean ClickToShippingAndPayment() {
        if (!ClickToShippingAndPayment.isEmpty()) {
            ClickToShippingAndPayment.get(0).click();
            return true;
        } else
            System.out.println("Nenalezeno tlacitko do casti kosiku doprava a platba");

        return false;

    }


    @Step("Dojde na rozluknutí více cen")
    @SneakyThrows
    public boolean morePrizes() {
        System.out.println("Jde rozšířit možnosti plateb");
        if  (!morePrize.isEmpty()) {
            System.out.println("clikne se : " + morePrize.get(0).getText());
            morePrize.get(0).click();
      } else
          System.out.println("Nenalezeno tlacitko pro více cen");

      return false;


    }




}


