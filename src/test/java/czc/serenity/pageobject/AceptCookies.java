package czc.serenity.pageobject;

import net.serenitybdd.core.steps.UIInteractions;

public class AceptCookies extends UIInteractions {
    public AceptCookies() {
    }

    public void confirmCookieModal() {
        System.out.println("Dojde k potvzeni cookies modalu.");
        this.$("#ccp-popup > div > div.ccp-controls > button.btn.btn-outline.btn--md.ccp-controls__all-cookies.js-all-cookies", new Object[0]).click();
        System.out.println("Cookies modal potvrzen");
    }
}

