import java.util.Scanner;

import exceptions.InvalidDepartmentException;
import exceptions.InvalidUserNameException;
import services.CredentialService;

public class Employee {
    private String firstName;
    private String lastName;
    private String password;
    private int passwordLength = 12;
    private String department;

    public Employee() throws InvalidUserNameException {
        throw new InvalidUserNameException("Invalid UserName!!\n Syntax: Email email = new Email(\"FirstName\",\"LastName\")");
    }

    /// Creates an Email object with the given [First Name] and [Last Name].
    public Employee(String firstName, String lastName) throws InvalidDepartmentException {
        this.firstName = firstName;
        this.lastName = lastName;

        /// Print spaces to make it beautiful in terminal.
        System.out.println("\n\n\n********** WELCOME TO CREDENTIALS MANAGER SERVICE **********\n\n");

        this.department = department();
        this.password = CredentialService.generateStrongPassword(passwordLength);
        String email = CredentialService.generateEmailForUser(firstName, lastName, department);
        /// Print credentials.
        CredentialService.showCredentials(email, password, department, firstName);
    }

    private String department() throws InvalidDepartmentException {
        System.out.print("Please enter the department from the following:\n1 for Admin\n2 for Technical\n3 for Human Resource\n4 for Legal \nEnter department code: ");
		Scanner inputStream = new Scanner(System.in);
        try {
            int departmentChoice = inputStream.nextInt();
            switch (departmentChoice) {
                case 1:
                return "Admin";
                case 2:
                return "Technical";
                case 3:
                return "Human Resource";
                case 4:
                return "Legal";
                default:
                    throw new InvalidDepartmentException("\n\n !!!!! Invalid department code !!!! \n\n");
            }
        }finally{
            inputStream.close();
        }
    }
}
