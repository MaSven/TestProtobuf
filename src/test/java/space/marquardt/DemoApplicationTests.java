package space.marquardt;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.googlecode.protobuf.format.JsonFormat;

import space.marquardt.protobuf.Demo.Course;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
  @Autowired
  private RestTemplate restTemplate;

  private static final transient String COURSE1_URL = "http://localhost:8080/courses/1";

  @Test
  public void contextLoads() {
    final ResponseEntity<Course> course = this.restTemplate
        .getForEntity(DemoApplicationTests.COURSE1_URL, Course.class);
    this.assertRespone(course.toString());

  }

  private void assertRespone(final String response) {
    Assert.assertThat(response, CoreMatchers.containsString("id"));
  }

  private InputStream executeHttprequest(final String url) throws IOException {
    final CloseableHttpClient httpClient = HttpClients.createDefault();
    final HttpGet get = new HttpGet(url);
    final HttpResponse httpResponse = httpClient.execute(get);
    return httpResponse.getEntity().getContent();
  }

  private String convertProtobufMessageStreamToJsonString(final InputStream protobufStream)
      throws IOException {
    final JsonFormat jsonFormat = new JsonFormat();
    final Course course = Course.parseFrom(protobufStream);
    return jsonFormat.printToString(course);

  }

  @Test
  public void whenUsingHttpClient_thenSucceed() throws IOException {
    final InputStream responseStream = this.executeHttprequest(DemoApplicationTests.COURSE1_URL);
    final String jsonOutput = this.convertProtobufMessageStreamToJsonString(responseStream);
    System.out.println(jsonOutput);
  }

}
