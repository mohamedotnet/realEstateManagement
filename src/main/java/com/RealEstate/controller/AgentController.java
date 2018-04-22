package com.RealEstate.controller;

import com.RealEstate.model.Agent;
import com.RealEstate.model.Appointment;
import com.RealEstate.model.Report;
import com.RealEstate.model.Visit;
import com.RealEstate.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class AgentController {

    @Autowired
    private AgentService agentService;

    @Autowired
    private Agent agent;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }


    @GetMapping("/Agent/login")
    public String login(Model m) {
        m.addAttribute("agent", new Agent());
        return "Agent/login";
    }

    @GetMapping("/Agent/addReport")
    public String addReport(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("agent")){
            m.addAttribute("agent", new Agent());
            return "Agent/login";
        }
        m.addAttribute("report", new Report());
        return "Agent/addReport";
    }

    @GetMapping("/Agent/removeReport")
    public String removeReport(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("agent")){
            m.addAttribute("agent", new Agent());
            return "Agent/login";
        }
        List<Report> list = agentService.getReportsList();
        m.addAttribute("array", list);
        m.addAttribute("size", list.size());
        m.addAttribute("report", new Report());
        return "Agent/removeReport";
    }

    @GetMapping("Agent/appointments")
    public String getAppointments(Model m, HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("agent")){
            m.addAttribute("agent", new Agent());
            return "Agent/login";
        }
        /*
            TODO: Handle empty list..
         */
        List<Appointment> list = agentService.getAppList(agentService.getAgentByUsername(
                                              (String)session.getAttribute("username")));
        m.addAttribute("array", list);
        m.addAttribute("app", new Appointment());
        return "Agent/appointments";
    }


    @GetMapping("/Agent/editReport")
    public String editReport(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("agent")){
            m.addAttribute("agent", new Agent());
            return "Agent/login";
        }
        List<Report> list = agentService.getReportsList();
        m.addAttribute("array", list);
        m.addAttribute("size", list.size());
        m.addAttribute("report", new Report());
        return "Agent/editReport";
    }

    @GetMapping("/Agent/programVisits")
    public String programVisits(Model m,HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("agent")){
            m.addAttribute("agent", new Agent());
            return "Agent/login";
        }
        List<Visit> list = agentService.getVisitsListByAgent(agent.getUsername());
        m.addAttribute("array", list);
        m.addAttribute("visit", new Visit());
        return "Agent/programVisits";
    }

    @GetMapping("/Agent/agentSpace")
    public String agentSpace(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("agent")){
            m.addAttribute("agent", new Agent());
            return "Agent/login";
        }
        m.addAttribute("agent", agent);
        return "Agent/agentSpace";
    }

    @GetMapping("/Agent/changeProfilePicture")
    public String changeProfilePicture(Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("agent")){
            m.addAttribute("agent", new Agent());
            return "Agent/login";
        }
        return "Agent/changeProfilePicture";
    }

    @PostMapping("loginSuccessA")
    public String loginSuccess(@Valid @ModelAttribute("SpringWeb") Agent agent, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors())
            return "Agent/login";
        if (agentService.checkAgentLogin(agent.getUsername(), agent.getPassword())) {
            this.agent = agentService.getAgentByUsername(agent.getUsername());
            session.setAttribute("username", this.agent.getUsername());
            session.setAttribute("role", this.agent.getRole());
            model.addAttribute("agent", this.agent);
            return "Agent/agentSpace";
        } else {
            model.addAttribute("agent", new Agent());
            model.addAttribute("err", "Nom d'utilisateur ou mot de passe incorrect");
            return "Agent/login";
        }
    }

    @PostMapping("addReportSuccess")
    public String addReportSuccess(@Valid @ModelAttribute("SpringWeb") Report report, BindingResult bindingResult, Model model,
                                   HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("agent")){
            model.addAttribute("agent", new Agent());
            return "Agent/login";
        }
        if (bindingResult.hasErrors())
            return "Agent/addReport";
        agentService.addReport(report);
        model.addAttribute("report", report);
        return "Agent/agentSpace";
    }

    @PostMapping("/removeReportSuccess")
    public String removeReportSuccess(@Valid @ModelAttribute("SpringWeb") Report report, BindingResult bindingResult, Model model,
                                      HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("agent")){
            model.addAttribute("agent", new Agent());
            return "Agent/login";
        }
        if (bindingResult.hasErrors()) {
            return "Agent/removeReport";
        }
        agentService.removeReport(report.getId());
        return "Agent/agentSpace";
    }

    @PostMapping("/validateAppointment")
    public String getAppointmentsPost(@Valid @ModelAttribute("SpringWeb") Appointment app, BindingResult bindingResult,
                                      Model m, HttpSession session){
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("agent")){
            m.addAttribute("agent", new Agent());
            return "Agent/login";
        }
        if (bindingResult.hasErrors()) {
            return "Agent/appointments";
        }
        agentService.validateAppointment(app, (String)session.getAttribute("username"));
        return "Agent/agentSpace";
    }

    @PostMapping("/editReportSuccess")
    public String editReport(@Valid @ModelAttribute("SpringWeb") Report report, BindingResult bindingResult, Model model,
                             HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("agent")){
            model.addAttribute("agent", new Agent());
            return "Agent/login";
        }
        if (bindingResult.hasErrors()) {
            return "Agent/editReport";
        }
        agentService.editReport(report);
        model.addAttribute("report", report);
        return "Agent/agentSpace";
    }

    @PostMapping("/changePictureSuccessAg")
    public String changeProfilePictureAg(@RequestParam("file") MultipartFile file, Model m, HttpSession session) {
        if (session.getAttribute("username") == null || !session.getAttribute("role").equals("agent")){
            m.addAttribute("agent", new Agent());
            return "Agent/login";
        }
        String picture = agentService.changeProfilePicture(file, this.agent.getUsername());
        this.agent.setPicture(picture);
        m.addAttribute("agent", this.agent);
        return "Agent/changeProfilePicture";
    }
}
