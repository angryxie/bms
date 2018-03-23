package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Hashtable;

@SpringBootApplication
@MapperScan(basePackages = {"com.wwxn.bms.dao"})
public class BmsApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(BmsApplication.class, args);
	}
}

