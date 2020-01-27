package api.config;

/**
 * Classe de configuracao para a seguranca do projeto
 * 
 * @author Paulo Hainosz
 *
 */
//@DatabaseIdentityStoreDefinition(dataSourceLookup = "java:/base", callerQuery = "SELECT password FROM user WHERE username = ?", groupsQuery = "SELECT role FROM user_roles where username = ?", hashAlgorithm = Pbkdf2PasswordHash.class)
//@BasicAuthenticationMechanismDefinition
//@DeclareRoles({ "USER", "ADMIN" })
//@ApplicationScoped	
public class AppSecurityConfig {
}
