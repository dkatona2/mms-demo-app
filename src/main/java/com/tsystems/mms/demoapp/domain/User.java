package com.tsystems.mms.demoapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import com.tsystems.mms.demoapp.domain.enums.Gender;
import com.tsystems.mms.demoapp.dto.UserDetails;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "demo_user")
@SQLDelete(sql = "UPDATE demo_user SET user_is_deleted = true where id=? ")
@Where(clause = "user_is_deleted=false")
@Getter
@Setter
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
  @JsonIgnore
  private Boolean deleted = false;

  @ManyToOne
  @JoinColumn(name = "unit_id")
  @JsonIgnore
  public OrganisationalUnit organisationalUnit;

  public User() {
  }

  public User(Long id, String firstName, String lastName, String email, Gender gender, String password) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.gender = gender;
    this.password = password;
  }

  public User(UserDetails userDetails) {
    this.email = userDetails.getEmail();
    this.password = userDetails.getPassword();
    this.firstName = userDetails.getFirstName();
    this.lastName = userDetails.getLastName();
    this.gender = Gender.valueOf(userDetails.getGender());
  }


}
