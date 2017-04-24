package space.marquardt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import space.marquardt.protobuf.Demo.Course;
import space.marquardt.protobuf.Demo.Student;

@SpringBootApplication
public class DemoApplication {

	public static void main(final String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	ProtobufHttpMessageConverter protbufHttpMessageConverter() {
		return new ProtobufHttpMessageConverter();
	}

	@Bean
	CourseRepository createTestResource() {
		final Map<Integer, Course> courses = new HashMap<>();
		final Course course = Course.newBuilder().setId(1).setCourseName("Rest")
				.addAllStudent(this.createTestSutdents()).build();
		final Course course2 = Course.newBuilder().setId(2).setCourseName("Rest 2")
				.addAllStudent(this.createTestSutdents()).build();
		courses.put(1, course);
		courses.put(2, course2);
		return new CourseRepository(courses);
	}

	private List<Student> createTestSutdents() {
		final List<Student> students = new ArrayList<>(5);
		for (int i = 0; i < 5; i++) {
			final Student student = Student.newBuilder().setEmail("Test").setFirstName("test").setId(i)
					.setLastName("test").build();
			students.add(student);
		}
		return students;

	}
	@Bean
	RestTemplate restTemplate(final ProtobufHttpMessageConverter hmc){
		return new RestTemplate(Arrays.asList(hmc));
	}
}
