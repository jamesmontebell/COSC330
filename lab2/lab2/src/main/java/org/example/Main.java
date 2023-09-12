package org.example;

public class Main {
    public static void main(String[] args) {
        Employee arr[] = new Employee[3];

        Executive emp0 = new Executive("Doe", "John", "VP", 100000, 50000, 2000);
        SoftwareEngineer emp1 = new SoftwareEngineer("Montebell", "Mikey", "Junior", 120000, 50000, 10000);
        TestEngineer emp2 = new TestEngineer("Minecraft", "Steve", "Miner", 500000, 100000);

        arr[0] = emp0;
        arr[1] = emp1;
        arr[2] = emp2;

        arr[0].displayInformation();
        System.out.println();
        arr[1].displayInformation();
        System.out.println();
        arr[2].displayInformation();

        System.out.println();
        System.out.println();
        System.out.println();

        emp0.displayInformation();
        System.out.println();
        emp1.displayInformation();
        System.out.println();
        emp2.displayInformation();
    }
}