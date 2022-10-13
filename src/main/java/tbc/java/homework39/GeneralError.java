package tbc.java.homework39;

import javax.xml.ws.WebFault;

@WebFault
public class GeneralError extends Exception{
    private static final long serialVersionUID = 1L;

    public GeneralError() {
        super("General Error");
    }

    public GeneralError(String str) {
        super(str);
    }
}
