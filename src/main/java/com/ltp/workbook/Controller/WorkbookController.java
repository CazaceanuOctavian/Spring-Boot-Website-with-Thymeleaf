package com.ltp.workbook.Controller;

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

import com.ltp.workbook.Constants;
import com.ltp.workbook.Show;
import com.ltp.workbook.Repository.ShowRepository;


@Controller
public class WorkbookController {
    
    ShowRepository showList= new ShowRepository();

    //GetMappings
    @GetMapping("/shows")
    public String getShows(Model model) {
    model.addAttribute("shows_list", showList); 
        return "shows";
    }

    @GetMapping("/showsPortal")
    public String showsPortal(Model model, @RequestParam(required = false) String id) {
    int index = getShowIndex(id);
    model.addAttribute("show", index == Constants.NOT_FOUND ? new Show() : showList.getShow(index));
        return "showsPortal";   
    }

    @GetMapping("/validation")
    public String validation(Model model, @RequestParam(required = true) String id) {
        model.addAttribute("show", showList.getShow(getShowIndex(id)));
        return "validation";
    }


    //PostMappings
    @PostMapping("/handleSubmit")
    public String addShow(@Valid Show show, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) 
            return "showsPortal";
            
        int index = getShowIndex(show.getId());
        if (index == Constants.NOT_FOUND)
            showList.addShow(show);
        else {
            showList.setShow(index, show);
        }
        redirectAttributes.addFlashAttribute("flashAttributeShow", show);
        return "redirect:/shows";
    }

    @PostMapping("/handleRemove")
    public String removeShow(Show show) {
       showList.removeShowIfFound(show);
       return "redirect:/shows";
    }

    @PostMapping("/sortByRating")
    public String sortByRating(Show show) {
         Collections.sort(showList, new Comparator<Show>() {
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
        for(int i=0; i<showList.listSize(); i++) {
            if(showList.getShow(i).getId().equals(Show_id)) 
                return i;
        }
        return Constants.NOT_FOUND;
    }

}
