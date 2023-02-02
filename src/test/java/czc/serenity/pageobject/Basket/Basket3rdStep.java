package czc.serenity.pageobject.Basket;

import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Basket3rdStep extends UIInteractions {

    @FindBy(css = "#del-info-frm__contact-detail")
    List<WebElement> checkFAdata;
    @FindBy(css = "#frm-additionalInformationRequest")
    List<WebElement> clickOnMarkOrderAsOxytest;
    @FindBy(css = "div.frm__row:nth-child(6) > label:nth-child(1)")
    List<WebElement> clickonCheckboxMarketing;
    @FindBy(css = "div.frm__row:nth-child(7) > label:nth-child(1)")
    List<WebElement> clickOnCheckboxMarketingHeureka;

    //---------------------------------------------------------------------//

    // Provede se kontrola FA udaju
    public boolean checkFAdata() {
        if (!checkFAdata.isEmpty()) {
            System.out.println("Fakturacni udaje se zobrazuji");
            return true;
        } else {
            System.out.println("Nejsou zobrazene fakturacni udaje");
            return false;
        }
    }

    // Zaklikne se checkbox Pridat poznamku a zapise se oznaceni objednavky "oxytest"
    public boolean fillMarkOrderAsOxytest(WebDriver driver) {
        if (!clickOnMarkOrderAsOxytest.isEmpty()) {
            System.out.println("Dojde k zakliknuti checkboxu 'Přidat poznámku nebo označení objednávky'");
            getDriver().findElement(By.cssSelector("#content > form > div.op-grid__content.op-grid__two-columns > div.del-info-frm__section > div:nth-child(1) > label")).click();
            driver.findElement(By.cssSelector("#frm-additionalInformation\\.code")).sendKeys("oxytest");
            System.out.println("u oznaceni objednavky je nastaven 'oxytest'");
            return true;
        } else {
            return false;
        }
    }

    // Zaklikne se checkbox marketing CZC
    public boolean clickOnCheckboxMarketingCZC() {
        if (!clickonCheckboxMarketing.isEmpty()) {
            System.out.println("Dojde k zakliknuti checkboxu: 'Nesouhlasím se zasíláním marketingových materiálů od společnosti CZC.cz s.r.o. na uvedený e-mail.'");
            getDriver().findElement(By.cssSelector("div.frm__row:nth-child(6) > label:nth-child(1)")).click();
            return true;
        } else {
            System.out.println("Nebylo mozne zakliknout checkbox: marketing od CZC");
            return false;
        }
    }

    // Zaklikne se checkbox marketing Heureka
    public boolean clickOnCheckboxMarketingHeureka() {
        if (!clickOnCheckboxMarketingHeureka.isEmpty()) {
            System.out.println("Dojde k zakliknuti checkboxu: Nesouhlasím se zasláním dotazníku spokojenosti v rámci programu Heureka - Ověřeno zákazníky.");
            getDriver().findElement(By.cssSelector("div.frm__row:nth-child(7) > label:nth-child(1)")).click();
            return true;
        } else {
            System.out.println("Nebylo mozne zakliknout checkbox: marketing od Heureka");
            return false;
        }
    }

    @Step("Dokonceni objednavky")
    public void continueToThirdStep() {
        System.out.println("Dojde k dokonceni objednavky");
        this.$("#content > form > div.op-grid__buttons > button", new Object[0]).click();
    }
    public boolean checkOrderFinish() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String currentUrl = getDriver().getCurrentUrl();
        return currentUrl.contains("odeslano");
    }

}
