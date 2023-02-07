package czc.serenity.pageobject.Basket;

import lombok.SneakyThrows;
import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class wait extends UIInteractions {

    @FindBy(css ="[id=\"availability\"] [class=\"filter-checkbox \"]  svg")
    List<WebElement> click;


    @Step
    @SneakyThrows
    public void setClick() {
        if (!click.isEmpty() && click.get(0).isDisplayed()) {
            Thread.sleep(1000);
            click.get(0).click();
            System.out.println("povedlo");
        } else {
            System.out.println("eee");


        }


    }
}

