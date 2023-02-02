package czc.serenity.pageobject.Basket;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasketGetPriceUtils {
    private BasketGetPriceUtils() {}

    /**
     * Metoda vytahne cenu produktu na jeho detailu
     */

    // Vytahne cenu produktu s DPH z detailu produktu
    public static String getPriceFromProductPage(WebDriver driver) {
        String productPrice = driver.findElement(By.cssSelector("#product-price-and-delivery-section > div.total-price > div > span > span.price-vatin")).getText();
        return productPrice;
    }

    /**
     * Metoda vytahne cenu na 1. Kroku kosiku
     */
    // Vytahne cenu produktu s DPH na 1.kroku NP
    public static String getPriceFromFirstPage(WebDriver driver) {
        String priceWat = driver.findElement(By.cssSelector("div.td:nth-child(6) > span:nth-child(2) > span:nth-child(1)")).getText();
        return priceWat;
    }

    // Vytahne celkovou cenu kosiku s DPH na 1.kroku NP
    public static String getPriceofTheBasketVatin(WebDriver driver) {
        String priceOverall = driver.findElement(By.cssSelector("#basket-content-summary-container > p.total-price > strong.price-vatin > span")).getText();
        return priceOverall;
    }

    // Vytahne text z 1.kroku NP bez DPH
    public static String getPriceFromFirstPageVatex(WebDriver driver) {
        String priceOverallWithoutWat = driver.findElement(By.cssSelector("span.price-vatin:nth-child(1) > span:nth-child(1)")).getText();
        return priceOverallWithoutWat;
    }

    /**
     * Metoda vytahne cenu na 2. Kroku kosiku
     */

    // Vytahne cenu produktu s DPH na 2.kroku NP
    public static String getPriceFromSecondPage(WebDriver driver) {
        String priceWat2Page = driver.findElement(By.cssSelector(".total-price > span:nth-child(2) > span:nth-child(1)")).getText();
        return priceWat2Page;
    }
    // Vytahne cenu produktu s DPH na 2.kroku NP
    public static String getOverallPriceFromSecondPage(WebDriver driver) {
        String overallPriceWat2Page = driver.findElement(By.cssSelector("span.price-vatin:nth-child(3) > span:nth-child(1)")).getText();
        return overallPriceWat2Page;
    }

}
