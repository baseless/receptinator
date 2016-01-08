package nu.njp.receptinator.entities;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

/**
 * Created by Mattias on 2016-01-08.
 */

public abstract class BaseEntity {

    public enum Status { ACTIVE, INACTIVE };

}
