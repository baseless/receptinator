package nu.njp.receptinator.resources;

import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.core.util.ResponseHelper;
import nu.njp.receptinator.entities.Account;
import nu.njp.receptinator.entities.Category;
import nu.njp.receptinator.interfaces.CategoryServiceLocal;
import nu.njp.receptinator.viewmodels.AccountViewModel;
import nu.njp.receptinator.viewmodels.CategoryViewModel;

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

@Path("categories")
@Stateless
public class CategoryResource {

    @Inject
    CategoryServiceLocal categoryService;

    @Inject
    ResponseHelper<Category> response;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        Collection<Category> categories = categoryService.allCategories();
        if(categories.isEmpty()) {
            return response.getFailed("Could not retrieve category list");
        }
        return response.getOk(new GenericEntity<Collection<Category>>(categories) {});
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(CategoryViewModel categoryModel) {

        Category categoryEntity = new Category();
        categoryEntity.setCategoryName(categoryModel.getCategoryName());

        if((categoryService.addCategory(categoryEntity)).getMessageType().equals(JsfMessage.MessageType.SUCCESS))
            return response.postOk("Category created.");
        else
            return response.postFailed("Failed to create category.");
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id")int id) {
        Category category = categoryService.findCategory(id);
        if(category != null) {
            return response.getOk(category);
        }
        return response.getNotFound();
    }
}
