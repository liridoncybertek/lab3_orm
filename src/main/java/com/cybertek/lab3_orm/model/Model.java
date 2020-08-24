package com.cybertek.lab3_orm.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@Getter
@Setter
@MappedSuperclass
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
}
