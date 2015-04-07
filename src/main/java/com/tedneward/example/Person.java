package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  private static int count;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  //Instantiates a Person with given name, age, and salary.
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    count++;
    ssn = "";
  }
  
  //CompareTo method
  public int compareTo(Person other) {
	  if (this.salary - other.salary > 0) {
        return -1;
      }
      if (this.salary - other.salary < 0) {
        return 1;
      }
      return 0;
  }
  
  //Returns an ArrayList<Person> consisting of four Person objects representing the Neward family members.
  public static ArrayList<Person> getNewardFamily() {
	  ArrayList<Person> list = new ArrayList<Person>();
	  list.add(new Person("Ted", 41, 250000.0));
	  list.add(new Person("Charlotte", 43, 150000.0));
	  list.add(new Person("Michael", 22, 10000.0));
	  list.add(new Person("Matthew", 15, 0.0));	
	  return list;
  }
  
  //Returns the number of Person instances created.
  public static int count() {
	  return count;
  }
  
  //A Comparator class that compares two Person instances and arranges them by age 
  static class AgeComparator implements Comparator<Person> {
	  public int compare(Person p1, Person p2) {
		  return p1.getAge() - p2.getAge();
	  }
  }
  

  //Getter for age.
  public int getAge() {
    return age;
  }
  
  //Setter for age.
  public void setAge(int age) {
    if (age < 0) throw new IllegalArgumentException();
    this.age = age;
  }
  
  //Getter for name.
  public String getName() {
    return name;
  }
  
  //Setter for name.
  public void setName(String name) {
    if (name == null) throw new IllegalArgumentException();
    this.name = name;
  }
  
  //Getter for salary.
  public double getSalary() {
    return salary;
  }
  
  //Setter for salary.
  public void setSalary(double salary) {
	  this.salary = salary;
  }
  
  //Getter for SSN.
  public String getSSN() {
    return ssn;
  }
  
  //Setter for SSN.
  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  
  
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  @Override
  public boolean equals(Object object) {
	  if (object == null) return false;
	  if (getClass() != object.getClass()) return false;
	  Person other = (Person) object;
	  return this.name != null && other.name != null &&
			 this.name.equalsIgnoreCase(other.name) && this.age == other.age;
	  
  }

  public String toString() {
    String result = "[Person name:";
    result += name;
    result += " age:";
    result += age;
    result += " salary:";
    result += salary;
    result += "]";
    return result;
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
