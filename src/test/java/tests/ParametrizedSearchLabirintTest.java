package tests;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Тесты на поиск в интернет магазине Лабиринт")
public class ParametrizedSearchLabirintTest {

    @BeforeEach
    void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        open("https://www.labirint.ru/");
    }


    @ValueSource(strings = {
            "Маленький принц", "Дюна"})
    @DisplayName("Тесты на поиск в интернет магазине Лабиринт (простой поиск)")
    @ParameterizedTest
    @Tags({
            @Tag("WEB"),
            @Tag("SMOKE")
    })
    void successfulSearchTest(String searchQuery) {
        SelenideElement topTitleElement = $(".index-top-title");

        $("#search-field").setValue(searchQuery).pressEnter();
        $$(".search-result > *").shouldBe(CollectionCondition.sizeGreaterThan(0));
        topTitleElement.shouldHave(attribute("data-title", searchQuery));
    }


    @CsvFileSource(resources = "/ParamitrizedSearchLabirintTest.csv")
    @DisplayName("Тесты на поиск в интернет магазине Лабиринт из файла ParamitrizedSearchLabirintTest.csv")
    @ParameterizedTest(name = "Для поискового запроса {0} в карточке товара должен быть результат со словом {1}")
    @Tags({
            @Tag("WEB"),
            @Tag("SMOKE")
    })
    void searchResultQueryCheckCsvFileTest(String searchQuery, String name) {
        SelenideElement topTitleElement = $(".index-top-title");

        $("#search-field").setValue(searchQuery).pressEnter();
        $$(".search-result > *").shouldBe(CollectionCondition.sizeGreaterThan(0));
        topTitleElement.shouldHave(attributeMatching("data-title", ".*" + name + ".*"));
    }

    @CsvSource(value = {
            "Алиса в стране чудес, Алиса",
            "Ruby для чайников, Ruby"
    })

    @DisplayName("Тесты на поиск в интернет магазине Лабиринт из @CsvSource")
    @ParameterizedTest(name = "Для поискового запроса {0} в карточке товара должен быть результат со словом {1}")
    @Tags({
            @Tag("WEB"),
            @Tag("SMOKE")
    })

    void searchResultQueryCheckCsvTest(String searchQuery, String name) {
        SelenideElement topTitleElement = $(".index-top-title");

        $("#search-field").setValue(searchQuery).pressEnter();
        $$(".search-result > *").shouldBe(CollectionCondition.sizeGreaterThan(0));
        topTitleElement.shouldHave(attributeMatching("data-title", ".*" + name + ".*"));
    }

}