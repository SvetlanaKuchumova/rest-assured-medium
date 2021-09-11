import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;

public class PostRequest {

    @Test
    public void postWithJsonBody() {
        RestAssured.baseURI = "https://httpbin.org";

        JSONObject requestBody = new JSONObject()
                .put("name", "test name")
                .put("age", 18)
                .put("hobby", "sport");
        Response response = given()
                .body(requestBody.toString())
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data", containsString("test name"))
                .body("headers.Content-Length", equalTo("45"))
                .extract().response();

        System.out.println("POST REQUEST");
        System.out.println("Response body from POST request:");
        response.prettyPrint();
        String origin = response.path("origin");
        System.out.println("origin: " + origin);
    }
}
