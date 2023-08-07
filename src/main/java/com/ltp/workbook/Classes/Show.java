package com.ltp.workbook.Classes;

import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


public class Show {
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotBlank(message = "Episode name cannot be blank")
    private String episode;
    @Min(value = 1, message = "Enter a rating between 1 and 10")
    @Max(value = 10, message = "Enter a rating between 1 and 10")
    private float rating;
    private String id;


    public Show(String title, String episode, float rating, String id) {
        this.title = title;
        this.episode = episode;
        this.rating = rating;
        this.id = id;
    }
    

    public Show() {
        this.id=UUID.randomUUID().toString();
        this.rating=1;
    }


    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEpisode() {
        return this.episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public float getRating() {
        return this.rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

       public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
 
}
