package models.employee;

import java.util.Calendar;

import static java.util.Calendar.*;

public class Person {
    private String name;
    private Calendar date_of_birth;
    private String email;
    private String phone_number;

    {
        this.date_of_birth = Calendar.getInstance();
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(int year, int month, int day) {
        this.date_of_birth.set(year, month-1, day);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", date_of_birth=" + date_of_birth +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                '}';
    }

    //~~~Other methods:

    static public Integer yearsBetween (Calendar firstDate, Calendar secondDate) {
        return Math.abs(firstDate.get(YEAR)-secondDate.get(YEAR));
    }

    public Integer getAge () {
        Calendar currentDay = Calendar.getInstance();
        if(date_of_birth.get(MONTH)<currentDay.get(MONTH))
            return yearsBetween(date_of_birth, currentDay);
        if(date_of_birth.get(MONTH)>currentDay.get(MONTH))
            return yearsBetween(date_of_birth, currentDay)-1;
        if(date_of_birth.get(DATE)<=currentDay.get(DATE))
            return yearsBetween(date_of_birth, currentDay);
        return yearsBetween(date_of_birth, currentDay)-1;
    }

}
