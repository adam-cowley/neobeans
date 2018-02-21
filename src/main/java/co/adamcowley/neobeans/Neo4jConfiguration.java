package co.adamcowley.neobeans;

import org.neo4j.driver.v1.AuthToken;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.ogm.session.SessionFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Neo4jConfiguration {

    @Value("${neo4j.scheme}")
    private String scheme;

    @Value("${neo4j.host}")
    private String host;

    @Value("${neo4j.port}")
    private String port;

    @Value("${neo4j.policy:#{null}}")
    private String routingPolicy;

    @Value("${neo4j.auth.type:basic}")
    private String authType;

    @Value("${neo4j.auth.username:#{null}}")
    private String username;

    @Value("${neo4j.auth.password:#{null}}")
    private String password;

    @Value("${neo4j.auth.ticket:#{null}}")
    private String ticket;

    /**
     * Create a new Driver instance
     *
     * @return Driver
     */
    @Bean
    public Driver neo4jDriver() {
        String uri = getUri();
        AuthToken token = getAuthToken();

        return GraphDatabase.driver(uri, token);
    }

    /**
     * Get the URI for the Neo4j Server
     * @return String
     */
    public String getUri() {
        // Get the base URI (ie bolt://localhost:7474)
        String uri = String.format("%s://%s:%s", scheme, host, port);

        // If there is a CC routing policy, append it to the end of the string
        if ( scheme == "bolt+routing" && routingPolicy != null ) {
            uri += "?policy="+ routingPolicy;
        }

        return uri;
    }

    /**
     * Use the neo4j.auth.type property to create an appropriate Auth Token
     *
     * @return AuthToken
     */
    public AuthToken getAuthToken() {
        switch ( authType ) {
            case "basic":
                return AuthTokens.basic(username, password);
            case "kerberos":
                return AuthTokens.kerberos(ticket);
            default:
                return AuthTokens.none();
        }
    }

    /**
     * Generate a Configuration Object
     *
     * @return org.neo4j.ogm.config.Configuration
     */
    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        return new org.neo4j.ogm.config.Configuration.Builder()
                .uri( getUri() )
                .credentials( username, password )
                .build();
    }

    /**
     * Method for creating a Session Factory
     *
     * @param config  Configuration object created above.
     * @return SessionFactory
     */
    @Bean
    public SessionFactory getSessionFactory(Neo4jConfiguration config) {
        return new SessionFactory( config.getConfiguration(), "co.adamcowley.neobeans" );
    }


}
