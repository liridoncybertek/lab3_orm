package com.cybertek.lab3_orm.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "category")
public class Category extends Model{

    private String name;

    private String description;
}
