package com.rays.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.rays.dto.UserDTO;
import com.rays.service.UserServiceInt;

@Component("testUserService")
public class TestUserService {

	@Autowired
	private UserServiceInt service = null;

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		TestUserService test = (TestUserService) context.getBean("testUserService");

//		test.testAdd();
//		test.testDelete();
//		test.testUpdate();
//		test.testFindByLogin();
//		test.testFindByPk();
		test.testSearch();
	}

	public void testAdd() {

		UserDTO dto = new UserDTO();

		dto.setFirstName("akabr");
		dto.setLastName("mansuri");
		dto.setLogin("akbar@gmail.com");
		dto.setPassword("akbar@123");
		long pk = service.add(dto);
		System.out.println("data added sucessfully.." + pk);

	}
	
	private void testDelete() {

		UserDTO dto = new UserDTO();
		dto.setId(2);
		service.delete(dto);
		System.out.println("data deleted successfully ");

	}

	private void testUpdate() {

		UserDTO dto = new UserDTO();
		dto.setId(1);
		dto.setFirstName("aditya");
		dto.setLastName("sharma");
		dto.setLogin("aditya@gmail.com");
		dto.setPassword("aditya@123");
		service.update(dto);
		System.out.println("data updated successfully..");
	}

	private void testFindByPk() {

		UserDTO dto = new UserDTO();
		dto = service.findByPk(1);

		if (dto != null) {
			System.out.print(dto.getId());
			System.out.print("\t" + dto.getFirstName());
			System.out.print("\t" + dto.getLastName());
			System.out.print("\t" + dto.getLogin());
			System.out.println("\t" + dto.getPassword());
		}
	}

	private void testFindByLogin() {

		UserDTO dto = new UserDTO();
		dto = service.findByLogin("akbar@gmail.com");

		if (dto != null) {
			System.out.print(dto.getId());
			System.out.print("\t" + dto.getFirstName());
			System.out.print("\t" + dto.getLastName());
			System.out.print("\t" + dto.getLogin());
			System.out.println("\t" + dto.getPassword());
		}
	}

	private void testAuthenticate() {
		UserDTO dto = new UserDTO();
		dto = service.authenticate("akshat@gmail.com", "akshat@123");

		if (dto != null) {
			System.out.print(dto.getId());
			System.out.print("\t" + dto.getFirstName());
			System.out.print("\t" + dto.getLastName());
			System.out.print("\t" + dto.getLogin());
			System.out.println("\t" + dto.getPassword());
		}

	}

	private void testSearch() {
		List<UserDTO> list = null;
		UserDTO dto = new UserDTO();

		dto.setFirstName("aditya");

		list = service.search(dto, 1, 5);

		for (UserDTO udto : list) {

			System.out.println(udto.getId());
			System.out.println(udto.getFirstName());
			System.out.println(udto.getLastName());
			System.out.println(udto.getLogin());
			System.out.println(udto.getPassword());
		}
	}


}
