import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class PostRequest {

    @Test
    public void postWithJsonBody() {
        RestAssured.baseURI = "https://httpbin.org";

        Response response = given()
                .body("{\n" +
                        "    \"name\": \"test name\",\n" +
                        "    \"age\": 18,\n" +
                        "    \"hobby\": \"sport\"\n" +
                        "}").
        when()
                .post("/post").
        then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data", containsString("test name"))
                .body("headers.Content-Length", equalTo("64"))
                .extract().response();

        response.prettyPrint();
        String origin= response.path("origin");
        System.out.println(origin);
    }
}
