package com.hocs.test.pages.create_case;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class AddDocuments extends Page {

    @FindBy(className = "govuk-error-summary")
    private WebElementFacade errorMessage;

    @FindBy(id = "add_document")
    private WebElementFacade addDocument;

    public void assertErrorMessageText(String text) {
        assertThat(getErrorMessageText(), containsString(text));
    }

    protected String getErrorMessageText() {
        return errorMessage.getText();
    }

    public void pageTitleIsDisplayed() {
        waitFor(pageTitle);
        pageTitle.containsText("Add documents");
    }

    public void uploadDocument() {
        upload("documents/test.docx").to(addDocument);
    }

}
