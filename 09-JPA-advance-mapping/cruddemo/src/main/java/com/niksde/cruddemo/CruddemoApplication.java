package com.niksde.cruddemo;

import com.niksde.cruddemo.dao.AppDAO;
import com.niksde.cruddemo.entity.Course;
import com.niksde.cruddemo.entity.Instructor;
import com.niksde.cruddemo.entity.InstructorDetail;
import com.niksde.cruddemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
//			createCourseAndReviews(appDAO);
//			retrieveCourseAndReviews(appDAO);
			deleteCourseAndReviews(appDAO);
		};


	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Deleting course id: " + theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		int theId = 10;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());
		System.out.println("Done!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		Course tempCourse = new Course("Pacman - Home To Score One Million Points");

		tempCourse.add(new Review("Great course ... loved it!"));
		tempCourse.add(new Review("Cool course, job well done."));
		tempCourse.add(new Review("What a dumb course, you are an idiot!"));

		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDAO.save(tempCourse);

		System.out.println("Done!");

	}

	private void backup(AppDAO appDAO) {
		//			createInstructor(appDAO);
//			findInstructor(appDAO);
//			deleteInstructor(appDAO);

//			findInstructorDetail(appDAO);

//			deleteInstructorDetailById(appDAO);

//			createInstructorWithCourses(appDAO);

//			findInstructorWithCourses(appDAO); // on fetch type is eager

//			findCoursesForInstructor(appDAO);

//			findInstructorWithCoursesJoinFetch(appDAO);

//			updateInstructor(appDAO);

//			updateCourse(appDAO);

//			deleteCourse(appDAO);

	}


	private void deleteCourse(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Deleting course id: "+ theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Done!");

	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Finding course id: "+ theId);
		Course tempCourse = appDAO.findCourseById(theId);

		System.out.println("Updating instructor id: "+ theId);
		tempCourse.setTitle("Enjoy The Simple Things");

		appDAO.update(tempCourse);
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: "+ theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("Updating instructor id: "+ theId);
		tempInstructor.setLastName("PUBLIC");

		appDAO.update(tempInstructor);
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: "+ theId);

		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor: "+ tempInstructor);
		System.out.println("the associated courses: "+ tempInstructor.getCourses());
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: "+ theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: "+ tempInstructor);

		List<Course> courses = appDAO.findCoursesByInstructorId(theId);
		tempInstructor.setCourses(courses);

		System.out.println("the associated courses: "+ tempInstructor.getCourses());
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: "+ theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: "+ tempInstructor);
		System.out.println("the associated courses: "+ tempInstructor.getCourses());
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor tempInstructor = new Instructor("Susan", "Public", "susan@niksde.com");

		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.niksde.com/youtube2",
				"Video Games");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("The Pinball Masterclass");

		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);


		System.out.println("Saving instructor: "+ tempInstructor);
		System.out.println("The courses: "+ tempInstructor.getCourses());

		appDAO.save(tempInstructor);

		System.out.println("Done!");

	}

	private void deleteInstructorDetailById(AppDAO appDAO) {
		int theId = 3;
		System.out.println("Deleting instructor detail by id: "+ theId);
		appDAO.deleteInstructorDetailById(theId);
		System.out.println("Done!");

	}

	private void findInstructorDetail(AppDAO appDAO) {
		int theId = 2;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

		System.out.println("InstructorDetail: "+ tempInstructorDetail);
		System.out.println("Instructor: "+ tempInstructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting instructor by id: "+ theId);

		appDAO.deleteInstructorById(theId);

		System.out.println("Done!");

	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 3;
		System.out.println("Finding instructor id: "+ theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("Instructor: "+ tempInstructor);
		System.out.println("the associated instructorDetail only: "+ tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
//		Instructor tempInstructor = new Instructor("Nik", "Shisode", "nik@niksde.com");
//
//		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.niksde.com/youtube",
//				"coding");

		Instructor tempInstructor = new Instructor("Madhu", "Patel", "madhu@niksde.com");

		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.niksde.com/youtube2",
				"guitar");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		System.out.println("Saving Instructor: "+ tempInstructor);

		appDAO.save(tempInstructor);

		System.out.println("Done!");

	}

}
