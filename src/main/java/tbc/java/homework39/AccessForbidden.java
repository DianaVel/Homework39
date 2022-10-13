package tbc.java.homework39;

import javax.xml.ws.WebFault;

@WebFault
public class AccessForbidden extends Exception{
    private static final long serialVersionUID = 1L;

    public AccessForbidden() {
        super("The specified IP address is forbidden");
    }

    public AccessForbidden(String str) {
        super(str);
    }
}
