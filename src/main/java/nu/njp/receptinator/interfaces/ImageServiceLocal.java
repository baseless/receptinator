package nu.njp.receptinator.interfaces;

import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.entities.Image;

import java.io.Serializable;

/**
 * Created by Andreas on 2015-12-21.
 */
public interface ImageServiceLocal extends Serializable {

    JsfMessage addImage(Image image);

    JsfMessage updateImage(Image image);

    JsfMessage removeImage(int imageId);
}
