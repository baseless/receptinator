package nu.njp.receptinator.service;

/**
 * Authentication service interface
 * @author Daniel Ryhle <daniel@ryhle.se>
 */
public interface AuthenticationService {
    String authenticate(String userName, String password);
}
