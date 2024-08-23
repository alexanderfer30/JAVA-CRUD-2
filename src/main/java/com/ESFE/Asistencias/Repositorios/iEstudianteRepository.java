package com.ESFE.Asistencias.Repositorios;

import com.ESFE.Asistencias.Entidades.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iEstudianteRepository extends JpaRepository<Estudiante,Integer> {
}
