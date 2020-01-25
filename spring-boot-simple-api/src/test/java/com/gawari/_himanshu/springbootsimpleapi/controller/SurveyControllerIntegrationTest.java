package com.gawari._himanshu.springbootsimpleapi.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.gawari._himanshu.springbootsimpleapi.SpringBootSimpleApiApplication;
import com.gawari._himanshu.springbootsimpleapi.model.Question;

@SpringBootTest(classes = SpringBootSimpleApiApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
class SurveyControllerIntegrationTest {

	@LocalServerPort
	private int port;

	private TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@BeforeEach
	void setUp() throws Exception {
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	}

	/*
	 * @Test public void retrieveSurveyQuestion() throws Exception {
	 * System.out.println("Random Local Port " + port); // String expected =
	 * "{id:Question1,description:Largest Country in the //
	 * World,correctAnswer:Russia,options:[India,Russia,United States,China]}";
	 * 
	 * ResponseEntity<String> response =
	 * template.exchange(createUrl("/surveys/Survey1/questions/Question1"),
	 * HttpMethod.GET, new HttpEntity<String>("DUMMY_DOESNT_MATTER", headers),
	 * String.class);
	 * 
	 * System.out.println("Response: " + response.getBody());
	 * JSONAssert.assertEquals("{id:Question1}", response.getBody(), false); }
	 */

	@Test
	public void testRetrieveSurveyQuestion() throws JSONException {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createUrl("/surveys/Survey1/questions/Question1"),
				HttpMethod.GET, entity, String.class);

		// String expected = "{id:Question1,description:Largest Country in the
		// World,correctAnswer:Russia}";

		JSONAssert.assertEquals("{id:Question1}", response.getBody(), false);
	}

	@Test
	public void retrieveAllSurveyQuestions() throws Exception {

		ResponseEntity<List<Question>> response = restTemplate.exchange(createUrl("/surveys/Survey1/questions"),
				HttpMethod.GET, new HttpEntity<String>("DUMMY_DOESNT_MATTER", headers),
				new ParameterizedTypeReference<List<Question>>() {
				});

		Question sampleQuestion = new Question("Question1", "Largest Country in the World", "Russia",
				Arrays.asList("India", "Russia", "United States", "China"));

		assertTrue(response.getBody().contains(sampleQuestion));
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void addQuestion() {

		Question question = new Question("DOESNTMATTER", "Question1", "Russia",
				Arrays.asList("India", "Russia", "United States", "China"));

		HttpEntity entity = new HttpEntity<Question>(question, headers);

		ResponseEntity<String> response = restTemplate.exchange(createUrl("/surveys/Survey1/questions"),
				HttpMethod.POST, entity, String.class);

		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

		assertTrue(actual.contains("/surveys/Survey1/questions/"));

	}

	private String createUrl(String uri) {
		return "http://localhost:" + port + uri;
	}

}
