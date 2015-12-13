package nu.njp.receptinator.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by base on 2015-12-13.
 */
@Entity
@Table(name = "accounts")
public class Account implements Serializable {

    public Account() {
        salt = "1234567890";
        status = Status.ACTIVE;
        permission = Permission.USER;
    }

    public enum Status { ACTIVE, DELETED }
    public enum Permission { USER, CONTRIBUTER, ADMINISTRATOR }

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountId")
    private String accountId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 4, max = 50)
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 10, max = 10)
    private String salt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 6, max = 200)
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 5, max = 300)
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid email format")
    private String email;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Permission permission;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
