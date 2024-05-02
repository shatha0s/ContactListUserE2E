package stepdefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojos.ContactPojo;

import static base_urls.ContactListBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class ContactListContactStepDefinitions {
    ContactPojo expectedData;
    Response response;
    private String contactId;
    public static String email;

    @Given("set the url for adding contact")
    public void set_the_url_for_adding_contact() {
        //https://thinking-tester-contact-list.herokuapp.com/contacts
        spec.pathParam("first", "contacts");
    }

    @Given("set the expected data for adding contact")
    public void set_the_expected_data_for_adding_contact() throws JsonProcessingException {
        String strJson = """
                {
                    "firstName": "John",
                    "lastName": "Doe",
                    "birthdate": "1970-01-01",
                    "email": "jdoe@fake.com",
                    "phone": "8005555555",
                    "street1": "1 Main St.",
                    "street2": "Apartment A",
                    "city": "Anytown",
                    "stateProvince": "KS",
                    "postalCode": "12345",
                    "country": "USA"
                }""";

        expectedData = new ObjectMapper().readValue(strJson, ContactPojo.class);
        System.out.println("expectedData = " + expectedData);


    }

    @When("send the post request for adding contact")
    public void send_the_post_request_for_adding_contact() {
        response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();
    }

    @Then("do assertion for adding contact")
    public void do_assertion_for_adding_contact() {
        ContactPojo actualData = response.as(ContactPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201, response.statusCode());
        assertEquals(expectedData.getFirstName(), actualData.getFirstName());
        //Rest is homework!!


    }


    @Given("set the URL for reading the created contact")
    public void setTheURLForReadingTheCreatedContact() {
        spec.pathParam("first", "contacts");
    }

    @Given("set the expected data for reading contact")
    public void setTheExpectedDataForReadingContact() throws JsonProcessingException {
        String strJson = """
                {
                    "firstName": "John",
                    "lastName": "Doe",
                    "birthdate": "1970-01-01",
                    "email": "jdoe@fake.com",
                    "phone": "8005555555",
                    "street1": "1 Main St.",
                    "street2": "Apartment A",
                    "city": "Anytown",
                    "stateProvince": "KS",
                    "postalCode": "12345",
                    "country": "USA"
                }""";

        expectedData = new ObjectMapper().readValue(strJson, ContactPojo.class);
        System.out.println("expectedData = " + expectedData);

    }

    @When("send the GET request for reading the created contact")
    public void sendTheGETRequestForReadingTheCreatedContact() {

        response = given(spec)
                .get("{first}");
    }

    @Then("do assertions for reading the created contact")
    public void doAssertionsForReadingTheCreatedContact() {
        response
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Given("set the url for update contact")
    public void setTheUrlForUpdateContact() {
        spec.pathParams("first", "contacts");

    }

    @And("set the expected data for update contact")
    public void setTheExpectedDataForUpdateContact() {
        expectedData = new ContactPojo("shatha", "aziz",
                "ksa", "2000-12-03", "0555678", "medina", "42367", "ksa",
                "king fahad ", "king fahad ", "shatha3@gmamil.com");
        System.out.println("expectedData = " + expectedData);

    }

    @When("send the patch request for update contact")
    public void sendThePatchRequestForUpdateContact() {
        response = given(spec).body(expectedData).patch("{first}/6633a00d5067770013109a46");
        response.prettyPrint();

    }

    @Then("do assertion for update contact")
    public void doAssertionForUpdateContact() {

    }

    @Given("set the url for delete contact")
    public void setTheUrlForDeleteContact() {
        spec.pathParams("first", "contacts");

    }

    @When("send the delete request contact")
    public void sendTheDeleteRequestContact() {
        response = given(spec).delete("{first}/6633a00d5067770013109a46");
        response.prettyPrint();
    }


    @Then("do assertion for delete contact")
    public void doAssertionForDeleteContact() {
        response
                .then()
                .assertThat()
                .statusCode(200);
    }


    @Given("set the URL for reading the Nonexistent contact")
    public void setTheURLForReadingTheNonexistentContact() {
        spec.pathParams("first", "contacts");

    }

    @When("send the GET request for reading the created contact using id")
    public void sendTheGETRequestForReadingTheCreatedContactUsingId() {
        response = given(spec).get("{first}/6633a00d5067770013109a46");
        response.prettyPrint();
    }

    @Then("message should be faild")
    public void messageShouldBe() {
        response
                .then()
                .assertThat()
                .statusCode(404);

    }
}
