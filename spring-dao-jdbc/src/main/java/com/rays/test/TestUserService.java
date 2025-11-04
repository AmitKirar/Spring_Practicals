package com.rays.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.rays.dto.UserDTO;
import com.rays.service.UserServiceInt;

@Component("testUserService")
public class TestUserService {

	@Autowired
	public UserServiceInt service = null;

	public static void main(String[] args) throws Exception {

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		TestUserService test = (TestUserService) context.getBean("testUserService");

		test.testAddUser();
//        test.testDeleteUser();
//        test.testUpdateUser();
//        test.testFindByLogin();
//        test.testAuthenticate();
//        test.testFindByPk();
//       test.testFindByName();
//        test.testNextPk();
	}

	public void testAddUser() throws Exception {
		UserDTO dto = new UserDTO();
//        dto.setId(3);
		dto.setFirstName("uday");
		dto.setLastName("dabi");
		dto.setLogin("uday@gmail.com");
		dto.setPassword("uday@123");

		long pk = service.add(dto);
		System.out.println("Data inserted... ID: " + pk);
	}

	public void testDeleteUser() {
		UserDTO dto = new UserDTO();
		dto.setId(1);
		service.delete(dto);
		System.out.println("Data deleted successfully.");
	}

	public void testUpdateUser() {
		UserDTO dto = new UserDTO();
		dto.setFirstName("amit");
		dto.setLastName("rajput");
		dto.setLogin("amit@gmail.com");
		dto.setPassword("amit@123");
		dto.setId(1);

		service.update(dto);
		System.out.println("Data updated successfully.");
	}

	public void testFindByLogin() {
		UserDTO dto = service.findyByLogin("amit@gmail.com");

		if (dto != null) {
			System.out.print(dto.getId());
			System.out.print("\t" + dto.getFirstName());
			System.out.print("\t" + dto.getLastName());
			System.out.print("\t" + dto.getLogin());
			System.out.print("\t" + dto.getPassword());
		} else {
			System.out.println("Invalid login ID.");
		}
	}

	public void testAuthenticate() {
		UserDTO dto = service.authenticate("amit@gmail.com", "amit@123");

		if (dto != null) {
			System.out.print(dto.getId());
			System.out.print("\t" + dto.getFirstName());
			System.out.print("\t" + dto.getLastName());
			System.out.print("\t" + dto.getLogin());
			System.out.print("\t" + dto.getPassword());
		} else {
			System.out.println("Invalid login or password.");
		}
	}

	public void testFindByPk() {
		UserDTO dto = service.findByPk(5);

		if (dto != null) {
			System.out.print(dto.getId());
			System.out.print("\t" + dto.getFirstName());
			System.out.print("\t" + dto.getLastName());
			System.out.print("\t" + dto.getLogin());
			System.out.print("\t" + dto.getPassword());
		} else {
			System.out.println("Record not found for this PK.");
		}
	}

	public void testFindByName() {
		UserDTO dto = service.findByName("amit");

		if (dto != null) {
			System.out.print(dto.getId());
			System.out.print("\t" + dto.getFirstName());
			System.out.print("\t" + dto.getLastName());
			System.out.print("\t" + dto.getLogin());
			System.out.print("\t" + dto.getPassword());
		} else {
			System.out.println("Record not found for this name.");
		}
	}

	public void testNextPk() {
		Integer pk = service.nextPk();
		System.out.println("Next PK is: " + pk);
	}
}
