package co.adamcowley.neobeans.domain.services;

import co.adamcowley.neobeans.domain.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

@Service
public class CreatePerson
{

    private final SessionFactory sessionFactory;

    // in the constructor, allows you to set final and control where it is set
    @Autowired
    public CreatePerson(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Person create(String firstName, String lastName) {
        Person output = new Person();

        output.setFirstName( firstName );
        output.setLastName( lastName );

        Session session = sessionFactory.openSession();

        session.save( output );

        return output;
    }


}
