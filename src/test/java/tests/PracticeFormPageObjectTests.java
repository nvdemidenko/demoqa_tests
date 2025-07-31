package tests;

import org.junit.jupiter.api.Test;
import pages.PracticeFormTestPage;

public class PracticeFormPageObjectTests {

    PracticeFormTestPage registrationPage = new PracticeFormTestPage();

    @Test
    void successfulRegistrationTest() {
        registrationPage.openPage()
                .removeBanner()
                .setFirstName("Иван")
                .setLastName("Иванов")
                .setEmail("testmail@test.test")
                .setPhoneNumber("8987654321")
                .setCurrentAddress("Москва, Красная площадь, д 2")
                .setGender("Male")
                .setDateOfBirth("09", "March", "1982")
                .setSubjects("Physics")
                .setSubjects("Math")
                .setHobbies("Sports")
                .setHobbies("Music")
                .setUploadPicture("futurama.jpg")
                .setState("NCR")
                .setCity("Gurgaon")
                .submitForm();


        registrationPage.verifyResultsModalAppears()
                .verifyResult("Student Name", "Иван Иванов")
                .verifyResult("Student Email", "testmail@test.test")
                .verifyResult("Gender", "Male")
                .verifyResult("Mobile", "8987654321")
                .verifyResult("Date of Birth", "09 March,1982")
                .verifyResult("Subjects", "Physics, Math")
                .verifyResult("Hobbies", "Sports, Music")
                .verifyResult("Picture", "futurama.jpg")
                .verifyResult("Address", "Москва, Красная площадь, д 2")
                .verifyResult("State and City", "NCR Gurgaon");
    }

    @Test
    void successfulRegistrationMinimalTest() {
        registrationPage.openPage()
                .removeBanner()
                .setFirstName("Олег")
                .setLastName("Иванов")
                .setGender("Male")
                .setPhoneNumber("8987654321")
                .submitForm();

        registrationPage.verifyResultsModalAppears()
                .verifyResult("Student Name", "Олег Иванов")
                .verifyResult("Gender", "Male")
                .verifyResult("Mobile", "8987654321");
    }

    @Test
    void negativeRegistrationTest() {
        registrationPage.openPage()
                .removeBanner()
                .setFirstName("Олег")
                .setLastName("Иванов")
                .setGender("Male")
                .submitForm();

        registrationPage.verifyResultsModalNotAppears();
    }
}