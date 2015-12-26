package nu.njp.receptinator.interfaces;

import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.entities.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Andreas on 2015-12-21.
 */

public interface CategoryServiceLocal extends Serializable {

    JsfMessage addCategory(Category category);

    Collection<Category> allCategories();

    JsfMessage updateCategory(Category category);

    JsfMessage removeCategory(int categoryId);

    Category findCategory(int categoryId);

}
