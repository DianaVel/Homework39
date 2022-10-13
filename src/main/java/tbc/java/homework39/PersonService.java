package tbc.java.homework39;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebService(endpointInterface = "tbc.java.homework39.PersonServicesWS")
public class PersonService implements PersonServicesWS{
    Logger logger = LogManager.getLogger();
    Gson gson = new Gson();
    @Override
    public Person getPerson(int id) throws PersonNotFound, GeneralError {

        try {
            logger.info("Method getPerson; Passed id "+id);
            Setting setting = ConfManager.getConfiguration().getSetting();
            JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Persons persons =(Persons) unmarshaller.unmarshal(new File(setting.personsAdd));
            List <Person> person = persons.getPersonList().stream().filter(x->x.id.equals(id)).collect(Collectors.toList());
            if(person.size()>0) {
                logger.info("Returned object  - "+gson.toJson(person));
                return person.get(0);
            }else {
                logger.error("Person not found");
                throw new PersonNotFound();
            }
        } catch (IOException | JAXBException e){
            logger.error(e.getMessage());
            throw new GeneralError();
        }
    }

    @Override
    public boolean addPerson(int id, Person person) throws PersonAlreadyExists, GeneralError {
        logger.info("Method - addPerson; Passed object - "+gson.toJson(person));
        try {
            Setting setting = ConfManager.getConfiguration().getSetting();

            JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Persons persons =(Persons) unmarshaller.unmarshal(new File(setting.personsAdd));

            List <Person> personList = persons.getPersonList();
            List <String>  ids = personList.stream().map(x->x.id).collect(Collectors.toList());
            if(ids.contains(person.id)){
                logger.error("Person already exists ");
                throw new PersonAlreadyExists();
            }else{
                personList.add(person);
                persons.setPersonList(personList);
                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.marshal(persons,new File(setting.getPersonsAdd()));
                logger.info("Person added ");
                return true;
            }
        }  catch (IOException | JAXBException e){
            logger.error(e.getMessage());
            throw new GeneralError();
        }
    }

    @Override
    public boolean updatePerson(int id, Person person) throws PersonNotFound, GeneralError {
        try {
            logger.info("Method - updatePerson ; Passed argument - "+gson.toJson(person));
            Setting setting = ConfManager.getConfiguration().getSetting();
            JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Persons persons =(Persons) unmarshaller.unmarshal(new File(setting.getPersonsAdd()));
            List <Person> personList = persons.getPersonList();
            boolean contains = false;
            Person toUpdate=null;
            for(Person temp : personList){
                if(temp.id.equals(person.id)) {
                    toUpdate = temp;
                    contains =true;
                    break;
                }
            }
            if(contains){
                toUpdate.age= person.age;
                toUpdate.lastName=person.lastName;
                toUpdate.firstName=person.firstName;
                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.marshal(persons,new File(setting.personsAdd));
                logger.info("Person updated - "+gson.toJson(person));
                return true;
            }else{
                logger.error("Couldn't find indicated person - "+gson.toJson(person));
                throw new PersonNotFound();
            }

        }  catch (IOException | JAXBException e){
            logger.error(e.getMessage());
            throw new GeneralError();
        }
    }

    @Override
    public boolean deletePerson(int id) throws PersonNotFound, GeneralError {
        try {
            logger.info("Method - deletePerson; Passed argument - "+id);
            Setting setting = ConfManager.getConfiguration().getSetting();

            JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Persons persons =(Persons) unmarshaller.unmarshal(new File(setting.getPersonsAdd()));

            List <Person> personList = persons.getPersonList();
            boolean contains = false;
            Person toDelete=null;
            for(Person temp : personList){
                if(temp.id.equals(id+"")) {
                    toDelete = temp;
                    contains =true;
                    break;
                }
            }
            if(contains){
                personList.remove(toDelete);
                persons.setPersonList(personList);
                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.marshal(persons,new File(setting.getPersonsAdd()));
                logger.info("Person deleted - "+id);
                return true;
            }else{
                logger.error("Couldn't find indicated person - "+id);
                throw new PersonNotFound();
            }

        }  catch (IOException | JAXBException e){
            logger.error(e.getMessage());
            throw new GeneralError();
        }
    }

    @Override
    public List<Person> listPersons() throws GeneralError {
        try {
            logger.info("Method - listPersons; ");
            Setting setting = ConfManager.getConfiguration().getSetting();

            JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Persons persons =(Persons) unmarshaller.unmarshal(new File(setting.getPersonsAdd()));
            logger.info("Returned value "+gson.toJson(persons));
            return persons.getPersonList();
        }  catch (IOException | JAXBException e){
            logger.error(e.getMessage());
            throw new GeneralError();
        }
    }
}
