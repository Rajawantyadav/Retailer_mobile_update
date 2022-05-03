package com.selenium;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class File_operation {

	public static void main(String[] args) throws FileNotFoundException {

		File file = new File("D:\\retailers\\Retailer_code_mobile.txt");
		Scanner reader = new Scanner(file);

		List<String> retailer_details = new ArrayList<String>();

		while (reader.hasNextLine()) {

			String data = reader.nextLine();
			retailer_details.add(data);
		}

		for (int i = 0; i <=1; i++) {
			if (i % 2 == 0) {
				System.out.println(retailer_details.get(i) + "-----" + i);
			}
			if (i % 2 != 0) {
				System.out.println(retailer_details.get(i) + "-----" + i);
			}
			
		}

	}

}
