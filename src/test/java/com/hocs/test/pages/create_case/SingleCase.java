package com.hocs.test.pages.create_case;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class SingleCase extends Page {

    @FindBy(css = "label[for='case-type-MIN']")
    private WebElementFacade dcuMinRadioButton;

    @FindBy(css = "label[for='case-type-TRO']")
    private WebElementFacade dcuTroRadioButton;

    @FindBy(css = "label[for='case-type-DTEN']")
    private WebElementFacade dcuDtenRadioButton;

    public void clickDcuMinRadioButton() {
        dcuMinRadioButton.click();
    }

    public void clickDcuTroRadioButton() {
        dcuTroRadioButton.click();
    }

    public void clickDcuDtenRadioButton() {
        dcuDtenRadioButton.click();
    }

    public void pageTitleIsDisplayed() {
        waitFor(pageTitle);
        pageTitle.containsText("Create Case");
    }


}
