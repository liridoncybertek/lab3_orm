package com.cybertek.lab3_orm.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
@Table(name = "categories")
public class Category extends Model{

    private String name;

    private String description;
}
