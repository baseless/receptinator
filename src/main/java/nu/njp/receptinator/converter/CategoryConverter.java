package nu.njp.receptinator.converter;

import nu.njp.receptinator.entities.Category;
import nu.njp.receptinator.interfaces.CategoryServiceLocal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;

/**
 * Created by Andreas on 2016-01-09.
 */
@ManagedBean
@ViewScoped
public class CategoryConverter implements Converter {

    @Inject
    CategoryServiceLocal categoryService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null || s.isEmpty()) { return null; }

        try {
            int id = Integer.valueOf(s);
            return categoryService.findCategory(id);
        } catch (NumberFormatException e) { throw new ConverterException("Not a valid Category ID: " + s, e); }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null) { return ""; }

        if (o instanceof Category) {
            int id = ((Category)o).getCategoryId();
            return (id != 0) ? String.valueOf(id) : null;
        } else { throw new ConverterException("Id not corresponding to an Category instance: " + o); }
    }
}
