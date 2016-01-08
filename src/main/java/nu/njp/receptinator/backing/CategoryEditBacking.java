package nu.njp.receptinator.backing;

import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.core.qualifier.DefaultLogger;
import nu.njp.receptinator.entities.Category;
import nu.njp.receptinator.interfaces.CategoryServiceLocal;
import org.slf4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Basic;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Andreas on 2016-01-04.
 */
@ManagedBean
@ViewScoped
public class CategoryEditBacking extends BackingBase {

    @Inject
    @DefaultLogger
    private Logger logger;

    @Inject
    CategoryServiceLocal categoryService;

    private Category category;

    public Category getCategory() {
        if(category == null)
            category = new Category();
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String addNewCategory(){
        JsfMessage result = categoryService.addCategory(category);
        setMessage(result);
        return null;
    }

    public String removeCategory() {
        JsfMessage result = categoryService.removeCategory(category.getCategoryId());
        setMessage(result);
        return null;
    }

    public String editCategory() {
        JsfMessage result = categoryService.updateCategory(category);
        setMessage(result);
        return null;
    }
}
