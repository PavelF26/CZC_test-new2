package czc.serenity.pageobject.Basket;

import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;

public class Basket2thStep extends UIInteractions {

    // Kontrola Haku na 2 kroku NP
    @FindBy(css = ".op-sum-row-group")
    List<WebElement> checkHooks2Page;
    // Kontrola vybrane dopravy na 2. kroku NP
    @FindBy(css = "#del-pay-frm__delivery-items > button")
    List<WebElement> checkTransporton2Page;
    // Kontrola vybrane platby na 2. kroku NP
    @FindBy(css = "#op-sum-del-pay > div > div.op-sum-row.last > div:nth-child(2) > div.op-sum-flex")
    List<WebElement> checkPaymenton2Page;


    @Step("Kontrola haku")
    public boolean checkProductHooks2Step(WebDriver driver) {
        if (!checkHooks2Page.isEmpty()) {
            System.out.println("haky se zobrazuji na 1. kroku NP");
            return true;
        } else {
            System.out.println("haky se nezobrazuji na 1. kroku NP");
            return false;
        }
    }

    /**
     * Metoda zvoli pozadovanou dopravu
     */

    @Step("Vybere se doprava - Nase prodejny")
    public void transportNaseProdejny() {
        System.out.println("Vybere se doprava - Nase prodejny - PobockyCZC");
        this.$("#del-pay-frm__delivery-items > label:nth-child(2)", new Object[0]).click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.$(".pup-points__item--selected button.btn-primary",new Object[0]).click();
    }


    /**
     * Metoda kontroluje jestli se doprava a platba vybrala
     */
    // kontrola vybrane dopravy
    public boolean checkSelectedTransport() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (!checkTransporton2Page.isEmpty()) {
            System.out.println("Doprava byla zvolena");
            return true;
        } else {
            System.out.println("Doprava nebyla vybrana");
            return false;
        }
    }
    // kontrola vybrane platby
    public boolean checkSelectedPayment() {
        if (!checkPaymenton2Page.isEmpty()) {
            System.out.println("Platba byla zvolena");
            return true;
        } else {
            System.out.println("Platba nebyla vybrana");
            return false;
        }
    }


    /**
     *  Metoda zvoli pozadovanou platbu
     */
    @Step("Vybere se platba - Nase prodejny")
    public void paymentNaseProdejny (WebDriver driver) {
        System.out.println("Vybere se platba - Pri vyzvednuti");
        this.$("#del-pay-frm__payment-items > label:nth-child(3)", new Object[0]).click();
    }


    /**
     * Metoda klikne na "pokradcovat v objednavce" a skontroluje jestli se nacetla 3ti stranka NP
     */
    @Step("Prechod na druhy krok NP + kontrola nacteni")
    public void continueToThirdStep(WebDriver driver) {
        System.out.println("Dojde k prechodu na 2. krok NP");
        this.$("#del-pay-frm__submit", new Object[0]).click();
    }
    public boolean check3PageIsLoaded() {
        String currentUrl = getDriver().getCurrentUrl();
        return currentUrl.contains("dodaci-udaje");
    }

}
