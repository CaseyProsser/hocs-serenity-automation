package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeNotNull;

import cucumber.api.java.en.When;

public class SearchStepDefs {

    @When("^I perform a valid search on the \"([^\"]*)\" page")
    public void validSearchOnPage(String pageName) {
        switch (pageName.toUpperCase()) {
            case "":
                break;
            default:
                System.out.println(pageName
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                pageName = null;
                assumeNotNull(pageName);
        }
    }

}
