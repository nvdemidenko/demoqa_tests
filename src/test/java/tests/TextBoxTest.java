package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.TextBoxFormTestPage;

public class TextBoxTest {

    TextBoxFormTestPage textBoxPage = new TextBoxFormTestPage();

    @BeforeAll
    static void beforeAll() {
        /*
        не использую, т.к. с этим параметром не выполняется $("#submit").click();
        Configuration.browserSize = "1920x1080";
        */
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        /*
        true используется только для отладки тестов
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 5000;
        */
    }

    @Test
    void fillFormTest() {
        textBoxPage.openPage()
                .removeBanner()
                .setUserName("Иванов Иван")
                .setUserEmail("testmail@test.ru")
                .setCurrentAddress("Москва, Красная площадь, д 2")
                .setPermanentAddress("Москва, Красная площадь, строение 3")
                .clickSubmit();


        textBoxPage.checkResult("Иванов Иван", "testmail@test.ru", "Москва, Красная площадь, д 2", "Москва, Красная площадь, строение 3");
    }
}