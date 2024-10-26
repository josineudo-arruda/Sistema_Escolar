package com.josineudo.sistema_escolar.models.repositories;

import com.josineudo.sistema_escolar.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
Formato de conectar banco com a aplicação, no Repository que é uma interface
extendemos um metodo JpaRepository e indicamos o model e o tipo da chave primaria.
CrudRepository, pode ser uma opção para o JpaRepository
*/
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
