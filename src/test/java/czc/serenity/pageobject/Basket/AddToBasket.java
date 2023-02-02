package czc.serenity.pageobject.Basket;

import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddToBasket extends UIInteractions {

    @Step("Pridani produktu do kosika z detailu produktu")
    public void addProductToBasket(WebDriver driver) {
        System.out.println("Dojde k pridani produktu do kosika.");
        String nameOfProduct = driver.findElement(By.cssSelector(".pd-wrap > h1:nth-child(1)")).getText();
        this.$("#product-price-and-delivery-section > div.pd-cta-buttons > button", new Object[0]).click();
        System.out.println("Produkt:  " + nameOfProduct + ", byl pridan do kosika.");
    }

    @Step("Prechod do kosika")
    public void continueToBasket(WebDriver driver) {
        System.out.println("Provede se klik na prechod do kosika");
        this.$("#buy-mode-product > div.buy-mode-product__item.buttons > a", new Object[0]).click();
        driver.getCurrentUrl().contains("kosik");
        System.out.println("Prechod do kosika byl uspesny");
    }

    @Step("Prechod z kosika na HP")
    public void backToHP() {
        System.out.println("Provede se klik na prechod do kosika");
        this.$("#basket-visibility-container > div > div > a", new Object[0]).click();
    }
}


