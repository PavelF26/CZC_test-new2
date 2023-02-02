package czc.serenity.pageobject;

import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;

public class MoveToHomePage extends UIInteractions {

    public MoveToHomePage() {
    }

    @Step("Navigate to the home page")
    public void goToHomePage() {
        this.openUrl("https://test.czc.cz/?forceAbVariant=stepThree");
    }
}
