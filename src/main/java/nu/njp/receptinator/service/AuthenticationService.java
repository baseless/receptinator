package nu.njp.receptinator.service;

import javax.ejb.Stateless;

public interface AuthenticationService {
    String authenticate(String userName, String password);
}
