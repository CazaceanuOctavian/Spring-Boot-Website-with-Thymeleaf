package com.ltp.workbook.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltp.workbook.classes.Constants;
import com.ltp.workbook.classes.Show;
import com.ltp.workbook.repository.ShowRepository;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;
    
    public Show getShow (int index) {
        return showRepository.getShow(index);
    }

    public void addShow (Show show) {
        showRepository.addShow(show);
    }

    public void setShow (int index, Show show) {
        showRepository.setShow(index, show);
    }

    public void removeShowIfFound(Show show) {
        showRepository.removeShowIfFound(show);
    }

    public int listSize () {
        return showRepository.listSize();
    }

    public List<Show> getShows() {
        return showRepository.getShows();
    }

    public int getShowIndex(String Show_id) {
        for(int i=0; i<listSize(); i++) {
            if(getShow(i).getId().equals(Show_id)) 
                return i;
        }
        return Constants.NOT_FOUND;
    }
    
    public Show getShowById(String id) {
        int index = getShowIndex(id);
        return index == Constants.NOT_FOUND ? new Show() : getShow(index);
    }

    public void submitShow(Show show) {
        int index = getShowIndex(show.getId());
        if (index == Constants.NOT_FOUND)
            addShow(show);
        else {
            setShow(index, show);
        }
    }

    public void sortByRating() {
        Collections.sort(getShows(), new Comparator<Show>() {
            public int compare (Show s1, Show s2) {
                return Float.valueOf(s2.getRating()).compareTo(s1.getRating());
            }
        });
    }

    public void sortByTitle() {
        Collections.sort(getShows(), new Comparator<Show>() {
            public int compare (Show s1, Show s2) {
                return String.valueOf(s1.getTitle()).compareTo(s2.getTitle());
            }
        });
    }
 
}
