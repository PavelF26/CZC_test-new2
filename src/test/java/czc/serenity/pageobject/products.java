package czc.serenity.pageobject;

import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class products extends UIInteractions {

    @FindBy(css = ".pd-gift__content")
    List<WebElement> checkHooks;

    @Step("Otevreni Login_PopUp okna")
    public void productsWithHooks(WebDriver driver) {
        System.out.println("Dojde k prechodu na stranku produktu s haky");
        driver.get("https://test.czc.cz/products/with-hooks/test-cases");
        this.$(".debug-content > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > a:nth-child(1)", new Object[0]).click();
        driver.getCurrentUrl().contains("produkt");
        System.out.println("Produkt s haky byl vybran");
    }

    @Step("Kontrola haku")
    public boolean checkProductHooks (WebDriver driver) {
        if (!checkHooks.isEmpty()) {
            System.out.println("haky se zobrazuji na detailu produktu");
            return true;
        } else {
            System.out.println("haky se nezobrazuji na detailu produktu");
            return false;
        }
    }

    @Step("Kontrola celkove ceny na detailu produktu")
    public String getPriceofProductVatin(WebDriver driver) {
        String ProductPrice = driver.findElement(By.cssSelector(".price > span:nth-child(2)")).getText();
        return ProductPrice;
    }

}
