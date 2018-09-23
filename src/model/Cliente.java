package model;

public class Cliente {
    private int id;
    private String firstName;
    private String lastName;
    private String homeNumber;
    private String phoneNumber;

    public Cliente(String firstName, String lastName, String homeNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeNumber = homeNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", homeNumber='" + homeNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}