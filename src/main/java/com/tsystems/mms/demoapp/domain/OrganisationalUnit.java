package com.tsystems.mms.demoapp.domain;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organisational_unit")
@SQLDelete(sql = "UPDATE organisational unit SET organisational_unit_deleted = true where id=? ")
@Where(clause = "organisational_unit_deleted=false")
@Getter
@Setter
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
    private List<User> users = new ArrayList<>();


}
