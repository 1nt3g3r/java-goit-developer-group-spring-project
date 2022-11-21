package com.goit.devgroup.spring.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "\"user\"")
@Entity
@Data
public class User {
    @Id
    private String id;

    private String name;
}
