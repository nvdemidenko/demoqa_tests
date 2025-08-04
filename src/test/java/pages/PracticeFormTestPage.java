package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.RegistrationResult;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class PracticeFormTestPage {
    private SelenideElement
            firstNameInput,
            lastNameInput,
            userEmailInput,
            genderWrapper,
            userPhoneNumberInput,
            calendarInput,
            subjectsInput,
            hobbiesWrapper,
            uploadPictureInput,
            currentAddressInput,
            stateInput,
            cityInput,
            stateCityWrapper,
            submitButton,
            fixedBan,
            footer;

    CalendarComponent calendarComponent = new CalendarComponent();
    public pages.PracticeFormTestPage openPage() {
        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        initializeElements();
        return this;
    }

    private void initializeElements() {
        firstNameInput = $("#firstName");
        lastNameInput = $("#lastName");
        userEmailInput = $("#userEmail");
        genderWrapper = $("#genterWrapper");
        userPhoneNumberInput = $("#userNumber");
        calendarInput = $("#dateOfBirthInput");
        subjectsInput = $("#subjectsInput");
        hobbiesWrapper = $("#hobbiesWrapper");
        uploadPictureInput = $("#uploadPicture");
        currentAddressInput = $("#currentAddress");
        stateInput = $("#state");
        cityInput = $("#city");
        stateCityWrapper = $("#stateCity-wrapper");
        submitButton = $("#submit");
        fixedBan = $("#fixedban");
        footer = $("footer");
    }

    RegistrationResult registrationResult = new RegistrationResult();

    public pages.PracticeFormTestPage removeBanner() {
        executeJavaScript("arguments[0].remove()", fixedBan);
        executeJavaScript("arguments[0].remove()", footer);
        return this;
    }

    public pages.PracticeFormTestPage setFirstName(String  firstName) {
        firstNameInput.setValue(firstName);

        return this;
    }

    public pages.PracticeFormTestPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);

        return this;
    }

    public pages.PracticeFormTestPage setEmail(String userEmail) {
        userEmailInput.setValue(userEmail);

        return this;
    }

    public pages.PracticeFormTestPage setGender(String gender) {
        genderWrapper.$(byText(gender)).click();

        return this;
    }

    public pages.PracticeFormTestPage setPhoneNumber(String userNumber) {
        userPhoneNumberInput.setValue(userNumber);

        return this;
    }

    public pages.PracticeFormTestPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public pages.PracticeFormTestPage setSubjects(String subjects) {
        subjectsInput.setValue(subjects).pressEnter();

        return this;
    }

    public pages.PracticeFormTestPage setHobbies (String hobbies) {
        hobbiesWrapper.$(byText(hobbies)).click();

        return this;
    }

    public pages.PracticeFormTestPage setUploadPicture (String fileName) {
        uploadPictureInput.uploadFromClasspath(fileName);

        return this;
    }

    public pages.PracticeFormTestPage setCurrentAddress (String currentAddress) {
        currentAddressInput.setValue(currentAddress);

        return this;
    }

    public pages.PracticeFormTestPage setCity(String city) {
        cityInput.click();
        stateCityWrapper.$(byText(city)).click();
        return this;
    }

    public pages.PracticeFormTestPage setState(String state) {
        stateInput.click();
        stateCityWrapper.$(byText(state)).click();
        return this;
    }

    public pages.PracticeFormTestPage submitForm() {
        submitButton.click();
        return this;
    }


    public PracticeFormTestPage verifyResultsModalAppears() {
        registrationResult.verifyModalAppears();

        return this;
    }

    public PracticeFormTestPage verifyResult(String key, String value) {
        registrationResult.verifyResult(key, value);

        return this;
    }

    public void verifyResultsModalNotAppears() {
        registrationResult.verifyModalAppearsNegative();

    }
}