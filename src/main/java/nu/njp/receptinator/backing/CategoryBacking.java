package nu.njp.receptinator.backing;

import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.core.qualifier.DefaultLogger;
import nu.njp.receptinator.entities.Category;
import nu.njp.receptinator.interfaces.CategoryServiceLocal;
import org.slf4j.Logger;

import javax.enterprise.context.RequestScoped;
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
@Named("categoryEdit")
@RequestScoped
public class CategoryBacking extends BackingBase {

    @Inject
    @DefaultLogger
    private Logger logger;

    @Inject
    CategoryServiceLocal categoryService;

    private Category category;
    private Collection<Category> categoryList;



    public Category getCategory() {/*
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        categoryId = Integer.parseInt(params.get("id"));
        */
        if(category == null)
            category = new Category();
            //category = categoryService.findCategory(categoryId);
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Collection<Category> getCategoryList() {
        categoryList = categoryService.allCategories();
        return categoryList;
    }

    public void setCategoryList(Collection<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public String addNewCategory(){

        JsfMessage result = categoryService.addCategory(category);
        setMessage(result);
        return null;
        /*
        if(category != null)
            categoryService.addCategory(category);
        return new JsfMessage("Category created!", "Category was successfully created", JsfMessage.MessageType.SUCCESS);
        */
    }
}
