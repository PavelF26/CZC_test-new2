package czc.serenity.pageobject.Basket;

import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class Basket1stStep extends UIInteractions {

    @FindBy(css = "div.td:nth-child(1)")
    List<WebElement> checkHooks;

    @Step("Kontrola nazvu produktu")
    public void checkProductName (WebDriver driver) {
        this.$("div.product-name:nth-child(2) > a:nth-child(1)", new Object[0]).isPresent();
        System.out.println("Nazev produktu se zobrazuje na spravnem miste");
    }

    @Step("Kontrola haku")
    public boolean checkProductHooks (WebDriver driver) {
        if (!checkHooks.isEmpty()) {
            System.out.println("haky se zobrazuji na 1. kroku NP");
            return true;
        } else {
            System.out.println("haky se nezobrazuji na 1. kroku NP");
            return false;
        }
    }

    @Step("Prechod na druhy krok NP + kontrola nacteni")
    public void continueToFirstandHalfStep(WebDriver driver) {
        System.out.println("Dojde k prechodu na 1,5 krok NP");
        this.$(".btn-primary", new Object[0]).click();
    }

    @Step("Prechod na druhy krok NP + kontrola nacteni")
    public void continueToSecondStep(WebDriver driver) {
        System.out.println("Dojde k prechodu na 2. krok NP");
        this.$(".btn-primary", new Object[0]).click();
    }

    public boolean check2PageIsLoaded() {
        String currentUrl = getDriver().getCurrentUrl();
        return currentUrl.contains("dodani-a-platba");
    }
}
