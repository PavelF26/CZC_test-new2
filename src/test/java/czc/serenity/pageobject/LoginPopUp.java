package czc.serenity.pageobject;

import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class LoginPopUp extends UIInteractions {

    // Kontrola jestli se v kosiku nachazi produkt
    @FindBy(css = "span.count:nth-child(3)")
    List<WebElement> checkBasket;


    // Otevreni prihlasovaci stranky zo stranky HP
    @Step("Otevreni Login_PopUp okna")
    public void loginPopUp() {
        System.out.println("Dojde k otevreni prihlasovaci stranky");
        this.$("#blue-menu-wrapper > ul:nth-child(2) > li:nth-child(2)", new Object[0]).click();
        System.out.println("otevreni popUp okna pro prihlaseni bylo uspesne");
    }
    // Otevreni Prohlasovaci stranky zo stranky Kosiku
    public void loginPopUpFromBasketPage() {
        System.out.println("Dojde k otevreni prihlasovaci stranky");
        this.$("#user-preview > a", new Object[0]).click();
        System.out.println("otevreni popUp okna pro prihlaseni bylo uspesne");
    }


    // Vyplneni Login udaju
    @Step("Vyplneni jmena")
    public void fillUserName(String keyword) {
        System.out.println("Dojde k vyplneni uzivatelskeho uctu");
        this.$("#frm-name", new Object[0]).sendKeys(new CharSequence[]{"Testovič0"});
    }
    @Step("Vyplneni hesla")
    public void fillUserPassword(String keyword) {
        System.out.println("Dojde k vyplneni uzivatelskeho uctu");
        this.$("#frm-password", new Object[0]).sendKeys(new CharSequence[]{"Manjaro22"});
    }
    @Step("Prihlaseni uzivatele")
    public void loginAccpet() {
        System.out.println("Dojde k prihlaseni uzivatele");
        this.$("#login-form > div.frm__row.frm__row--with-buttons.row--h-end > button", new Object[0]).click();

    // Kontrola prihlaseni uzivatele z HP stranky
    }
    public boolean checkLoginUser() {
        this.$("#logged-user", new Object[0]).isDisabled();
        System.out.println("Prihlaseni uzivatele bylo uspesne");
        return true;
    }
    // Kontrola prihlaseni uzovatele z Basket stranky
    public boolean checkLoginUserFromBasketPage() {
        this.$("#hui-logged-user > span.avatar", new Object[0]).isDisplayed();
        System.out.println("Prihlaseni uzivatele bylo uspesne");
        return true;
    }


    @Step("Vyprazdneni koisku")
    public void eraseBasket(WebDriver driver) {

        if (!checkBasket.isEmpty()) {
            Actions builder = new Actions(driver);
            builder.moveToElement(element("#basket-preview > a")).perform();
            builder.moveToElement(element(".js-clean-basket")).click().perform();
            System.out.println("Produkt z kosiku byl odstranen, pokracuji v testu");
        }else{
            System.out.println("V kosiku se nenachazi zadny produkt, pokracuji v testu");
        }
    }
    // Prechod do kosiku
    public void goToBasket(){
        this.$("#basket-preview > a", new Object[0]).click();
    }
    public boolean checkGoToBasket() {
        String currentUrl = getDriver().getCurrentUrl();
        return currentUrl.contains("kosik");
    }




}

