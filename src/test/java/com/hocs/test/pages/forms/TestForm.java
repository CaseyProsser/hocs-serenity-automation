package com.hocs.test.pages.forms;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.webelements.RadioButtonGroup;
import org.openqa.selenium.WebDriver;

public class TestForm extends Page {

    @Managed
    WebDriver driver;

    @FindBy(css = "label[for='checkboxContext-Has1']")
    private WebElementFacade checkboxOne;

    @FindBy(css = "label[for='checkboxContext-Has2']")
    private WebElementFacade checkboxTwo;

    @FindBy(css = "label[for='checkboxContext-Has3']")
    private WebElementFacade checkboxThree;

    @FindBy(id = "text-area")
    private WebElementFacade textField;

    @FindBy(className = "govuk-radios__label")
    private WebElementFacade radioButtons;

    @FindBy(css = "label[for='case-type-isA']")
    private RadioButtonGroup radioButtonA;

    @FindBy(css = "label[for='case-type-isB']")
    private WebElementFacade radioButtonB;

    @FindBy(css = "label[for='case-type-isC']")
    private WebElementFacade radioButtonC;

    @FindBy(id = "dropdown-test")
    private WebElementFacade testDropdown;

    public void enterCurrentDate() {
        dateDayField.clear();
        dateDayField.sendKeys(getCurrentDay());
        dateMonthField.clear();
        dateMonthField.sendKeys(getCurrentMonth());
        dateYearField.clear();
        dateYearField.sendKeys(getCurrentYear());
    }

    public void enterName(String name) {
        nameField.clear();
        nameField. sendKeys(name);
    }

    public void enterSurname(String surname) {
        surnameField.clear();
        surnameField.sendKeys(surname);
    }

    public void enterText() {
        textField.clear();
        textField.sendKeys(generateRandomString());
    }

    public void getRadioButtons() {
        String text = radioButtons.getText();
        System.out.println(text);
    }

    public void selectCheckbox() {
        checkboxOne.click();
        checkboxThree.click();
    }

    public void selectRadioButton() {
        radioButtonC.click();
        radioButtonB.click();
    }

    public void selectTestDropdown(int index) {
        testDropdown.selectByIndex(index);
    }

}
