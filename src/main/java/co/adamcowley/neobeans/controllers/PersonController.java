package co.adamcowley.neobeans.controllers;

import org.neo4j.ogm.cypher.ComparisonOperator;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.cypher.Filters;
import org.neo4j.ogm.cypher.query.Pagination;
import org.neo4j.ogm.cypher.query.PagingAndSorting;
import org.neo4j.ogm.cypher.query.PagingAndSortingQuery;
import org.neo4j.ogm.cypher.query.SortOrder;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import co.adamcowley.neobeans.domain.entities.Person;
import co.adamcowley.neobeans.domain.services.CreatePerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PersonController {

    private final CreatePerson service;

    private final SessionFactory factory;

    @Autowired
    public PersonController(CreatePerson service, SessionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @RequestMapping("/people")
    public Collection<Person> getList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam String lastName,
            @RequestParam(defaultValue = "lastName") String orderBy,
            @RequestParam(defaultValue = "asc") String sort
    ) {
        Session session = factory.openSession();

        Pagination pagination = new Pagination( page-1, limit );

        Filters filters = new Filters();

        if ( lastName != null ) {
            filters.add( new Filter( "lastName", ComparisonOperator.CONTAINING,  lastName) );
        }

        SortOrder.Direction direction = sort == "asc" ? SortOrder.Direction.ASC : SortOrder.Direction.DESC;

        SortOrder order = new SortOrder().add( direction, orderBy );


        Collection<Person> people = session.loadAll(Person.class, filters, order, pagination);

        return people;
    }

    @RequestMapping(value = "/people", method = RequestMethod.POST)
    public Person postList( @RequestBody Map<String, Object> body ) {
        String firstName = (String) body.get("firstName");
        String lastName = (String) body.get("lastName");

        Person output = service.create(firstName, lastName);

        return output;
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public Person getIndex( @PathVariable("id") Long id ) {
        Session session = factory.openSession();

        return session.load( Person.class, id );
    }

}
