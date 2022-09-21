package com.tsystems.mms.demoapp.user;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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

  @Column(name = "email", nullable = false)
  private String email;



  @Column(name ="password", nullable = false)
  private String password;

  @Column(name = "user_is_deleted")
  private Boolean deleted = false;

  public User() {
  }



  public User(UserDTO userDTO) {
    this.email = userDTO.getEmail();
    this.password = userDTO.getPassword();
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
