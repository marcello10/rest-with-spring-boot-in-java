package br.com.marcelo.restwithspringbootinjava.integrationtests.controller;

import br.com.marcelo.restwithspringbootinjava.configs.TestConfigs;
import br.com.marcelo.restwithspringbootinjava.integrationtests.testcontainer.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PersonControllerTest extends AbstractIntegrationTest {

	@Test
	public void shouldDisplaySwaggerUiPage() {
		var content = given()
						.basePath("/swagger-ui/index.html")
						.port(TestConfigs.SERVER_PORT)
						.when()
						.get()
						.then()
						.statusCode(200)
						.extract()
						.body()
						.asString();
		assertTrue(content.contains("Swagger UI"));
	}

}
