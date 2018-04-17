package com.RealEstate.controller;

import com.RealEstate.model.Apartment;
import com.RealEstate.service.UserService;
//import jdk.nashorn.internal.parser.AbstractParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.text.html.ImageView;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    protected UserService userService;

    @GetMapping("/")
    public String index(Model m) {
        m.addAttribute("someAttribute", "someValue");
        return "index";
    }

    @GetMapping("/apartmentsList")
    public String editCustomer(Model m) {
        List<Apartment> list = userService.getApartmentsList();
        Apartment[] array = new Apartment[list.size()];
        array = list.toArray(array);
        m.addAttribute("list", array);
        m.addAttribute("size", list.size());
        m.addAttribute("apartment", new Apartment());
        return "apartmentsList";
    }


}
