package sample;

public class Staff extends Employee
{
    public int accessLevel;

    public Staff(String firstName, String lastName, boolean isActive) {
        super(firstName, lastName, isActive);
    }
}
