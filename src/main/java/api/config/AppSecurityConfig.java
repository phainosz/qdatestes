package api.config;

//import javax.annotation.security.DeclareRoles;
//import javax.enterprise.context.ApplicationScoped;
//import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
//import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
//import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 * Classe de configuracao para a seguranca do projeto
 * @author Paulo Hainosz
 *
 */
//@DatabaseIdentityStoreDefinition(dataSourceLookup = "java:/base", callerQuery = "SELECT password FROM user WHERE username = ?", groupsQuery = "SELECT role FROM user_roles where username = ?", hashAlgorithm = Pbkdf2PasswordHash.class)
//@BasicAuthenticationMechanismDefinition
//@DeclareRoles({ "USER", "ADMIN" })
//@ApplicationScoped
public class AppSecurityConfig {
}
