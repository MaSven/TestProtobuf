package space.marquardt;

import java.util.Map;

import space.marquardt.protobuf.Demo.Course;

public class CourseRepository {

	private final Map<Integer, Course> courses;

	public CourseRepository(final Map<Integer, Course> courses) {
		this.courses=courses;
	}

	/**
	 * @return the courses
	 */
	public Map<Integer, Course> getCourses() {
		return this.courses;
	}

	public Course getCourse(final int id) {
		return this.courses.get(id);
	}



}
