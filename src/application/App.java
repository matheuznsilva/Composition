package application;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class App {
    public static void main(String[] args) throws Exception {
        
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.printf("\nEnter department's name: ");
        String departmentName = sc.nextLine();
        System.out.printf("Enter worker data: ");
        System.out.printf("\nName: ");
        String workerName = sc.nextLine();
        System.out.printf("Level: ");
        String workerLevel = sc.nextLine();
        System.out.printf("Base salary: ");
        double baseSalary = sc.nextDouble();
        Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), baseSalary, new Department(departmentName));
        
        System.out.printf("\nHow many contracts to this worker? ");
        int N = sc.nextInt();

        for(int i=0; i<N; i++){
            System.out.printf("\nEnter contract %d data: ", i+1);
            System.out.printf("\nDate (dd/mm/yyyy): ");
            Date contractDate = sdf.parse(sc.next());
            System.out.printf("\nValue per hour: ");
            double valuePerHour = sc.nextDouble();
            System.out.printf("\nDuration (hours): ");
            int hours = sc.nextInt();
            HourContract contract = new HourContract(contractDate, valuePerHour, hours);
            worker.addContract(contract);
        }

        System.out.printf("\n\nEnter month and year to calculate income (mm/yyyy): ");
        String monthAndYear = sc.next();
        int month = Integer.parseInt(monthAndYear.substring(0, 2));
        int year = Integer.parseInt(monthAndYear.substring(3));
        System.out.printf("\n----------------------------\n");
        System.out.println("Name: " + worker.getName());
        System.out.println("Department: " + worker.getDepartment().getName());
        System.out.println("Income for: " + monthAndYear + "; " + String.format("%.2f", worker.income(year, month)));

        sc.close();
    }
}