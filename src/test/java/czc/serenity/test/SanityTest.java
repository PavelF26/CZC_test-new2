package czc.serenity.test;

import czc.serenity.pageobject.*;
import czc.serenity.pageobject.Basket.*;
import lombok.SneakyThrows;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.WebDriver;
import java.net.HttpURLConnection;
import java.net.URL;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
//import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Order;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class Sanity {


    @ExtendWith({SerenityJUnit5Extension.class})
    @Execution(ExecutionMode.SAME_THREAD)
    @Nested
    @Order(1)
    @DisplayName("Sanity: pruchod kosikem")
    public class SanityTestBasket {
        @Managed(driver = "chrome")
        WebDriver driver;
        MoveToHomePage HP;
        AceptCookies cookie;
        LoginPopUp Prihlaseni;
        products Produkty;
        AddToBasket PridamiProduktuDoKosika;
        Basket1stStep PrvniKrokNP;
        Basket2thStep DruhyKrokNP;
        Basket3rdStep TretiKrokNP;
        UserPage UzivatelskaStranka;

        @Test
        @Order(1)
        @BeforeClass
        public void createAndCancelOrderWithHooksTest() {
            // otevreni HP CZC
            this.HP.goToHomePage();
            this.cookie.confirmCookieModal();

            // prihlaseni uzivatele
            this.Prihlaseni.loginPopUp();
            this.Prihlaseni.fillUserName("Testovič0");
            this.Prihlaseni.fillUserPassword("Manjaro22");
            this.Prihlaseni.loginAccpet();
            assertTrue("Nedoslo k prihlaseni uzivatele", Prihlaseni.checkLoginUser());

            // vyprazdneni kosiku
            this.Prihlaseni.eraseBasket(driver);

            // prechod na produkt s haky + kontrola
            this.Produkty.productsWithHooks(driver);
            //this.Produkty.hooksProductControl();
            assertTrue("Haky se nezobrazuji na detailu produktu", Produkty.checkProductHooks(driver));
            String getProductPrice = BasketGetPriceUtils.getPriceFromProductPage(driver);

            // Pridani produktu do kosika
            this.PridamiProduktuDoKosika.addProductToBasket(driver);
            this.PridamiProduktuDoKosika.continueToBasket(driver);

            // 1. krok NP + kontrola cen
            String getPriceFromFirstPage = BasketGetPriceUtils.getPriceFromFirstPage(driver);
            String getPriceofTheBasketVatin = BasketGetPriceUtils.getPriceofTheBasketVatin(driver);
            Assert.assertEquals("Cena produktu v kosiku se nezhoduje s cenou na 1. kroku NP", getProductPrice, getPriceFromFirstPage);
            Assert.assertEquals("Cena produktu bez DPH na 1. kroku NP je chybna", getPriceofTheBasketVatin, getProductPrice);
            this.PrvniKrokNP.checkProductName(driver);
            assertTrue("Haky na 1. kroku NP se nezobrazuji", PrvniKrokNP.checkProductHooks(driver));
            this.PrvniKrokNP.continueToSecondStep(driver);
            assertTrue("Nepovedlo se prejit na 2. krok NP", PrvniKrokNP.check2PageIsLoaded());

            // 2. krok NP
            // 2. krok NP - kontrola cen a haku
            String getPriceFromSecondPage = BasketGetPriceUtils.getPriceFromSecondPage(driver);
            String getOverallPriceFromSecondPage = BasketGetPriceUtils.getOverallPriceFromSecondPage(driver);
            assertTrue("Nebyl nalezen Hak, na 2. krok NP", DruhyKrokNP.checkProductHooks2Step(driver));
            Assert.assertEquals("Cena produktu v kosiku se nezhoduje s cenou na 2. kroku NP", getProductPrice, getPriceFromSecondPage);
            Assert.assertEquals("Cena produktu v kosiku se nezhoduje s cenou na 2. kroku NP", getPriceFromFirstPage, getOverallPriceFromSecondPage);
            // 2. krok NP - Vyber dopravy
            this.DruhyKrokNP.transportNaseProdejny();
            assertTrue("Pozadovana doprava nebyla vybrana!", DruhyKrokNP.checkSelectedTransport());
            this.DruhyKrokNP.paymentNaseProdejny(driver);
            assertTrue("Pozadovana platba nebyla vybrana!", DruhyKrokNP.checkSelectedPayment());
            this.DruhyKrokNP.continueToThirdStep(driver);
            assertTrue("Nepovedlo se prejit na 3. krok NP", DruhyKrokNP.check3PageIsLoaded());

            // 3. krok NP
            assertTrue("Nepovedlo se prejit na 3. krok NP", TretiKrokNP.checkFAdata());
            //this.TretiKrokNP.clickOnMarkOrderAsOxytest();
            assertTrue("Nepovedlo se: zakliknout checkbox a doplnit oznaceni objednavky", TretiKrokNP.fillMarkOrderAsOxytest(driver));
            assertTrue("Nepovedlo se kliknout na checkbox: 'Nesouhlasím se zasíláním marketingových materiálů od společnosti CZC.cz s.r.o. na uvedený e-mail.'", TretiKrokNP.clickOnCheckboxMarketingCZC());
            assertTrue("Nepovedlo se kliknout na checkbox: 'Nesouhlasím se zasláním dotazníku spokojenosti v rámci programu Heureka - Ověřeno zákazníky.'", TretiKrokNP.clickOnCheckboxMarketingHeureka());
            this.TretiKrokNP.continueToThirdStep();
            assertTrue("Neni zobrazena stranka po dokonceni objednavky", TretiKrokNP.checkOrderFinish());

            // Dokonceni objednavky a prechod do uzivatelskeho uctucreateAndCancelOrderWithHooksTest
            this.UzivatelskaStranka.clickOnLoggedUser();
            assertTrue("Nedoslo k prechodu na stranku s objednavkami uzivatele", UzivatelskaStranka.userOrders());
            assertTrue("nepovdelo se stornovat objednavku", UzivatelskaStranka.cancelLatestOrder());
            assertTrue("Nezobrazuje se hlaska o Storne objednavky", UzivatelskaStranka.checkCancelOrder());
        }

        @Test
        @Order(2)
        public void headerLoginBasketTest() {
            // otevreni HP CZC
            this.HP.goToHomePage();
            this.cookie.confirmCookieModal();

            // vyprazdneni kosiku
            this.Prihlaseni.eraseBasket(driver);
            // prechod do kosiku
            this.Prihlaseni.goToBasket();
            assertTrue("https://test.czc.cz/kosik <- Stranka nebyla nactena", Prihlaseni.checkGoToBasket());

            // prihlaseni uzivatele
            this.Prihlaseni.loginPopUpFromBasketPage();
            this.Prihlaseni.fillUserName("Testovič0");
            this.Prihlaseni.fillUserPassword("Manjaro22");
            this.Prihlaseni.loginAccpet();
            assertTrue("Nedoslo k prihlaseni uzivatele", Prihlaseni.checkLoginUserFromBasketPage());
            this.PridamiProduktuDoKosika.backToHP();
            this.Prihlaseni.goToBasket();
        }

        //-------------------------------------- NEXT CLASS -------------------------------------------------//

        @ExtendWith({SerenityJUnit5Extension.class})
        @Execution(ExecutionMode.CONCURRENT)
        @Nested
        @Order(2)
        @DisplayName("Sanity: Testy status kodu")
        public class SanityTestResponseCode {
            @Managed(driver = "remote")
            WebDriver driver;
            AceptCookies cookie;

            @SneakyThrows
            @ParameterizedTest
            @ValueSource(
                    strings = {
                            "https://test.czc.cz/apple/hledat?param=%%test%%",
                            "https://test.czc.cz/vybirejte-z-bonus-klubu/produkty",
                            "https://test.czc.cz/vypis-recenzi",
                            "https://test.czc.cz/apple/vyrobce",
                            "https://test.czc.cz/apple-iphone-4s-64gb-cerny/98195/produkt/cenovka",
                            "https://test.czc.cz/amd-phenom-ii-x4-955-black-edition-hdz955fbgibox-box/64947/produkt/aqumulr3c4ipi9k18jpai1mg28/diskuse",
                            "https://test.czc.cz/apple/zbozi",
                            "https://test.czc.cz/logitech-x-230/29236/produkt/recenze?reviewId=ca99eg07hajr787g4hkh4ivtb6",
                            "https://test.czc.cz/3o9mht5p68jc6a6fd9mhrkl1u1/seznam",
                            "https://test.czc.cz/nikon-d5200-18-105-af-s-dx-vr/119026/produkt/clanky",
                            "https://test.czc.cz/favicon.ico",
                            "https://test.czc.cz/search?q-fulltext=i5",
                            "https://test.czc.cz/reklamace-new/clanek",
                            "https://test.czc.cz/czc-klub",
                            "https://test.czc.cz/clanky?param=%%test",
                            "https://test.czc.cz/msi-gtx-960-gaming-4g-4gb/168790/produkt/vypis-recenzi",
                            "https://test.czc.cz/dotaznik-spokojenosti?code=test-quest",
                            "https://test.czc.cz/ssd/pevne-disky/hledat#q-first=27&q-c-0-f_2025059=r200-499",
                            "https://test.czc.cz/cm9vmu5f08gkoa9op8vcmobrq2/recenze",
                            "https://test.czc.cz/manifest.json",
                            "https://test.czc.cz/robots.txt",
                            "https://test.czc.cz/seznamy",
                            "https://test.czc.cz/konfigurator-pc",
                            "https://test.czc.cz/?utm_source=firmy.cz&utm_medium=cpd&utm_campaign=%%ZDROJ%%-%%TYP%%&utm_content=%%ID%%",
                            "https://test.czc.cz/praha-4-haje/kontakt",
                            "https://test.czc.cz/kontakty",
                            "https://test.czc.cz/notebooky/produkty",
                            "https://test.czc.cz/search?q-fulltext=xdqer",
                            "https://test.czc.cz/asus-xonar-dg_2/80294/produkt/2vn2phlm1aga6b4b3kp9hkrnb3/diskuse",
                            "https://test.czc.cz/evolveo-t3/204617/produkt",
                            "https://test.czc.cz/Philips%20241B7/hledat",
                            "https://test.czc.cz/pro/hledat",
                            "https://test.czc.cz/postovne-zdarma_2/83022/produkt",
                            "https://test.czc.cz/zvyhodnene",
                            "https://test.czc.cz/lian-li/zbozi",
                            "https://test.czc.cz/jslog?level=info&msg=info",
                            "https://test.czc.cz/hdd/pevne-disky/hledat",
                            "https://test.czc.cz/pobocky",
                            "https://test.czc.cz/vypis-recenzi?q-first=40&q-order=RATING",
                            "https://test.czc.cz/praha-4-haje/kontakt/prezentace",
                            "https://test.czc.cz/pouzite-zbozi/stitek",
                            "https://test.czc.cz/recenze/clanky",
                            "https://test.czc.cz/kosik",
                            "https://test.czc.cz/search/old?q-fulltext=intel",
                            "https://test.czc.cz/clanky",
                            "https://test.czc.cz/159751,159749/porovnat-kody",
                            "https://test.czc.cz/komponenty/kategorie",
                            "https://test.czc.cz/registrace/nova",
                            "https://test.czc.cz/czc-cz/vyrobce",
                            "https://test.czc.cz/porovnat?code=179320&code=178263&code=178264",
                            "https://test.czc.cz/login",
                            "https://test.czc.cz/intel-core-i5-2500k/85284/produkt/forum",
                            "https://test.czc.cz/search/old?q-fulltext=intel",
                            "https://test.czc.cz/pohled-do-zakulisi-czc-cz-tady-sestavime-az-70-pocitacu-denne/clanek/forum",
                            "https://test.czc.cz/procesory/produkty?q-first=90&q-order=DATE",
                            "https://test.czc.cz/browserconfig.xml",
                            "https://test.czc.cz/usb/hledat",
                            "https://test.czc.cz/seriovy-kabel-prodluzovaci-2m/20428/produkt",
                            "https://test.czc.cz/mikrotik/mikrotik/zbozi",
                            "https://test.czc.cz/111508-111600/kody"
                    }
            )
            @DisplayName("Checking Status Code 200:")
            void checkStatusCode200(String path) {
                HttpURLConnection cont = (HttpURLConnection) new URL(path).openConnection();
                cont.setRequestMethod("HEAD");
                cont.connect();
                int rs = cont.getResponseCode();
                if (rs == 200) {
                    System.out.println("Http response code: " + rs + " pro: " + path);

                } else {
                    throw new Exception("Status kod pro " + path + " je: " + rs + " !! ma byt 200");
                }
            }
            //-------------------------------------- END of Check Status Code 200 -----------------------------------//

            //-------------------------------------- Check Status Code 404 -----------------------------------------//
            @SneakyThrows
            @ParameterizedTest
            @ValueSource(
                    strings = {
                            "https://test.czc.cz/kabely_2/produkty&nesmzslnyfiltr=hodnota",
                            "https://test.czc.cz/neexistujicipobocka/kontakt/prezentace",
                            "https://test.czc.cz/neexistujici-vyrobce/produktovy-typ/zbozi",
                            "https://test.czc.cz/nesmyslneurl/nesmyslneid/produkt/seznam",
                            "https://test.czc.cz/nesmyslneurl/nesmyslneid/produkt/vypis-recenzi",
                            "https://test.czc.cz/neexistujici-vyrobce/zbozi",
                            "https://test.czc.cz/nesmyslnytyp/clanky",
                            "https://test.czc.cz/nesmyslneid/diskuse",
                            "https://test.czc.cz/search/old&q-fulltext=nejakyretezec",
                            "https://test.czc.cz/nesmyslneid/forum",
                            "https://test.czc.cz/neexistujicistitek/sticker",
                            "https://test.czc.cz/nesmyslneid/seznam",
                            "https://test.czc.cz/search&q-fulltext=nejakyretezec",
                            "https://test.czc.cz/neexistujicistranka",
                            "https://test.czc.cz/neexistujicipobocka/kontakt",
                            "https://test.czc.cz/neexistujici-zbozi/149338b2b1/produkt",
                            "https://test.czc.cz/nesmyslneurl/nesmyslneid/produkt/recenze",
                            "https://test.czc.cz/nesmyslneurl/nesmyslneid/produkt",
                            "https://test.czc.cz/-/71070/produkt/seznamy",
                            "https://test.czc.cz/nesmyslneurl/produkty",
                            "https://test.czc.cz/nesmyslneid/recenze",
                            "https://test.czc.cz/neexistuje/stitky",
                            "https://test.czc.cz/nesmyslneurl/nesmyslneid/produkt/cenovka",
                            "https://test.czc.cz/nesmyslneurl/nesmyslneid/produkt/clanky",
                            "https://test.czc.cz/nesmyslneurl/nesmyslneid/produkt/galerie",
                            "https://test.czc.cz/geek/neexistujici_stranka",
                            "https://test.czc.cz/nesmyslneid/clanek",
                            "https://test.czc.cz/nesmyslneurl/nesmyslneid/produkt/clanek"
                    }
            )
            @DisplayName("Checking Status Code 404:")
            void checkStatusCode404(String path) {
                HttpURLConnection cont = (HttpURLConnection) new URL(path).openConnection();
                cont.setRequestMethod("HEAD");
                cont.connect();
                int rs = cont.getResponseCode();
                if (rs == 404) {
                    System.out.println("Http response code: " + rs + " pro: " + path);

                } else {
                    throw new Exception("Status kod pro " + path + " je: " + rs + " !! ma byt 404");
                }
            }
            //-------------------------------------- END of Check Status Code 404 -----------------------------------//


            //-------------------------------------- Check  httpResponseCodeOxy1UrlsTest ----------------------------//
            @SneakyThrows
            @ParameterizedTest
            @ValueSource(
                    strings = {
                            "https://test.czc.cz/product.jsp?artno=24466",
                            "https://test.czc.cz/product_doc-845585A86D370F80C125741A005CC91C.html",
                            "https://test.czc.cz/disc_doc-PFF802432FF134EA0C125768E0039F59E.html?ansid=21",
                            "https://test.czc.cz/user_review_doc-Q0902191414290812101618590991947.html",
                            "https://test.czc.cz/art_doc-C91B81F14272A8FCC12574E3001E6774.html",
                            "https://test.czc.cz/mobilni-telefony/produkty?q-c-0-availability=d1",
                            "https://test.czc.cz/apple/hledat?q-c-0-availability=d1&q-c-0-conditionSticker=sNEW",
                            "https://test.czc.cz/apple/zbozi?q-c-0-sticker=sFREE_SHIPING",
                            "https://test.czc.cz/pouzite-zbozi/stitek?q-c-0-sticker=sFREE_SHIPING",
                            "https://test.czc.cz/apple/vyrobce?q-c-0-conditionSticker=sNEW"
                    })
            @DisplayName("Checking Status Code - httpResponseCodeOxy1UrlsTest:")
            void checkStatusCodehttpResponseCodeOxy1UrlsTest(String path) {
                driver.get(path);
                HttpURLConnection connection = (HttpURLConnection) new URL("http://www.ebay.com/itm/131709867498").openConnection();
                connection.setRequestMethod("GET");
                connection.setInstanceFollowRedirects(false);
                int statusCode = connection.getResponseCode();
                this.cookie.confirmCookieModal();
                int rs = connection.getResponseCode();
                if (rs == 301) {
                    System.out.println("Method argument: " + path + " --> " + driver.getCurrentUrl() + "Status Code: " + statusCode);
                } else {
                    throw new Exception("Status kod pro " + path + " je: " + rs);
                }
            }
            //-------------------------------------- END of httpResponseCodePreSEOUrlsTest -----------------------------------/


            //-------------------------------------- Check  PageTemplateTest ------------------------------------------------//
            @SneakyThrows
            @ParameterizedTest
            @ValueSource(
                    strings = {
                            "https://test.czc.cz/test-web?test=erp-replica",
                            "https://test.czc.cz/test-web?test=erp-read",
                            "https://test.czc.cz/test-web?test=proddb-ratio",
                            "https://test.czc.cz/test-web?test=vies-connection",
                            "https://test.czc.cz/test-web?test=db-foreign-key-no-index",
                            "https://test.czc.cz/test-web?test=payu-connection",
                            "https://test.czc.cz/test-web?test=proddb-ping",
                            "https://test.czc.cz/test-web?test=erp-write",
                            "https://test.czc.cz/test-web?test=erp-replica-full",
                            "https://test.czc.cz/test-web?test=ares-connection",
                            "https://test.czc.cz/test-web?test=csob-connection",
                            "https://test.czc.cz/test-web?test=hc-web-api-connection",
                            "https://test.czc.cz/test-web?test=db-varchar2-in-chars",
                            "https://test.czc.cz/test-web?test=uptime",
                            "https://test.czc.cz/test-web?test=mojeid-connection",
                            "https://test.czc.cz/test-web?test=erp-ratio",
                            "https://test.czc.cz/test-web?test=email-send",
                            "https://test.czc.cz/test-web?test=db-write",
                            "https://test.czc.cz/test-web?test=price-guarantee-data-presence"
                    })
            @DisplayName("Checking Status Code - webResponseStatusTest OK/KO:")
            public void webResponseStatusTest(String url) {
                driver.get(url);
                //kontrola, ze stranka obsahuje retazec "OK"
                String pageSource = driver.getPageSource();
                System.out.println("URL: {}, page source: {} " + url + pageSource);
                assertTrue("Obsah stranky ma nulovu velikost.", pageSource.length() > 0);
                assertTrue("Stranka " + url + " neobsahuje status 'OK'.", pageSource.contains("OK"));
                assertFalse("Stranka " + url + " obsahuje status 'KO'.", pageSource.contains("KO"));
            }
            //-------------------------------------- END of PageTemplateTest ------------------------------------------------/

            //-------------------------------------- Check  openAuthUrlAsUnsignedUserTest ----------------------------------//
            @SneakyThrows
            @ParameterizedTest
            @ValueSource(
                    strings = {
                            "https://test.czc.cz/3u74jlmok0jg89dvg31a9j67v3/seznam/smazat",
                            "https://test.czc.cz/999ap6abs6gbebqigs4bt807g1/recenze/editace",
                            "https://test.czc.cz/moje-diskuze/vlakna",
                            "https://test.czc.cz/1313698/moje-diskuze/sledovane-diskuze",
                            "https://test.czc.cz/bonus-klub",
                            "https://test.czc.cz/INV.3019329-AAAAAAAAAAAAAA/faktura",
                            "https://test.czc.cz/moje-diskuze/sledovane-diskuze",
                            "https://test.czc.cz/1313698/moje-diskuze/sledovana-temata",
                            "https://test.czc.cz/moje-diskuze/sledovana-temata",
                            "https://test.czc.cz/-/71821/produkt/recenze/nova",
                            "https://test.czc.cz/moje-diskuze",
                            "https://test.czc.cz/hlidaci-pes/watcherId/smazat",
                            "https://test.czc.cz/1313698/historie-mych-akci",
                            "https://test.czc.cz/ORD.6229744-AAAAAAAAAAAAAA/objednavka",
                            "https://test.czc.cz/1313698/moje-obrazky",
                            "https://test.czc.cz/RMA.163047-AAAAAAAAAAAAAA/reklamace",
                            "https://test.czc.cz/historie-mych-akci",
                            "https://test.czc.cz/dalsi-nastaveni",
                            "https://test.czc.cz/ORD.6229744-AAAAAAAAAAAAAA/objednavka/editace",
                            "https://test.czc.cz/1313698/moje-diskuze",
                            "https://test.czc.cz/moje-obrazky",
                            "https://test.czc.cz/moje-diskuze/zakazane",
                            "https://test.czc.cz/uzivatel/vypis-recenzi",
                            "https://test.czc.cz/moje-videa",
                            "https://test.czc.cz/uzivatel/hlidane-polozky",
                            "https://test.czc.cz/DEL.1079770-AAAAAAAAAAAAAA/dodaci-list",
                            "https://test.czc.cz/dorucovaci-adresy",
                            "https://test.czc.cz/registrace/editace",
                            "https://test.czc.cz/1313698/moje-diskuze/zakazane",
                            "https://test.czc.cz/uzivatel",
                            "https://test.czc.cz/1313698/moje-videa",
                            "https://test.czc.cz/1313698/moje-diskuze/vlakna"
                    })
            @DisplayName("Checking Status Code - TEST")
            public void openAuthUrlAsUnsignedUserTest(String url) {
                driver.get(url);
                HttpURLConnection cont = (HttpURLConnection) new URL(url).openConnection();
                cont.setRequestMethod("HEAD");
                cont.connect();
                this.cookie.confirmCookieModal();
                int rs = cont.getResponseCode();
                if (rs == 200) {
                    System.out.println("testovanie zobrazenia autentizovanej stranky '" + url + "' pre neprihlaseneho uzivatela." + " -- Zobrazuje se Login page?: " + driver.getCurrentUrl().contains("login"));
                } else {
                    throw new Exception("Status kod pro " + url + " je: " + rs + " !! ma byt 200 " + " -- Zobrazuje se Login page?: " + driver.getCurrentUrl().contains("login"));
                }
            }
            //-------------------------------------- END of  openAuthUrlAsUnsignedUserTest ----------------------------------//


            //-------------------------------------- Check  pageTemplateAvailabilityAsUnasignedUserTest ---------------------//
            @SneakyThrows
            @ParameterizedTest
            @ValueSource(
                    strings = {
                            "https://test.czc.cz/acer-aspire-3-a315-34-cervena_5/342953/produkt",
                            "https://test.czc.cz/extra-vyhodny-balicek-lego-art-kolekce-jim-lee-batmantm-31205-batman-a-catwomen_2/344536/produkt",
                            "https://test.czc.cz/iget-security-m3p15v2-bezdratova-ip-kamera/236534b1/produkt",
                            "https://test.czc.cz/laino-jemne-vyzivujici-telove-mleko-ruze-400ml/344265a/produkt",
                            "https://test.czc.cz/patona-baterie-pro-sony-np-bn1-630mah/126115/produkt",
                            "https://test.czc.cz/lauben-electric-kettle-17cr/350876/produkt",
                            "https://test.czc.cz/hp-cf213a-magenta/114323/produkt",
                            "https://test.czc.cz/asus-tuf-gaming-k3-kailh-red-cerna/300606/produkt",
                            "https://test.czc.cz/patriot-signature-line-2gb-ddr2-800-cl6-so-dimm/52412/produkt",
                            "https://test.czc.cz/irobot-braava-jet-m6/273894/produkt",
                            "https://test.czc.cz/orava-lt-1397-127cm/326761/produkt",
                            "https://test.czc.cz/apple-macbook-air-13-m2-8-core-24gb-512gb-10-core-gpu-stribrna-m2-2022_2/349767/produkt",
                            "https://test.czc.cz/emos-led-dekorace-voskova-svicka-12-5-cm-2x-aa-vintage-2-ks-ovladac-casovac/355226/produkt",
                            "https://test.czc.cz/microsoft-comfort-mouse-4500-seda/116842b34/produkt",
                            "https://test.czc.cz/epico-silikonovy-naramek-pro-apple-watch-38-40-41-mm-zelena/342382/produkt",
                            "https://test.czc.cz/microsoft-lifechat-lx-6000-business/118864/produkt",
                            "https://test.czc.cz/caselogic-pouzdro-na-notebook-reflect-15-6-bezova/320882/produkt",
                            "https://test.czc.cz/hp-x500_2/144869/produkt",
                            "https://test.czc.cz/ricoh-theta-z1-360-kamera/257388/produkt",
                            "https://test.czc.cz/lego-architecture-21056-tadz-mahal/318606a/produkt",
                            "https://test.czc.cz/czc-konfigurovatelne-pc-office-pentium-g6500/325881/produkt",
                            "https://test.czc.cz/cc-darkovy-poukaz-czc-cz-200kc-test/255923/produkt",
                            "https://test.czc.cz/kosik"

                    })
            @DisplayName("Checking Status Code - TEST")
            public void pageTemplateAvailabilityAsUnasignedUserTest(String url) {
                driver.get(url);
                HttpURLConnection cont = (HttpURLConnection) new URL(url).openConnection();
                cont.setRequestMethod("HEAD");
                cont.connect();
                this.cookie.confirmCookieModal();
                int rs = cont.getResponseCode();
                if (rs == 200) {
                    System.out.println("testovanie zobrazenia autentizovanej stranky '" + url + "' pre neprihlaseneho uzivatela.");
                } else {
                    throw new Exception("Status kod pro " + url + " je: " + rs + " !! ma byt 200 ");
                }
            }
            //-------------------------------------- END of pageTemplateAvailabilityAsUnasignedUserTest ---------------------//
        }

    }

}