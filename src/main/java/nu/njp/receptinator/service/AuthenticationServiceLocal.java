package nu.njp.receptinator.service;

/**
 * Authentication service interface
 * @author Daniel Ryhle <daniel@ryhle.se>
 */
public interface AuthenticationServiceLocal {
    String authenticate(String userName, String password);
}
