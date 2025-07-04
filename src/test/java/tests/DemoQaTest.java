package tests;

import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DemoQaTest {

    @BeforeAll
    static void beforeAll() {
        /*
        не использую, т.к. с этим параметром не выполняется $("#submit").click();
        Configuration.browserSize = "1920x1080";
        */
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 5000;
    }

    @Test
    void practiceFormTest () {

        open("/automation-practice-form");

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
        $("#closeLargeModal").click();
        $("h1").shouldHave(text("Practice Form"));
    }
}
