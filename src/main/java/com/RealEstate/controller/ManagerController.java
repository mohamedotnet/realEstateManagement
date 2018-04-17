package com.RealEstate.controller;

import com.RealEstate.model.*;
import com.RealEstate.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private Manager manager;


    @GetMapping("/Manager/login")
    public String login(Model m){
        m.addAttribute("manager", new Manager());
        return "Manager/login";
    }

    @GetMapping("/Manager/addContract")
    public String addContract(Model m, HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("manager")){
            m.addAttribute("manager", new Manager());
            return "Manager/login";
        }
        m.addAttribute("contract", new Contract());
        m.addAttribute("manager", manager);
        return "Manager/addContract";
    }

    @GetMapping("/Manager/changeProfilePicture")
    public String changeProfilePicture(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("manager")){
            m.addAttribute("manager", new Manager());
            return "Manager/login";
        }
        return "Manager/changeProfilePicture";
    }

    @GetMapping("/Manager/managerSpace")
    public String managerSpace(Model m, HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("manager")){
            m.addAttribute("manager", new Manager());
            return "Manager/login";
        }
        m.addAttribute("manager", manager);
        return "Manager/managerSpace";
    }

    @GetMapping("/Manager/editContract")
    public String editContract(Model m, HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("manager")){
            m.addAttribute("manager", new Manager());
            return "Manager/login";
        }
        List<Contract> list = managerService.getContractsList();
        Contract[] array = new Contract[list.size()];
        array = list.toArray(array);
        m.addAttribute("list", array);
        m.addAttribute("size", list.size());
        m.addAttribute("contract", new Contract());
        return "Manager/editContract";
    }

    @GetMapping("/Manager/validateSale")
    public String validateSale(Model m, HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("manager")){
            m.addAttribute("manager", new Manager());
            return "Manager/login";
        }
        List<Sale> list = managerService.getSalesList();
        Sale[] array = new Sale[list.size()];
        array = list.toArray(array);
        m.addAttribute("list", array);
        m.addAttribute("size", list.size());
        m.addAttribute("sale", new Sale());
        return "Manager/validateSale";
    }

    @GetMapping("/Manager/deleteContract")
    public String deleteContract(Model m, HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("manager")){
            m.addAttribute("manager", new Manager());
            return "Manager/login";
        }
        List<Contract> list = managerService.getContractsList();
        Contract[] array = new Contract[list.size()];
        array = list.toArray(array);
        m.addAttribute("list", array);
        m.addAttribute("size", list.size());
        m.addAttribute("contract", new Contract());
        return "Manager/deleteContract";
    }

    @PostMapping("loginSuccessM")
    public String loginSuccess(@Valid @ModelAttribute("SpringWeb")Manager manager, BindingResult bindingResult, Model model,
                                HttpSession session){
        if(bindingResult.hasErrors())
            return "Manager/login";
        if(managerService.checkManagerLogin(manager.getUsername(), manager.getPassword())){
            this.manager = managerService.getManagerByUsername(manager.getUsername());
            session.setAttribute("username", this.manager.getUsername());
            session.setAttribute("role", this.manager.getRole());
            model.addAttribute("manager", this.manager);
            return "Manager/managerSpace";
        }
        model.addAttribute("manager", new Manager());
        model.addAttribute("err", "Nom d'utilisateur ou mot de passe incorrect");
        return "Manager/login";
    }

    @PostMapping("/validateSaleSuccess")
    public String validateSaleSuccess(@Valid @ModelAttribute("SpringWeb")Sale sale, BindingResult bindingResult, Model model,
                                      HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("manager")){
            model.addAttribute("manager", new Manager());
            return "Manager/login";
        }
        if(bindingResult.hasErrors())
            return "Manager/validateSale";
        managerService.validatePayment(sale.getReference(), sale.getApartment());
        return "Manager/managerSpace";
    }

    @PostMapping("/addContractSuccess")
    public String addContractSuccess(@RequestParam("file") MultipartFile file, @RequestParam("sale") String sale,
                                     @RequestParam("content") String content, @RequestParam("date") String date,
                                     @RequestParam("time") String time, @RequestParam("manager") String manager, Model m,
                                     HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("manager")){
            m.addAttribute("manager", new Manager());
            return "Manager/login";
        }
        managerService.addContract(file, sale, content, date,time, manager);
        m.addAttribute("manager", this.manager);
        return "Manager/managerSpace";
    }

    @PostMapping("/editContractSuccess")
    public String editContractSuccess(@Valid @ModelAttribute("SpringWeb")Contract contract, BindingResult bindingResult, Model model,
                                      HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("manager")){
            model.addAttribute("manager", new Manager());
            return "Manager/login";
        }
        if(bindingResult.hasErrors())
            return "Manager/validateSale";
        managerService.updateContract(contract);
        return "Manager/managerSpace";
    }

    @PostMapping("/deleteContractSuccess")
    public String deleteContractSuccess(@Valid @ModelAttribute("SpringWeb")Contract contract, BindingResult bindingResult, Model model,
                                        HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("manager")){
            model.addAttribute("manager", new Manager());
            return "Manager/login";
        }
        if(bindingResult.hasErrors())
            return "Manager/validateSale";
        managerService.deleteContract(contract.getId());
        return "Manager/managerSpace";
    }

    @PostMapping("/changePictureSuccessM")
    public String changePictureSuccess(@RequestParam("file") MultipartFile file, Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("manager")){
            m.addAttribute("manager", new Manager());
            return "Manager/login";
        }
        String picture = managerService.changeProfilePicture(file, this.manager.getUsername());
        this.manager.setPicture(picture);
        m.addAttribute("manager", this.manager);
        return "Manager/managerSpace";
    }

}