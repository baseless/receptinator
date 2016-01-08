package nu.njp.receptinator.backing;

import nu.njp.receptinator.entities.Category;
import nu.njp.receptinator.interfaces.CategoryServiceLocal;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

/**
 * Created by Andreas on 2016-01-04.
 */

@ManagedBean
@ViewScoped
public class CategoryListBacking {

    @Inject
    CategoryServiceLocal categoryService;

    private Collection<Category> categoryList;

    public Collection<Category> getCategoryList() {
        if(categoryList == null) {
            categoryList = categoryService.allCategories();
        }
        return categoryList;
    }

    public void setCategoryList(Collection<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
