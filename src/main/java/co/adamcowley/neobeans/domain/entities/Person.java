package co.adamcowley.neobeans.domain.entities;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@NodeEntity
public class Person extends Entity {

    private String firstName;

    private String lastName;

    @Relationship( type = "KNOWS" )
    Set<Knows> knows;

    /**
     * Setter method to set the first name
     * @param firstName
     * @return void
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter method to get the first name
     *
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }


    /**
     * Setter method to set the first name
     * @param lastName
     * @return void
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter method to get the first name
     *
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

}
