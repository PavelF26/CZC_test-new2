package czc.serenity.pageobject.Basket;

import lombok.SneakyThrows;
import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class wait extends UIInteractions { // extends UIInteractions bere to jako kniznici

    @FindBy(css = "[class=\"filter-checkbox active\"]")
    private List<WebElement> check;

    @FindBy(css ="[id=\"availability\"] [class=\"filter-checkbox \"]  svg + span")
    List<WebElement> click; //definice ye je to web element a je to css


    @FindBy(css ="[class=\"new-tile\"]:nth-child(1) [class=\"btn btn-buy\"]")
    private List<WebElement> getText;


    @Step("fungovalo overeni ze je kaskrtnuty chechbox")
    @SneakyThrows
    public void setClick() {
        if (!click.isEmpty() && click.get(0).isDisplayed()) {
            Thread.sleep(1000);
            System.out.println("ted se klidne na: " + click.get(0).getText());
            click.get(0).click(); //vzbere hned prvni to je 0
            System.out.println("povedlo");
        } else {
            System.out.println("eee");


        }
    }

    public boolean checkBox() {
        if (!check.isEmpty()) {
            System.out.println("dobry");
            return true;
        } else {
            return false;
        }

    }


    public String checkButton() {
        return getText.get(0).getText();

    }




}



