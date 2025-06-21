import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main{
    static String employeeUsername = "", employeeName = "", employeeSurname = "";
    static int employeePin = 0, employeeAge = 0;
    static Employee employee = new Employee(employeeUsername, employeeName, employeeSurname, employeeAge, employeePin);
    static ArrayList<Employee> employees = new ArrayList<>();

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        ArrayList<Manager> managers = new ArrayList<>();
        Iterator<Employee> iterator = employees.iterator();

        String managerUsername = "314186";
        int managerPin = 2356;

        Manager manager = new Manager(managerUsername, managerPin);
        manager.setManagerPin(managerPin);
        manager.setManagerUsername(managerUsername);

        managers.addFirst(manager);
        boolean mainMenuRunning = true;

        while(mainMenuRunning){
            int option;
            System.out.println("=== MAIN MENU ===");
            System.out.println("<1> Manager Login");
            System.out.println("<2> Employee Login");
            System.out.println("<3> Promotions");
            System.out.println("<0> Exit");
            System.out.print("Select: ");
            option = sc.nextInt();
            sc.nextLine();

            switch( option ){
                case 1: managerLoginMenu(sc, managers, manager, iterator, employees);
                    break;
                case 2: employeeLoginMenu(sc, employees);
                    break;
                case 3: promosMenu();
                    break;
                case 0: System.out.println("Goodbye : )");
                    mainMenuRunning = false;
                    break;
                default: System.out.println("Incorrect Selection.");

            }
        }
    }

    public static void managerLoginMenu(Scanner sc, ArrayList<Manager> managers, Manager manager, Iterator iterator, ArrayList<Employee> employees) {
        boolean managerLoginMenuRunning = true;
        int option_2 = 0, attempts = 5 ,iTry = 0;

        while (managerLoginMenuRunning) {
            System.out.println("Manager Login\n");
            System.out.print("Username/Manager number: ");
            String managerUsernameLogin = sc.nextLine();
            System.out.print("Manager Pin: ");
            int managerPinLogin = sc.nextInt();
            sc.nextLine();

            boolean found = false;

            for ( Manager i : managers ) {
                if ( manager.getManagerUsername().equalsIgnoreCase(managerUsernameLogin) && manager.getManagerPin() == managerPinLogin ) {
                    System.out.println("Manager Access Granted");
                    System.out.println("Manager: " + managers.indexOf(i) + 1 );
                    found = true;
                    //after granting access to the manager, call the manager method/function
                    Manager.managerMenu(sc, employees, iterator, employee);
                    iTry++;
                }
                else if ( attempts == 0 ) {
                    System.out.println("Attempt limit reached, try again later!\nReturning to Menu....");
                    managerLoginMenuRunning = false;
                }

                else {
                    attempts--;
                    System.out.println("Access Denied.");
                }
            }
            if (!found) {
                System.out.println("No Managers Found. Error!\nReturning to Menu....");
                managerLoginMenuRunning = false;
            }
            if ( found && iTry == 1 ) {
                System.out.println("Returning to Menu....");
                managerLoginMenuRunning = false;
            }
        }
    }

    public static void employeeLoginMenu( Scanner sc, ArrayList<Employee> employees ){

        boolean employeeLoginMenuRunning = true;
        int option_3 = 0;

        while ( employeeLoginMenuRunning ) {
            System.out.println("Employee Login\n");
            System.out.print("Employee Username: ");
            String employeeUsernameLogin = sc.nextLine();
            System.out.print("Employee PIN: ");
            int employeePinLogin = sc.nextInt();


            boolean found = false;

            for ( Employee e : employees ) {
                if ( employeeUsernameLogin.equalsIgnoreCase(employee.getEmployeeUsername()) && employeePinLogin == employee.getEmployeePin() ) {
                    System.out.println("Access Granted!");
                    Employee.employeeMenu();
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No Employees Found. \nReturning to Menu....");
                employeeLoginMenuRunning = false;
            }
            sc.nextLine();
        }
    }

    public static void promosMenu(){

    }
}

