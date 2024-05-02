package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.checkerframework.checker.units.qual.C;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static stepdefinitions.EndToEndTestStepDefinitions.email;

public class Authentication {

    public static String generateToken() {

        Map<String, String> bodyMap = new HashMap<>();

        if (email==null){
            bodyMap.put("email", "shatha3@gmamil.com");//If the email is null we will assign our email and password.
            bodyMap.put("password", "1234567");
        }else {
            bodyMap.put("email", email);//This email will be assigned by selenium run. If selenium does not work, this will be null.
            bodyMap.put("password", "Password.123");
        }


        Response response = given()
                .body(bodyMap)
                .contentType(ContentType.JSON)
                .post("https://thinking-tester-contact-list.herokuapp.com/users/login");
//        response.prettyPrint();

        return response.jsonPath().getString("token");
    }


}
