package tbc.java.homework39;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Persons {
    private List <Person> personList;
    public  Persons (){ }

    @XmlElement (name="person")
    public void setPersonList (List <Person> personList){
        this.personList=personList;
    }
    public List <Person> getPersonList(){
        return personList;
    }

}
