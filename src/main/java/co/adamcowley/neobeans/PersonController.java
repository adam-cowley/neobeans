package co.adamcowley.neobeans;

import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PersonController {

    @Autowired
    Driver driver;

    @RequestMapping("/people")

    public List<Map<String, Object>> getIndex(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit) {
        try ( Session session = driver.session() ) {
            Number skip = (page - 1) * limit;

            String query = "MATCH (p:Person) RETURN p ORDER BY p.name SKIP {skip} LIMIT {limit}";
            Map<String, Object> params = new HashMap<String, Object>() {{
                put("limit", limit);
                put("skip", skip);
            }};

            return session.readTransaction(tx -> {
               return tx.run(query, params)
                       .list( row -> row.get("p").asMap() );
            });
        }
    }

}
