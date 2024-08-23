package com.ESFE.Asistencias.Repositorios;

import com.ESFE.Asistencias.Entidades.DocenteGrupo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

public interface iDocenteGrupoRepository extends JpaRepository<DocenteGrupo,Integer> {
    Page<DocenteGrupo> findByOrderByDocenteDesc(Pageable pageable);
}
