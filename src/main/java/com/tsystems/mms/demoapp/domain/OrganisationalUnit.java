package com.tsystems.mms.demoapp.domain;


import com.sun.istack.NotNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "organisational_unit")
@SQLDelete(sql = "UPDATE organisational unit SET organisational_unit_deleted = true where id=? ")
@Where(clause = "organisational_unit_deleted=false")
public class OrganisationalUnit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "organisational_unit_deleted")
    private Boolean deleted = false;

    @OneToMany(mappedBy = "organisationalUnit")
    private List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
