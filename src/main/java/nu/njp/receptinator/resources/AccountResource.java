package nu.njp.receptinator.resources;

import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.core.util.ResponseHelper;
import nu.njp.receptinator.entities.Account;
import nu.njp.receptinator.interfaces.AccountServiceLocal;
import nu.njp.receptinator.viewmodels.AccountViewModel;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * Created by Mattias on 2016-01-11.
 */

@Path("accounts")
@Stateless
public class AccountResource {

    @Inject
    AccountServiceLocal accountService;

    @Inject
    ResponseHelper<Account> response;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        Collection<Account> accounts = accountService.allAccounts();
        if(accounts.isEmpty()) {
            return response.getFailed("Could not retrieve account list");
        }
        return response.getOk(new GenericEntity<Collection<Account>>(accounts) {});
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(AccountViewModel accountModel) {

        Account accountEntity = new Account();
        accountEntity.setEmail(accountModel.getEmail());
        accountEntity.setFirstName(accountModel.getFirstName());
        accountEntity.setLastName(accountModel.getLastName());
        accountEntity.setPassword(accountModel.getPassword());
        accountEntity.setUserName(accountModel.getUserName());

        if((accountService.addAccount(accountEntity)).getMessageType().equals(JsfMessage.MessageType.SUCCESS))
            return response.postOk("Account created.");
        else
            return response.postFailed("Failed to create account.");
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id")int id) {
        Account account = accountService.findAccount(id);
        if(account != null) {
            return response.getOk(account);
        }
        return response.getNotFound();
    }

}
