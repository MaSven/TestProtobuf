package space.marquardt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import space.marquardt.protobuf.Demo.Course;

@RestController
public class CourseController {
	@Autowired
	CourseRepository courseRepository;

	@RequestMapping("courses/{id}")
	Course customer(@PathVariable final Integer id) {
		return this.courseRepository.getCourse(id);
	}
}
