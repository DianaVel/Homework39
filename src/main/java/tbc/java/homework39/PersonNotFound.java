package tbc.java.homework39;

import javax.xml.ws.WebFault;

@WebFault
public class PersonNotFound extends Exception {

    private static final long serialVersionUID = 1L;

    public PersonNotFound() {
        super("The specified Person does not exist");
    }

    public PersonNotFound(String str) {
        super(str);
    }

}
