package com.niksde.cruddemo;

import com.niksde.cruddemo.dao.AppDAO;
import com.niksde.cruddemo.entity.Instructor;
import com.niksde.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
//			createInstructor(appDAO);
			findInstructor(appDAO);
//			deleteInstructorById(appDAO);

//			findInstructorDetail(appDAO);

//			deleteInstructorDetailById(appDAO);
		};

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

	private void deleteInstructorById(AppDAO appDAO) {
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
