package czc.serenity.pageobject;

import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class UserPage extends UIInteractions {

    @FindBy(css = ".popup-header")
    List<WebElement> checkCanceledOrder;
    //tlacitko zrusit objednavku - u posledni objednavky
    @FindBy(css= "div.op-item:nth-child(1) > div:nth-child(1) > div:nth-child(2) > a")
    private List<WebElement> cancelLastOrder;

    @Step("Otevreni menu prihlaseneho uzivatele")
    public void clickOnLoggedUser() {
        System.out.println("Dojde k prechodu do casti 'Objednavky'");
        this.$("#logged-user", new Object[0]).click();
        withTimeoutOf(Duration.ofSeconds(5)).find(By.cssSelector(".header-user-info-blue > div:nth-child(2) > div:nth-child(2) > a:nth-child(3)")).click();
    }

    // Kontrola jestli se otevrelo okno s uzivatelskymi objednavkami
    public boolean userOrders() {
        String currentUrl = getDriver().getCurrentUrl();
        return currentUrl.contains("objednavky");
    }

    // Odklikne se tlacitko "Zrusit Objednavku"
    public boolean cancelLatestOrder() {
        if (!cancelLastOrder.isEmpty()) {
            cancelLastOrder.get(0).click();
            return true;
        }else{
            System.out.println("Nepodarilo se najit tlacitko pro zruseni posledni objednavky");
            return false;
        }
    }

    // kontrola zrusene objednavky
    public boolean checkCancelOrder() {
        if (!checkCanceledOrder.isEmpty()) {
            getDriver().findElement(By.cssSelector("button.btn:nth-child(1)")).click();
            return true;
        } else {
            String checkCancelationText = getDriver().findElement(By.cssSelector(".popup-header")).getText();
            System.out.println("Nezobrazuje se: " + checkCancelationText);
            return false;
        }
    }

}
