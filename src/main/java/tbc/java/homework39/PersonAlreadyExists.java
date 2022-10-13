package tbc.java.homework39;

import javax.xml.ws.WebFault;

@WebFault
public class PersonAlreadyExists extends Exception{
    private static final long serialVersionUID = 1L;

    public PersonAlreadyExists() {
        super("The specified Person already exist");
    }

    public PersonAlreadyExists(String str) {
        super(str);
    }
}
