import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

class Manager {
    static ArrayList<Inventory> inventoryArrayList = new ArrayList<>();
    static String productName = "", productCategory = "";
    static double productPrice = 0.00;


    static DecimalFormat df = new DecimalFormat("R#,###.00");

    static Inventory inventory = new Inventory(productName, productCategory, productPrice);
    String managerUsername;
    int managerPin;

    public Manager( String managerUsername, int managerPin ){
        this.managerUsername = managerUsername;
        this.managerPin = managerPin;
    }

    //setters for manager values
    public void setManagerUsername(String managerUsername){
        this.managerUsername = managerUsername;
    }

    public void setManagerPin(int managerPin){
        this.managerPin = managerPin;
    }

    //getters for manager values

    public String getManagerUsername(){
        return managerUsername;
    }

    public int getManagerPin(){
        return managerPin;
    }

    public static void managerMenu(Scanner sc, ArrayList<Employee> employees, Iterator iterator, Employee employee){

        boolean managerMenuRunning = true;
        int option;
        while( managerMenuRunning ){
            System.out.println(" ==== Main Menu ==== ");
            System.out.println("<1> See Inventory");
            System.out.println("<2> Add Product");
            System.out.println("<3> Add New Employee");
            System.out.println("<4> Remove Employee");
            System.out.println("<5> Employee List");
            System.out.println("<0> Exit");

            try{
                System.out.print("Select: ");
                option = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException ime) {
                System.out.println("Error!");
                throw new RuntimeException(ime);
            }
            switch ( option ) {
                case 1: seeInventory(sc);
                    break;
                case 2: addProduct(sc, inventoryArrayList, inventory);
                    break;
                case 3:
                    addEmployee(sc, employees, employee);
                    break;
                case 4: removeEmployee(sc, employees, iterator);
                    break;
                case 5: employeeList(employees, employee);
                    break;
                case 0:
                    System.out.println("Returning to Home Menu....");
                    managerMenuRunning = false;
                    break;
                default:
                    System.out.println("Invalid Input!");
            }


        }
    }
    public static void seeInventory(Scanner sc){

        boolean seeInventoryMenuRunning = true;
        int option2;

        while( seeInventoryMenuRunning ){
            if ( inventoryArrayList.isEmpty() ) {
                System.out.println("=== No Products Found ===");
                System.out.println("Returning to Main Menu....\n");
                seeInventoryMenuRunning = false;
            } else {
                for ( Inventory i : inventoryArrayList ) {
                    System.out.println(i.toString());
                }

            }
            System.out.println("<0> Return to Main Menu");
            System.out.print("Select: ");
            option2 = sc.nextInt();

            if ( option2 == 0 ) {
                System.out.println("Returning to Main Menu....");
                seeInventoryMenuRunning = false;
            } else {
                System.out.println("Invalid input.");
            }

        }
    }

    public static void addProduct(Scanner sc, ArrayList<Inventory> inventoryArrayList, Inventory inventory){
        boolean addProductMenuRunning = true;
        int option3;
        String productName, productCategory = "";
        double productPrice;

        //User i.e. manager should select from a list of already existing categories, (but manager can add a new Category if they want)
        while(addProductMenuRunning){
            System.out.println("--- Add Product ---\n");

            System.out.print("Enter Product Name: ");
            productName = sc.nextLine();
            System.out.println("Select Product Category: ");
            System.out.println("<1> Dairy");
            System.out.println("<2> Deli/Fast Foods");
            System.out.println("<3> Drinks");
            System.out.println("<4> Hygiene");
            System.out.println("<5> Produce");
            try{
                System.out.print("Select: ");
                int opt = sc.nextInt();
                sc.nextLine();

                switch(opt){
                    case 1: productCategory = "Dairy";
                        break;
                    case 2: productCategory = "Deli/Fast Foods";
                        break;
                    case 3: productCategory = "Drinks";
                        break;
                    case 4: productCategory = "Hygiene";
                        break;
                    case 5: productCategory = "Produce";
                        break;
                    default: System.out.println("INVALID SELECTION! Please Try Again.");
                }

            } catch (InputMismatchException ime) {
                System.out.println("An Error Occurred.");
            }

            System.out.print("Enter Product Price: ");
            productPrice = sc.nextDouble();
            sc.nextLine();
            System.out.println("Successfully Added Product\nProduct Name: "+productName+"\nCategory: "+productCategory+"\tPrice: "+df.format(productPrice));

            inventory.setProductCategory(productCategory);
            inventory.setProductName(productName);
            inventory.setProductPrice(productPrice);
            Inventory newProduct = new Inventory(productName, productCategory, productPrice);
            inventoryArrayList.add(newProduct);

            System.out.println("Add Another Product? ");
            System.out.println("<1> YES");
            System.out.println("<0> NO");
            option3 = sc.nextInt();
            sc.nextLine();

            if( option3 == 1 ) {
                //continue;
                System.out.println();
            }
            else if ( option3 == 0 ) {
                System.out.println("Returning to Main Menu....");
                addProductMenuRunning = false;
            }
        }

    }
    public static void addEmployee(Scanner sc, ArrayList<Employee> employees, Employee employee){
        boolean addEmployeeMenuRunning = true;
        int option4, employeeAge, employeePin, i = 0;
        String employeeName, employeeSurname, employeeUsername;


        while ( addEmployeeMenuRunning ) {

            System.out.println("=== ADD EMPLOYEE ===\n");
            System.out.print("Employee Name: ");
            employeeName = sc.nextLine();
            char cInitial = employeeName.charAt(0);
            System.out.print("Employee Surname: ");
            employeeSurname = sc.nextLine();

            do {
                System.out.print("Employee Age: ");
                employeeAge = sc.nextInt();
                if (employeeAge < 18) {
                    System.out.println("Employee must 18 years or older.");
                } else if ( employeeAge > 65 ) {
                    System.out.println("Unable to hire a Senior citizen.");
                }
            } while (employeeAge < 18);

            sc.nextLine();
            boolean found;
            do {
                found = false;
                System.out.print("Please Set an Employee Pin (e.g. 1234): ");
                employeePin = sc.nextInt();
                for (Employee e : employees) {
                    if (employeePin == e.employeePin) {
                        System.out.println("Pin Already Exists in System `Please Set Unique Pins`");
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("Pin Successfully Set");
                }
            } while (found);

            sc.nextLine();
            employeeUsername = Character.toUpperCase(cInitial) + employeeSurname + employeeAge;

            employee.setEmployeeName(employeeName);
            employee.setEmployeeSurname(employeeSurname);
            employee.setEmployeeAge(employeeAge);
            employee.setEmployeePin(employeePin);
            employee.setEmployeeUsername(employeeUsername);

            Employee newEmployee = new Employee(employeeUsername, employeeName, employeeSurname, employeeAge, employeePin);


            employees.add(newEmployee);

            System.out.println("ADD ANOTHER EMPLOYEE? ");
            System.out.println("<1> YES");
            System.out.println("<0> NO");
            System.out.print("Select: ");
            option4 = sc.nextInt();
            sc.nextLine();

            if (option4 == 1)
                System.out.println();
            else if (option4 == 0) {
                System.out.println("Returning to Main Menu....");
                addEmployeeMenuRunning = false;
            } else
                System.out.println("Invalid Selection! Please Try Again.");

        }
    }
    public static void removeEmployee(Scanner sc,  ArrayList<Employee> employees, Iterator iterator){
        boolean removeEmployeeMenuRunning = true;
        int option5 = 0;

        while( removeEmployeeMenuRunning ){
            System.out.println("=== REMOVE EMPLOYEE ===\n");
            if( employees.isEmpty() ){
                System.out.println("NO EMPLOYEES FOUND.");
            }else {
                System.out.print("Enter Employee Username to Remove: ");
                String employeeUsernameCheck = sc.nextLine();

                while ( iterator.hasNext() ) {
                    Employee emp = (Employee) iterator.next();
                    if ( emp.getEmployeeUsername().equalsIgnoreCase(employeeUsernameCheck) ) {
                        iterator.remove();
                        System.out.println(employeeUsernameCheck+" Successfully Removed.");
                    } else {
                        System.out.println("Username not found.");
                    }
                }

            }
            System.out.println("<1> Remove Again");
            System.out.println("<0> Return to Main Menu");
            System.out.print("Select: ");
            option5 = sc.nextInt();
            sc.nextLine();
            if ( option5 == 0 ) {
                System.out.println("Returning to Main Menu....");
                removeEmployeeMenuRunning = false;
            } else if ( option5 == 1 ) {
                continue;
            } else {
                System.out.println("Invalid input.");
            }
        }
    }
    public static void employeeList ( ArrayList<Employee> employees, Employee newEmployee){

        boolean employeeListMenuRunning = true;

        while (employeeListMenuRunning) {

            if ( employees.isEmpty() ) {
                System.out.println("No Employees in System.");
                break;
            } else {
                System.out.println("\n==== Employees ====\n");
                for ( Employee e : employees ) {
                    System.out.println( e.toString() );
                }

                System.out.println("Returning to Menu...");
                employeeListMenuRunning = false;
            }
        }

    }
}
