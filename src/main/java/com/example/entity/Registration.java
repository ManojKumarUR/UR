package com.example.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class Registration {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;


  @Column(name = "name")
  private String name;

  @Column
  private String password;

  @Column
  private String phoneNumber;
}
