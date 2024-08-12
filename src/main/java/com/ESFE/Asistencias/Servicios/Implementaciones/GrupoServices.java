package com.ESFE.Asistencias.Servicios.Implementaciones;

import com.ESFE.Asistencias.Entidades.Grupo;
import com.ESFE.Asistencias.Repositorios.iGrupoRepository;
import com.ESFE.Asistencias.Servicios.Interfaces.iGrupoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoServices implements iGrupoServices {

    @Autowired
    private iGrupoRepository grupoRepository;

    @Override
    public Page<Grupo> BuscarTodosPaginados(Pageable pageable) {
        return grupoRepository.findAll(pageable);
    }

    @Override
    public List<Grupo> ObtenerTodos() {
        return grupoRepository.findAll();
    }

    @Override
    public Optional<Grupo> BuscarPorId(Integer id) {
        return grupoRepository.findById(id);
    }

    @Override
    public Grupo CrearOeditar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Override
    public void EliminarPorId(Integer id) {
        grupoRepository.deleteById(id);
    }
}
