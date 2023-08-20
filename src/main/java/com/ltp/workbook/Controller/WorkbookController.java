package com.ltp.workbook.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ltp.workbook.classes.Show;
import com.ltp.workbook.service.ShowService;


@Controller
public class WorkbookController {
    
    @Autowired
    ShowService showService;

    //GetRequests
    @GetMapping("/shows")
    public String getShows(Model model) {
    model.addAttribute("shows_list", showService.getShows()); 
        return "shows";
    }

    @GetMapping("/showsPortal")
    public String showsPortal(Model model, @RequestParam(required = false) String id) {
    model.addAttribute("show", showService.getShowById(id));
        return "showsPortal";   
    }

    @GetMapping("/validation")
    public String validation(Model model, @RequestParam(required = true) String id) {
        model.addAttribute("show", showService.getShow(showService.getShowIndex(id)));
        return "validation";
    }


    //PostRequests
    @PostMapping("/handleSubmit")
    public String addShow(@Valid Show show, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) 
            return "showsPortal";

        showService.submitShow(show);

        redirectAttributes.addFlashAttribute("flashAttributeShow", show);
        return "redirect:/shows";
    }

    @PostMapping("/handleRemove")
    public String removeShow(Show show) {
       showService.removeShowIfFound(show);
       return "redirect:/shows";
    }

    @PostMapping("/sortByRating")
    public String sortByRating(Show show) {
        showService.sortByRating();
        return "redirect:/shows";
    }

    @PostMapping("/sortByTitle")
    public String sortByTitle(Show show) {
        showService.sortByTitle();
        return "redirect:/shows";
    }

}
