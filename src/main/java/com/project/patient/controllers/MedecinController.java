package com.project.patient.controllers;


import com.project.patient.entities.Medecin;
import com.project.patient.entities.Patient;
import com.project.patient.repositories.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MedecinController {

    @Autowired
    private MedecinRepository medecinRepository;

    @GetMapping("/user/Medecin")
    public String ListeMedecin(Model m, @RequestParam(name="page",defaultValue = "0") int page , @RequestParam(name="size",defaultValue = "5")
            int size,
                               @RequestParam(name = "keyword",defaultValue = "") String keyword)
    {
        Page<Medecin> medecins=medecinRepository.findbyCriteria(keyword, PageRequest.of(page,size));
        m.addAttribute("AllDoctors",medecins.getContent());
        m.addAttribute("pages",new int[medecins.getTotalPages()]);
        m.addAttribute("currentPage",page);
        m.addAttribute("keyword",keyword);
        return "medecins";
    }


}
