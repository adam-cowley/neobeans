package co.adamcowley.neobeans.domain.entities;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

import java.math.BigInteger;
import java.util.Date;

@RelationshipEntity( type = "KNOWS" )
public class Knows extends Entity {

    @StartNode
    Person source;

    @EndNode
    Person target;

    @DateLong
    Date since;

    public Knows(Person source, Person target, Date since) {
        this.source = source;
        this.target = target;
        this.since = since;
    }

    /**
     * Set the Since property
     *
     * @param since
     */
    public void setSince(Date since) {
        this.since = since;
    }

    /**
     * Get the since property
     *
     * @return
     */
    public Date getSince() {
        return since;
    }

}
