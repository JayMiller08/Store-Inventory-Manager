public class Employee {

    String employeeUsername, employeeName, employeeSurname;
    int employeePin, employeeAge;

    public Employee (String employeeUsername, String employeeName, String employeeSurname, int employeeAge, int employeePin){
        this.employeeUsername = employeeUsername;
        this.employeeName = employeeName;
        this.employeeSurname = employeeSurname;
        this.employeeAge = employeeAge;
        this.employeePin = employeePin;
    }

    public void setEmployeeUsername(String employeeUsername) {
        this.employeeUsername = employeeUsername;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public void setEmployeePin(int employeePin){
        this.employeePin = employeePin;
    }

    public void setEmployeeSurname(String employeeSurname) {
        this.employeeSurname = employeeSurname;
    }

    public void setEmployeeAge(int employeeAge) {
        this.employeeAge = employeeAge;
    }

    public String getEmployeeUsername() {
        return employeeUsername;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmployeeSurname() {
        return employeeSurname;
    }

    public int getEmployeeAge() {
        return employeeAge;
    }

    public int getEmployeePin() {
        return employeePin;
    }
    @Override
    public String toString(){
        return "Username: "+employeeUsername;
    }

    public static void employeeMenu() {
        System.out.println("This Launches Employee Menu.");
    }
}
