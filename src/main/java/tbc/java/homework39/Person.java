package tbc.java.homework39;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class Person {
    String id;
    String firstName;
    String lastName;
    int age;

    public Person (){}
    public Person (String id, String firstName, String lastName, int age){
        this.id = id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
    }

    public String getId() {
        return id;
    }

    @XmlAttribute(name="id")
    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    @XmlElement (name="first-name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @XmlElement (name="last-name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    @XmlElement (name="age")
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString(){
        return "id = " + id+"; "+
                "name = " + firstName+"; "+
                "last name = " + lastName+"; "+
                "age = " + age+"; ";
    }
}
