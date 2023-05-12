package czc.serenity.pageobject.Basket;


import czc.serenity.pageobject.AceptCookies;
import jdk.jfr.Description;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;


public class BusketPrice {
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @ExtendWith({SerenityJUnit5Extension.class})
    @Execution(ExecutionMode.SAME_THREAD)
    @Nested
    @DisplayName("Category Content Test")

    public class BasketTransportAndPaymentTest {
        @Order(1)
        @Managed(driver = "firefox")
        WebDriver driver;

        String baseURL = "https://www.czc.cz/";
        AceptCookies cookie;
        PageTransitions pageTransitions;
        MethodBP MethodBP;
        MethodBP ClickNext;
        MethodBP ClickToBeforeBasket;
        MethodBP ClickToShippingAndPayment;
        BasketTwo chooseRandomDelivery;
        backCheck backCheck;
        MethodBP morePrizes;

        basketTwoPrice chooseRandomPrice;


        private String buttonBasket = "Do košíku";
        public Object BasketTwo;



        @Test
        @Description("pruchod kosikem s: Home Credit Disabled test")
        public void basketBranchStorePaymentWithHomeCreditDisabledTest() throws InterruptedException {

            //otevre PC
            driver.get(baseURL);
            //potvrdi cookies
            this.cookie.confirmCookieModal();
           // menuHover.selectRandomMenu();








            // MethodBP.setClick();
            // ClickNext.ClickNext();
            // ClickToBeforeBasket.ClickToBeforeBasket();
            // ClickToShippingAndPayment.ClickToShippingAndPayment();

            // pouziti random doprava
            // chooseRandomDelivery.chooseRandomDeliver();

            // nabídne více cen
            // MethodBP.morePrizes();
            // random plateb
            // chooseRandomPrice.chooseRandomPrice();

            // ověření vybrané dopravy a platby
            // Assertions.assertTrue(backCheck.checkTransportOne(), "Nepovedlo se ověřit vybranou dopravu");
            // Assertions.assertTrue(backCheck.checkTransportPayment(),"Nepovedlo se ověřit vybranou platbu");










        }
    }
}








