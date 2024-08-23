package com.ESFE.Asistencias.Servicios.Implementaciones;

import com.ESFE.Asistencias.Entidades.Estudiante;
import com.ESFE.Asistencias.Repositorios.iDocenteRepository;
import com.ESFE.Asistencias.Repositorios.iEstudianteRepository;
import com.ESFE.Asistencias.Servicios.Interfaces.iEstudianteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServices implements iEstudianteServices {

    @Autowired
    private iEstudianteRepository estudianteRepository;

    @Override
    public Page<Estudiante> BuscarTodosPaginados(Pageable pageable) {
        return estudianteRepository.findAll(pageable);
    }

    @Override
    public List<Estudiante> ObtenerTodos() {
        return estudianteRepository.findAll();
    }

    @Override
    public Optional<Estudiante> BuscarPorId(Integer id) {
        return estudianteRepository.findById(id);
    }

    @Override
    public Estudiante CrearOeditar(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public void EliminarPorId(Integer id) {
        estudianteRepository.deleteById(id);
    }
}
