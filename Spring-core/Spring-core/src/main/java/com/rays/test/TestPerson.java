package com.rays.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class TestPerson {

	public static void main(String[] args) {

		BeanFactory factory = new XmlBeanFactory(new ClassPathResource("person.xml"));

		Person p = (Person) factory.getBean("person");
		System.out.println("person's name is " + p.getName());
		System.out.println("person's address is " + p.getAddress());

		System.out.println("-------------------------");

		Person p1 = (Person) factory.getBean("person1");

		System.out.println("person's name is " + p1.getName());
		System.out.println("person's address is " + p1.getAddress());
		
		

		System.out.println("----dependency injection by parameterise constructor-----");
		
		Person p2 = (Person) factory.getBean("person2");
		

		

		System.out.println("person's name is " + p2.getName());
		System.out.println("person's address is " + p2.getAddress());

	}

}
