package com.Mainproject.Todolist.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TodoList {
    @Id
    private long contentid;
    private String title;
    private String description;
    private String date;
    private String time;
    private String url;
    private String status;
    private String sendby;
    private String priority;

}
