package com.tsystems.mms.demoapp.domain;

import com.sun.istack.NotNull;
import com.tsystems.mms.demoapp.domain.enums.Gender;
import com.tsystems.mms.demoapp.dto.UserDetails;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "demo_user")
@SQLDelete(sql = "UPDATE demo_user SET user_is_deleted = true where id=? ")
@Where(clause = "user_is_deleted=false")
public class User implements Serializable {

  private static final long serialVersionUID = 1715994813284718249L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  protected Long id;

  @Column(name = "user_first_name")
  @NotNull
  private String firstName;

  @Column(name = "user_last_name")
  @NotNull
  private String lastName;

  @Column(name = "email", nullable = false)
  private String email;

  @Enumerated(EnumType.STRING)
  @Column(name = "gender")
  @NotNull
  private Gender gender;

  @Column(name ="password", nullable = false)
  private String password;

  @Column(name = "user_is_deleted")
  private Boolean deleted = false;

  @ManyToOne
  @JoinColumn(name = "unit_id")
  public OrganisationalUnit organisationalUnit;

  public User() {
  }

  public User(Long id, String firstName, String lastName, String email, Gender gender, String password, OrganisationalUnit organisationalUnit) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.gender = gender;
    this.password = password;
    this.organisationalUnit = organisationalUnit;
  }

  public User(UserDetails userDetails) {
    this.email = userDetails.getEmail();
    this.password = userDetails.getPassword();
    this.firstName = userDetails.getFirstName();
    this.lastName = userDetails.getLastName();
    this.gender = Gender.valueOf(userDetails.getGender());
  }

  public OrganisationalUnit getOrganisationalUnit() {
    return organisationalUnit;
  }

  public void setOrganisationalUnit(OrganisationalUnit organisationalUnit) {
    this.organisationalUnit = organisationalUnit;
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

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Boolean getDeleted() {
    return deleted;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }
}
