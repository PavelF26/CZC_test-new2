package czc.serenity.pageobject.Basket;

import czc.serenity.pageobject.AceptCookies;
import czc.serenity.pageobject.MoveToHomePage;
import jdk.jfr.Description;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Test1 {


    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @ExtendWith({SerenityJUnit5Extension.class})
    @Execution(ExecutionMode.SAME_THREAD)
    @Nested
    @DisplayName("BasketTest HomeCredit")
    public class BasketTransportAndPaymentTest {
        @Order(1)
        @Managed(driver = "chrome")
        WebDriver driver;
        MoveToHomePage goToHomePage;
        String baseURL = "https://test.czc.cz/";
        AceptCookies cookie;

        String buttonBasket = "Do košíku";



        @Test
        @Description("pruchod kosikem s: Home Credit Disabled test")
        public void basketBranchStorePaymentWithHomeCreditDisabledTest() {
            // otevreni HP CZC
            this.goToHomePage.goToHomePage();
            //otevre EL
            driver.get(baseURL + "microsoft-elektronicke-licence/produkty");
            //potvrdi cookies
            this.cookie.confirmCookieModal();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }

           //potvrzeni filtru
            driver.findElement(By.cssSelector("[id=\"availability\"] [class=\"filter-checkbox \"]  svg")).click();

            //zachyceni textu a overeni
            String getTextButon = String.valueOf(driver.findElement(By.cssSelector("[class=\"new-tile\"]:nth-child(1) [class=\"btn btn-buy\"]")).getText());
            System.out.println(getTextButon);
            Assertions.assertEquals(buttonBasket, getTextButon, "Text do kosiku nenalezeno");




        }
    }
}