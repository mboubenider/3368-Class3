package sample;

import java.util.UUID;

public class Employee implements Worker
{
    public String firstName;
    public String lastName;
    public boolean isActive;
    public  UUID id;

    public Employee(String firstName, String lastName, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
    }

    @Override
    public String toString()
    {
        return this.firstName + " " + this.lastName + " "+ this.isActive;
    }


    @Override
    public void hire()
    {
        isActive = true;
    }

    @Override
    public void fire()
    {
        isActive = false;
    }
}
