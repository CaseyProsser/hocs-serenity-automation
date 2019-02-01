package com.hocs.test.pages.markup;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

public class Topics extends Page {

    @FindBy(id = "react-select-2-input")
    private WebElementFacade topicsTextField;

    @FindBy(id = "react-select-2-input")
    private WebElementFacade focusedTopicsTextField;

    @FindBy(id = "DraftingTeamName")
    public WebElementFacade autoAssignedDraftTeam;

    @FindBy(id = "POTeamName")
    public WebElementFacade autoAssignedPrivateOfficeTeam;

    @FindBy(xpath = "//a[text()='Add a ']")
    public WebElementFacade addTopicButton;


    // Basic Methods

    public void clickAddTopicButton() {
        addTopicButton.click();
    }

    public void enterTopic() {
        topicsTextField.sendKeys(generateRandomString());
    }


    // Multi Step Methods

    public void enterRealTopic() {

        topicsTextField.click();
        System.out.println("Clicked topic Dropdown");
        focusedTopicsTextField.sendKeys("Cardiff University Kittens");
        System.out.println("Sent topic 1 to topic dropdown, waiting 1 second");
        sleep(1000);
        topicsTextField.sendKeys(Keys.RETURN);
        System.out.println("Hit return");
    }


    // Assertions

    public void assertTopicsTextFieldDisplayed() {
        isElementDisplayed(topicsTextField);
    }

}
