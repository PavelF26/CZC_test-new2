package czc.serenity.pageobject.Basket;

import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;

public class PageTransitions extends UIInteractions {

   /* @Step("Navigace na HP")
    public void goToHomePage() {
        this.openUrl("https://www.czc.cz/");
    }*/

    @Step("Navigate to the homepage")
    public void goToHomePage() {
        this.open("https://www.czc.cz/");
    }


}
