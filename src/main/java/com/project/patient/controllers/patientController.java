package com.project.patient.controllers;


import com.project.patient.entities.Patient;
import com.project.patient.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.List;

@Controller
public class patientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/user/Liste")
public String ListePatient(Model m, @RequestParam(name="page",defaultValue = "0") int page ,@RequestParam(name="size",defaultValue = "5")
            int size,
    @RequestParam(name = "keyword",defaultValue = "") String keyword)
    {
        Page<Patient> patients=patientRepository.findbyCriteria(keyword,PageRequest.of(page,size));
        m.addAttribute("AllPatients",patients.getContent());
        m.addAttribute("pages",new int[patients.getTotalPages()]);
        m.addAttribute("currentPage",page);
        m.addAttribute("keyword",keyword);
        return "patients";
    }
  /*  @GetMapping("/Search")
    public String findbyCriteria(Model m,@RequestParam(name = "keyword") String keyword) {
        List<Patient> listpatients=patientRepository.findbyCriteria(keyword);
        m.addAttribute("listPatients",listpatients);
return "patients";
    }

   */
 @GetMapping("/admin/delete")
public String delete(Long id,Model m,String keyword,int page){

     m.addAttribute("currentPage",page);
     m.addAttribute("keyword",keyword);
      patientRepository.deleteById(id);
      return "redirect:/user/Liste";
 }

 @GetMapping("/admin/AddPatient")
    public String PatientForm(Model model)
 {

     model.addAttribute("patient",new Patient());
     return "AddPatient";
 }
 @GetMapping("/")
 public  String home()
 {
     return "Home";
 }
 @GetMapping("/admin/edit")
    public String edit(Model model,Long id,int page,String keyword)
 {
    Patient p= patientRepository.findById(id).orElse(null);
    if(p==null) throw new RuntimeException("Patient introuvable");

     model.addAttribute("patient",p);
     model.addAttribute("page",page);
     model.addAttribute("keyword",keyword);
     return "editPatient";
 }

    @PostMapping("/admin/add")
    public String addPerson(Model model,@Valid Patient patient,BindingResult bindingResult,@RequestParam(defaultValue ="0") int page,@RequestParam(defaultValue = "" )String keyword) {
         if(bindingResult.hasErrors()) return "AddPatient";
        patientRepository.save(patient);
        return "redirect:/user/Liste?page="+page+"&keyword="+keyword;
    }

}
