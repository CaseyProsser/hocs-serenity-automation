package com.hocs.test.api.workflow;

import static config.Environments.LOCAL;
import static config.Services.WORKFLOW;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;

public class WorkflowService extends PageObject {

    private Response response;

    private RequestSpecification request;

    private Response post;

    public void getInfo() {
        Serenity.setSessionVariable("getInfo")
                .to(LOCAL.getEnvironmentURL() + WORKFLOW.getPort() + "/actuator/info");
    }

    public void assertResponse(int statusCode) {
        response.then().statusCode(statusCode);
    }

}
