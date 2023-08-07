package com.ltp.workbook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class WorkbookController {
    
    //Global 
    List<Show> shows_list = new ArrayList<Show>();


    //GetMappings
    @GetMapping("/shows")
    public String getShows(Model model) {
    model.addAttribute("shows_list", shows_list); 
        return "shows";
    }

    @GetMapping("/showsPortal")
    public String showsPortal(Model model, @RequestParam(required = false) String id) {
    int index = getShowIndex(id);
    model.addAttribute("show", index == Constants.NOT_FOUND ? new Show() : shows_list.get(index));
        return "showsPortal";   
    }

    @GetMapping("/validation")
    public String validation(Model model, @RequestParam(required = true) String id) {
        model.addAttribute("show", shows_list.get(getShowIndex(id)));
        return "validation";
    }


    //PostMappings
    @PostMapping("/handleSubmit")
    public String addShow(@Valid Show show, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) 
            return "showsPortal";
            
        int index = getShowIndex(show.getId());
        if (index == Constants.NOT_FOUND)
            shows_list.add(show);
        else {
            shows_list.set(index, show);
        }
        redirectAttributes.addFlashAttribute("flashAttributeShow", show);
        return "redirect:/shows";
    }

    @PostMapping("/handleRemove")
    public String removeShow(Show show) {
       shows_list.removeIf(n -> (n.getId().equals(show.getId())));
       return "redirect:/shows";
    }

    @PostMapping("/sortByRating")
    public String sortByRating(Show show) {
         Collections.sort(shows_list, new Comparator<Show>() {
            public int compare (Show s1, Show s2) {
                return Float.valueOf(s2.getRating()).compareTo(s1.getRating());
            }
        });
        return "redirect:/shows";
    }

    @PostMapping("/sortByTitle")
    public String sortByTitle(Show show) {
         Collections.sort(shows_list, new Comparator<Show>() {
            public int compare (Show s1, Show s2) {
                return String.valueOf(s1.getTitle()).compareTo(s2.getTitle());
            }
        });
        return "redirect:/shows";
    }


    //Functions
    public Integer getShowIndex(String Show_id) {
        for(int i=0; i<shows_list.size(); i++) {
            if(shows_list.get(i).getId().equals(Show_id)) 
                return i;
        }
        return Constants.NOT_FOUND;
    }

}
