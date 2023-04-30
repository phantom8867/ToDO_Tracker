package com.Mainproject.Todolist.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Comparator;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class Userinfo implements Comparator<Users> {

    private String email ;


    private String firstname;

    private String profileimg;


    @Override
    public int compare(Users o1, Users o2) {
        return 0;
    }
}
