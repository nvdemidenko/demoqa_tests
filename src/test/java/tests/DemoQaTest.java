package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import attach.Attach;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@Tag("web")
public class DemoQaTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        //Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        //Configuration.holdBrowserOpen = true;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Test

    void practiceFormTest () {

        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        //ФИО и email
        $("#firstName").setValue("Иван");
        $("#lastName").setValue("Иванов");
        $("#userEmail").setValue("testmail@test.test");

        //Пол
        $("label[for='gender-radio-1']").click();

        //Телефон
        $("#userNumber").setValue("8987654321");

        //Календарь
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1982");
        $(".react-datepicker__month-select").selectOption("March");
        $(".react-datepicker__day--009").click();


        //Предметы
        $("#subjectsInput").setValue("Math").pressEnter();
        $("#subjectsInput").setValue("Physics").pressEnter();
        $("#subjectsInput").setValue("Computer Science").pressEnter();

        //Хобби
        $("label[for='hobbies-checkbox-1']").click();
        $("label[for='hobbies-checkbox-3']").click();

        //Фото
        $("#uploadPicture").uploadFromClasspath("futurama.jpg");

        //Адрес
        $("#currentAddress").setValue("Москва, Красная площадь, д 2");

        //Штат и город
        $("#state").click();
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#city").click();
        $("#react-select-4-input").setValue("Gurgaon").pressEnter();

        //Нажимаем кнопку submit
        $("#submit").click();

        //Проверка анкеты
        $(".table-responsive").
                shouldHave(
                        text("Иван Иванов"),
                        text("testmail@test.test"),
                        text("Male"),
                        text("8987654321"),
                        text("09 March,1982"),
                        text("Maths, Physics, Computer Science"),
                        text("Sports, Music"),
                        text("futurama.jpg"),
                        text("Москва, Красная площадь, д 2"),
                        text("NCR Gurgaon")
                );
    }
    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
