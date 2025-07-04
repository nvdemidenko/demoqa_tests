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
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = true;
        //Configuration.timeout = 5000;
    }

    @Test
    void practiceFormTest () {

        open("/automation-practice-form");

        //Заполняем ФИО и email
        $("#firstName").setValue("Имя");
        $("#lastName").setValue("Фамилия");
        $("#userEmail").setValue("mailfortest@test.test");

        //Заполняем пол
        $("label[for='gender-radio-1']").click();

        //Заполняем телефон
        $("#userNumber").setValue("8999999999");

        //Заполняем календарь
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__month-select").selectOption("March");
        $(".react-datepicker__day--001").click();


        //Заполняем предметы
        $("#subjectsInput").setValue("Math").pressEnter();
        $("#subjectsInput").setValue("Physics").pressEnter();
        $("#subjectsInput").setValue("Computer Science").pressEnter();

        //Заполняем хобби
        $("label[for='hobbies-checkbox-1']").click();
        $("label[for='hobbies-checkbox-3']").click();

        //Заполняем фото
        $("#uploadPicture").uploadFromClasspath("picture.png");

        //Заполняем адрес
        $("#currentAddress").setValue("Москва, Театральный проезд, 5с1");

        //Заполняем штат и город
        $("#state").click();
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#city").click();
        $("#react-select-4-input").setValue("Gurgaon").pressEnter();

        //Нажимаем кнопку
        $("#submit").click();

        //Проверки всего что наполняли

        $(".table-responsive").
                shouldHave(
                        text("Имя Фамилия"),
                        text("mailfortest@test.test"),
                        text("Male"),
                        text("8999999999"),
                        text("01 March,1995"),
                        text("Maths, Physics, Computer Science"),
                        text("Sports, Music"),
                        text("futurama.jpg"),
                        text("Москва, Театральный проезд, 5с1"),
                        text("NCR Gurgaon")
                );
    }
}
