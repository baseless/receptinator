package nu.njp.receptinator.core.pojo;

/**
 * Pojo class for managing JSF navbar menu items
 * @author Daniel Ryhle <daniel@ryhle.se>
 */
public class JsfMenuItem {
    private boolean active;
    private String name;
    private String path;

    public JsfMenuItem(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getClassParameter() {
        if(active) {
            return "active";
        }
        return null;
    }
}
