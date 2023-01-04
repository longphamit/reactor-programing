package com.longpc.reactorprograming.dtos;

import lombok.Data;

@Data
public class Person {
    private String name;
    private String school;

    public Person(String name, String school) {
        this.name = name;
        this.school = school;
    }
}
