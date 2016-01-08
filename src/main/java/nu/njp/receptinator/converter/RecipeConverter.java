package nu.njp.receptinator.converter;

import nu.njp.receptinator.entities.Recipe;
import nu.njp.receptinator.interfaces.RecipeServiceLocal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;

/**
 * RecipeConverter bean
 * @author Daniel Ryhle <daniel@ryhle.se>
 */
@ManagedBean
@ViewScoped
public class RecipeConverter implements Converter {

    @Inject
    RecipeServiceLocal recipeService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null || s.isEmpty()) { return null; }

        try {
            int id = Integer.valueOf(s);
            return recipeService.findRecipe(id);
        } catch (NumberFormatException e) { throw new ConverterException("Not a valid Recipe ID: " + s, e); }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null) { return ""; }

        if (o instanceof Recipe) {
            int id = ((Recipe)o).getRecipeId();
            return (id != 0) ? String.valueOf(id) : null;
        } else { throw new ConverterException("Id not corresponding to an Recipe instance: " + o); }
    }

}
