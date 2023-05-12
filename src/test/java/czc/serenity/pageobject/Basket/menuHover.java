package czc.serenity.pageobject.Basket;

/*

public class menuHover {

    @FindBy(xpath = "//div[@class='main-menu active']//div[@class='main-menu__title' and not(@class='unselectable') and contains(text(),'AKCE %') and not(contains(text(),'CZC.Klub'))]")
    static
    List<WebElement> randomUpMenu;

 /*   @SneakyThrows
        @Step
        public static String selectRandomMenu() {
            Thread.sleep(2000);
            Random randomRedMenu = new Random();
            WebElement randomMenu = randomUpMenu.get(randomRedMenu.nextInt(randomUpMenu.size()));
         //   builder.moveToElement(RandomMenu1Geek).build().perform();
            logger.info("");
        }






    }
    
    


/* if (!randomMenu.isEmpty()) {
        System.out.println("Výběr náhodné platby");
        Random generator = new Random();
        WebElement randomProduct = randomMenu.get(generator.nextInt(randomMenu.size()));
        String menuText = randomProduct.getText();
        randomProduct.hover();
        return menuText;
    } else {
        System.out.println("Nenalezeny platby pro výběr");
      return null;
    }*/