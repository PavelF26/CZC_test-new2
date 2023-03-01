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



public class BusketPrice {
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
        String baseURL = "https://www.czc.cz/";
        AceptCookies cookie;
        MethodBP MethodBP;
        MethodBP ClickNext;
        MethodBP ClickToBeforeBasket;
        MethodBP ClickToShippingAndPayment;

        private String buttonBasket = "Do košíku";


        @Test
        @Description("pruchod kosikem s: Home Credit Disabled test")
        public void basketBranchStorePaymentWithHomeCreditDisabledTest() {

            //otevre PC
            driver.get(baseURL + "notebooky/produkty");
            //potvrdi cookies
            this.cookie.confirmCookieModal();

              this.MethodBP.setClick();
              this.ClickNext.ClickNext();
              this.ClickToBeforeBasket.ClickToBeforeBasket();
              this.ClickToShippingAndPayment.ClickToShippingAndPayment();
















        }
    }
}








