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

    @Basic(optional = true)
    @Size(min = 1, max = 200)
    private String categoryName;

    private Category category;
    private int categoryId;
    private Collection<Category> categoryList;


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category getCategory() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        categoryId = Integer.parseInt(params.get("id"));

        if(category == null)
            category = categoryService.findCategory(categoryId);
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Collection<Category> getCategoryList() {
        categoryList = categoryService.allCategories();
        return categoryList;
    }

    public void setCategoryList(Collection<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public JsfMessage addNewCategory(){
        System.out.println("--------------------------------");
        System.out.println(category.getCategoryId());
        System.out.println(category.getCategoryName());
        System.out.println("----------------------------------");
        if(category != null)
         categoryService.updateCategory(category);
        return new JsfMessage("Category Updated!", "Category was successfully updated", JsfMessage.MessageType.SUCCESS);
    }
}
