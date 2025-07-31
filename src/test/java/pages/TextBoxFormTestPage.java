package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class TextBoxFormTestPage {

    private final SelenideElement userName = $("#userName"),
            userEmail = $("#userEmail"),
            userCurrentAddress = $("#currentAddress"),
            userPermanentAddress = $("#permanentAddress"),
            submitButton = $("#submit"),
            resultFullName = $("#output #name"),
            resultEmail = $("#output #email"),
            resultCurrentAddress = $("#output #currentAddress"),
            resultPermanentAddress = $("#output #permanentAddress");


    public TextBoxFormTestPage openPage() {
        open("/text-box");

        return this;
    }

    public TextBoxFormTestPage removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public TextBoxFormTestPage setUserName(String value) {
        userName.setValue(value);

        return this;
    }

    public TextBoxFormTestPage setUserEmail(String value) {
        userEmail.setValue(value);

        return this;
    }

    public TextBoxFormTestPage setCurrentAddress(String value) {
        userCurrentAddress.setValue(value);

        return this;
    }

    public TextBoxFormTestPage setPermanentAddress(String value) {
        userPermanentAddress.setValue(value);

        return this;
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public void checkResult(String userName, String userEmail, String userCurrentAddress, String userPermanentAddress) {
        resultFullName.shouldHave(text(userName));
        resultEmail.shouldHave(text(userEmail));
        resultCurrentAddress.shouldHave(text(userCurrentAddress));
        resultPermanentAddress.shouldHave(text(userPermanentAddress));

    }
}