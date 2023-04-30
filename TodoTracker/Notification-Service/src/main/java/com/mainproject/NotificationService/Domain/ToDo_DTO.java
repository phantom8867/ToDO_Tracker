package com.mainproject.NotificationService.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class ToDo_DTO {
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
