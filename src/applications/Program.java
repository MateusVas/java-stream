package applications;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);


		System.out.print("Enter full file path: ");
		String path = sc.next();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			List<Employee> list = new ArrayList<>();
			
			String line = br.readLine();

			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			
			System.out.print("Enter salary: ");
			double salary = sc.nextDouble();
			
			List<String> email = list.stream()
					.filter(e -> e.getSalary() > salary)
					.map(e -> e.getEmail())
					.sorted()
					.collect(Collectors.toList());
			
			System.out.println("Email of people whose salary is more than " + String.format("%.2f", salary) + ":");
			email.forEach(System.out::println);
			
			double sum = list.stream()
					.filter(e -> e.getName().charAt(0) == 'M')
					.map(e -> e.getSalary())
					.reduce(0.0, (x, y) -> x + y);
			
			System.out.print("Sum of salary of peoples whose name starts wih 'M': " + String.format("%.2f", sum));

		} 
		catch (IOException e) {
			System.out.print("Error: " + e.getMessage());
		}


	

		sc.close();
	}

}