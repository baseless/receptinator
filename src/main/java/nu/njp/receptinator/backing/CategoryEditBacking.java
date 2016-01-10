package nu.njp.receptinator.backing;

import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.core.qualifier.DefaultLogger;
import nu.njp.receptinator.entities.Category;
import nu.njp.receptinator.interfaces.CategoryServiceLocal;
import org.slf4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

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


    private Category category = new Category();

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
        if(!result.getMessageType().equals(JsfMessage.MessageType.SUCCESS)) {
            setMessage(result);
            return null;
        } else {
            return "list";
        }
    }

    public String removeCategory() {
        System.out.println("------------DELETE CATEGORY--------------");
        JsfMessage result = categoryService.removeCategory(getCategory().getCategoryId());
        if(!result.getMessageType().equals(JsfMessage.MessageType.SUCCESS)) {
            setMessage(result);
            return null;
        } else {
            return "list";
        }
    }

    public String editCategory() {
        JsfMessage result = categoryService.updateCategory(category);
        setMessage(result);
        if(!result.getMessageType().equals(JsfMessage.MessageType.SUCCESS)) {
            setMessage(result);
            return null;
        } else {
            return "list";
        }
    }

}
