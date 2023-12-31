package com.ULPalumnos.ULPAlumnos.SPRING;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Controller
public class ULPController {
    @Autowired
    private TemplateEngine templateEngine;
    
    @RequestMapping("/")
    public String inicio(){
        return "inicio";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/carreras")
    public String carreras(Model  model) {
        MateriaData mateData = new MateriaData();
        ArrayList<Materia> objectList = mateData.listarMaterias();
        Context context = new Context();
        context.setVariable("objectList", objectList);

        String renderedTemplate = templateEngine.process("carreras.html", context);
        model.addAttribute("objectList", objectList);
        return "carreras";
    }

    @GetMapping("/docentes")
    public String docentes(Model  model) {
        InscripcionData inscData = new InscripcionData();
        List<Inscripcion> objectList = inscData.obtenerIncripciones();
        Context context = new Context();
        context.setVariable("objectList", objectList);

        String renderedTemplate = templateEngine.process("docentes.html", context);
        model.addAttribute("objectList", objectList);
        return "docentes";
    }

    @GetMapping("/alumnos")
    public String alumnos(Model  model) {
    AlumnoData aluData = new AlumnoData();
    List<Alumno> objectList = aluData.listarAlumnos();
     Context context = new Context();
     context.setVariable("objectList", objectList);

        String renderedTemplate = templateEngine.process("alumnos.html", context);
        model.addAttribute("objectList", objectList);
        return "alumnos";
}

}
