package com.didispace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author 程序猿DD
 * @version 1.0.0
 * @blog http://blog.didispace.com
 *
 */
@SpringBootApplication
public class Application {
	
	public void test3() {
		System.out.println("test3");
	}
	public void test1() {
		System.out.println("test1");
	}
	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

	}

	public void test2() {
		System.out.println("test2");
	}

	public void test4() {
		System.out.println("test4");
	}

}
