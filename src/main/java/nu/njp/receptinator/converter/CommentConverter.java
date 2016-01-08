package nu.njp.receptinator.converter;

import nu.njp.receptinator.entities.Account;
import nu.njp.receptinator.entities.Comment;
import nu.njp.receptinator.interfaces.AccountServiceLocal;
import nu.njp.receptinator.interfaces.CommentServiceLocal;
import nu.njp.receptinator.services.CommentService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;

/**
 * AccountConverter bean
 * @author Daniel Ryhle <daniel@ryhle.se>
 */
@ManagedBean
@ViewScoped
public class CommentConverter implements Converter {

    @Inject
    CommentServiceLocal commentService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null || s.isEmpty()) { return null; }

        try {
            int id = Integer.valueOf(s);
            return commentService.findComment(id);
        } catch (NumberFormatException e) { throw new ConverterException("Not a valid Comment ID: " + s, e); }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null) { return ""; }

        if (o instanceof Comment) {
            int id = ((Comment)o).getCommentId();
            return (id != 0) ? String.valueOf(id) : null;
        } else { throw new ConverterException("Id not corresponding to an Comment instance: " + o); }
    }
}
