package tbc.java.homework39;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import javax.jws.soap.SOAPBinding.Style;
import java.util.List;

@WebService
    @SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
    public interface PersonServicesWS {

        @WebMethod(operationName = "GetPerson")
        @WebResult(name = "GetPersonResult")
        Person getPerson(@WebParam(name = "PersonId") int id) throws PersonNotFound, GeneralError;

        @WebMethod(operationName = "AddPerson")
        @RequestWrapper(localName = "AddPersonRequest", partName = "AddPersonRequest", className = "tbc.java.homework39.Person")
        @ResponseWrapper(localName = "AddPersonResponse", partName = "AddPersonResponse", className = "tbc.java.homework39.Person")
        boolean addPerson(@WebParam(name = "ID", mode = Mode.IN) int id,
                         @WebParam(name = "FirstName", mode = Mode.IN) Person person) throws PersonAlreadyExists, GeneralError;

        @WebMethod(operationName = "UpdatePerson")
        @RequestWrapper(localName = "UpdatePerson", className = "tbc.java.homework39.Person")
        @WebResult(name = "UpdatePersonResult")
        boolean updatePerson(@WebParam(name = "ID") int id, @WebParam(name = "FirstName") Person person)
                throws PersonNotFound, GeneralError;

        @WebMethod(operationName = "DeletePerson")
        @WebResult(name = "DeletePersonResult")
        boolean deletePerson(@WebParam(name = "ID") int id) throws PersonNotFound, GeneralError;


        @WebMethod(operationName = "ListPersons")
        @WebResult(name = "ListPersonsResult")
        List<Person> listPersons() throws GeneralError;
    }

