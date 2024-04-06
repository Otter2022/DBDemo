package edu.utsa.cs3443.testdbhelper.Model;

public class CustomerModel {

    private String name;
    private int age;
    private int id;
    private boolean isActive;

    public CustomerModel(String name, int age, int id, boolean isActive) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.isActive = isActive;
    }

    public CustomerModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getage() {
        return age;
    }

    public void setage(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", id=" + id +
                ", isActive=" + isActive +
                '}';
    }
}
