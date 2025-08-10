package tests;

import org.junit.jupiter.api.Test;
import pages.PracticeFormTestPage;
import static tests.FakerDataForTest.*;

public class PracticeFormFakerTest {

    PracticeFormTestPage practiceFormTestPage = new PracticeFormTestPage();

    @Test
    void registrationTest() {
        practiceFormTestPage.openPage()
                .removeBanner()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setPhoneNumber(phoneNumber)
                .setCurrentAddress(currentAddress)
                .setGender(gender)
                .setDateOfBirth(day, month, year)
                .setSubjects(subject)
                .setHobbies(hobby)
                .setUploadPicture(pictureName)
                .setState(state)
                .setCity(city)
                .submitForm();

        practiceFormTestPage.verifyResultsModalAppears()
            .verifyResult("Student Name", firstName + " " + lastName)
                .verifyResult("Student Email", userEmail)
                .verifyResult("Gender", gender)
                .verifyResult("Mobile", phoneNumber)
                .verifyResult("Date of Birth", day +" "+ month + "," + year)
                .verifyResult("Subjects", subject)
                .verifyResult("Hobbies", hobby)
                .verifyResult("Picture", pictureName)
                .verifyResult("Address", currentAddress)
                .verifyResult("State and City", state + " " + city);
}
}
