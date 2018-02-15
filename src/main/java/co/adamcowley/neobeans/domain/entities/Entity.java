package co.adamcowley.neobeans.domain.entities;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Entity {

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

}
