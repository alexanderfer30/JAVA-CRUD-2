package com.ESFE.Asistencias.Controladores;

import com.ESFE.Asistencias.Entidades.*;
import com.ESFE.Asistencias.Servicios.Interfaces.iDocenteServices;
import com.ESFE.Asistencias.Servicios.Interfaces.iEstudianteGrupoServices;
import com.ESFE.Asistencias.Servicios.Interfaces.iEstudianteServices;
import com.ESFE.Asistencias.Servicios.Interfaces.iGrupoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/EstudianteGrupos")
public class EstudianteGrupoController {
    @Autowired
    private iEstudianteGrupoServices estudianteGrupoServices;

    @Autowired
    private iEstudianteServices estudianteServices;

    @Autowired
    private iGrupoServices grupoServices;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<EstudianteGrupo> estudianteGrupos = estudianteGrupoServices.BuscarTodosPaginados(pageable);
        model.addAttribute("estudianteGrupos", estudianteGrupos);

        int totalPage = estudianteGrupos.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pageNumber = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());

            model.addAttribute("pageNumber", pageNumber);
        }
        return "estudianteGrupo/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("estudiantesGrupos",new EstudianteGrupo());
        model.addAttribute("estudiantes",estudianteServices.ObtenerTodos());
        model.addAttribute("grupos", grupoServices.ObtenerTodos());
        return "estudianteGrupo/create";
    }

    @PostMapping("/save")
    public String save(@RequestParam Integer estudianteId,
                       @RequestParam Integer grupoId,
                       RedirectAttributes attributes) {
        Estudiante estudiante = estudianteServices.BuscarPorId(estudianteId).get();
        Grupo grupo = grupoServices.BuscarPorId(grupoId).get();


        if (estudiante != null && grupo != null) {
            EstudianteGrupo estudianteGrupo = new EstudianteGrupo();
            estudianteGrupo.setEstudiante(estudiante);
            estudianteGrupo.setGrupo(grupo);
            estudianteGrupoServices.CrearOeditar(estudianteGrupo);
            attributes.addFlashAttribute("msg",   "EstudianteGrupo creado");
        }

        return "redirect:/EstudianteGrupos";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model){
        EstudianteGrupo estudianteGrupo = estudianteGrupoServices.BuscarPorId(id).get();
        model.addAttribute("estudianteGrupo",estudianteGrupo);
        return "estudianteGrupo/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        EstudianteGrupo estudianteGrupo= estudianteGrupoServices.BuscarPorId(id).get();
        model.addAttribute("estudiantesGrupos",estudianteGrupo);
        model.addAttribute("estudiantes",estudianteServices.ObtenerTodos());
        model.addAttribute("grupos", grupoServices.ObtenerTodos());
        return "estudianteGrupo/edit";
    }

    @PostMapping("/update")
    public String update(@RequestParam Integer id,
                         @RequestParam Integer grupoId,
                         @RequestParam Integer estudianteId,
                         RedirectAttributes attributes) {
        Estudiante estudiante = estudianteServices.BuscarPorId(estudianteId).get();
        Grupo grupo = grupoServices.BuscarPorId(grupoId).get();

        if (estudiante != null && grupo != null) {
            EstudianteGrupo estudianteGrupo = new EstudianteGrupo();
            estudianteGrupo.setEstudiante(estudiante);
            estudianteGrupo.setGrupo(grupo);
            estudianteGrupo.setId(id);
            estudianteGrupoServices.CrearOeditar(estudianteGrupo);
            attributes.addFlashAttribute("msg", "asignacion modificada correcta");
        }
        return "redirect:/EstudianteGrupos";
    }
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        EstudianteGrupo estudianteGrupo= estudianteGrupoServices.BuscarPorId(id).get();
        model.addAttribute("estudianteGrupo",estudianteGrupo);
        return "estudianteGrupo/delete";
    }

    @PostMapping("/delete")
    public String delete(EstudianteGrupo estudianteGrupo, RedirectAttributes attributes){
        estudianteGrupoServices.EliminarPorId(estudianteGrupo.getId());
        attributes.addFlashAttribute("msg","Asignacion elimanado");
        return "redirect:/EstudianteGrupos";
    }
}
