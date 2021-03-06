package com.andriosi.fabio.firstspring.test;

import com.andriosi.fabio.firstspring.Greeting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void corsWithAnnotation() throws Exception {
        ResponseEntity<Greeting> entity = this.restTemplate.exchange(
                RequestEntity.get(uri("/greeting")).header(HttpHeaders.ORIGIN, "http://localhost:9000").build(),
                Greeting.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals("http://localhost:9000", entity.getHeaders().getAccessControlAllowOrigin());
        Greeting greeting = entity.getBody();
        assertEquals("Hello, Stranger!", greeting.getContent());
    }

    @Test
    public void corsWithJavaconfig() {
        ResponseEntity<Greeting> entity = this.restTemplate.exchange(
                RequestEntity.get(uri("/greeting-javaconfig")).header(HttpHeaders.ORIGIN, "http://localhost:9000").build(),
                Greeting.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals("http://localhost:9000", entity.getHeaders().getAccessControlAllowOrigin());
        Greeting greeting = entity.getBody();
        assertEquals("Hello, Stranger!", greeting.getContent());
    }

    private URI uri(String path) {
        return restTemplate.getRestTemplate().getUriTemplateHandler().expand(path);
    }

}
