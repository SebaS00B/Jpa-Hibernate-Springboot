package com.sebas.springboot.jpa.sprinboot_jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sebas.springboot.jpa.sprinboot_jpa.dto.PersonDto;
import com.sebas.springboot.jpa.sprinboot_jpa.entities.Person;

public interface PersonRepository  extends CrudRepository <Person, Long> {

    @Query ("SELECT p FROM Person p WHERE p.id in ?1")
    public List <Person> getPersonsByIds(List <Long> ids);

    @Query ("SELECT p FROM Person p WHERE p.id = (select max(p.id) FROM Person p)")
    public Optional <Person> getLastRegistracion();

    @Query ("SELECT p.name, length(p.name) FROM Person p WHERE length(p.name) = (select min(length(p.name)) FROM Person p)")
    public List <Object[]> getShorterName();

    @Query ("SELECT p.name, length(p.name) FROM Person p WHERE length(p.name) = (select max(length(p.name)) FROM Person p)")
    public List <Object[]> getLongerName();

    @Query ("SELECT min (p.id),max (p.id),sum (p.id), avg (length(p.name)), count(p.id) FROM Person p ")
    public Object getResumeAgreggationFuntion();

    @Query ("SELECT p.name, length(p.name) FROM Person p ")
    public List <Object[]> getPersonNameLength();

    @Query ("SELECT min(length(p.name) )  FROM Person p ")
    public Integer getMinLengthName();

    @Query ("SELECT max(length(p.name) )  FROM Person p ")
    public Integer getMaxLengthName();

    @Query ("SELECT count (p) FROM Person p")
    Long getTotalPerson();

    @Query ("SELECT min (p.id) FROM Person p")
    Long getMinId();

    @Query ("SELECT max (p.id) FROM Person p")
    Long getMaxId();

    List <Person> findAllByOrderByNameAscLastnameDesc();
    
    List <Person> findByIdBetweenOrderByIdDesc( Long id1, Long id2);
    List <Person> findByNameBetweenOrderByNameAsc( String name1, String name2);
 
    @Query ("SELECT p FROM Person p where p.name between ?1 and ?2  order by p.name desc")  
    List <Person> findAllBetweenByName( String name1, String name2 );
    
    @Query ("SELECT p FROM Person p where p.id between ?1 and ?2 order by p.name asc")  
    List <Person> findAllBetweenId( Long id1, Long id2 );
    
    @Query ("SELECT p.id , upper(p.name), lower(p.lastname), upper(p.programmingLanguague) FROM Person p ")
    List <Object []> findAllPersonDataListCase();

    @Query ("SELECT upper (CONCAT(p.name , ' ', p.lastname)) as fullname FROM Person p ")
    List <String> findAllFullNameConcatUpper();

    @Query ("SELECT lower (CONCAT(p.name , ' ', p.lastname)) as fullname FROM Person p ")
    List <String> findAllFullNameConcatLower();

    @Query ("SELECT CONCAT(p.name , ' ', p.lastname) as fullname FROM Person p ")
    List <String> findAllFullNameConcat();
    
    @Query("Select count (distinct (p.programmingLanguague)) FROM Person p")
    Long findAllProgrammingDistincCount();

    @Query("Select distinct (p.programmingLanguague) FROM Person p")
    List <String> findAllProgrammingDistinc();

    @Query("Select distinct (p.name) FROM Person p")
    List <String> findAllNames();

    @Query ("Select new com.sebas.springboot.jpa.sprinboot_jpa.dto.PersonDto(p.name, p.lastname) FROM Person p")
    List <PersonDto> findAllPersonDto();
    
    @Query ("Select new Person(p.name, p.lastname) FROM Person p")
    List <Person> findAllObjectPersonPerzonalized();

    @Query ("SELECT p.name FROM Person p WHERE p.id = ?1")
    String getNameById(Long id);

    @Query ("SELECT p.id FROM Person p WHERE p.id = ?1")
    Long getIdById(Long id);

    @Query ("SELECT CONCAT(p.name, ' ', p.lastname) as fullname FROM Person p WHERE p.id = ?1")
    String getFullNameById(Long id);
    
    @Query ("SELECT p FROM Person p WHERE p.id = ?1")
    Optional <Person> findOne(Long id);

    @Query ("SELECT p FROM Person p WHERE p.name = ?1")
    Optional <Person> findOneName(String name);

    @Query ("SELECT p FROM Person p WHERE p.name like %?1%")
    Optional <Person> findOneLikeName(String name);


    Optional <Person> findByNameContaining(String name);

    // No need to implement any method here
    List <Person> findByProgrammingLanguague(String programmingLanguague);

    @Query("select p from Person p where p.programmingLanguague = :programmingLanguague")
    List <Person> buscarByProgrammingLanguague(String programmingLanguague);

    List <Person> findByProgrammingLanguagueAndName(String programmingLanguague, String name);

    @Query ("select p, p.programmingLanguague from Person p ")
    List <Object[]> findAllMixPerson ();
    
    @Query ("select p.id, p.name, p.lastname , p.programmingLanguague from Person p ")
    List <Object[]> obtenerPersonDataList ();

    @Query ("select p.id, p.name, p.lastname ,p.programmingLanguague from Person p  where p.id = ?1")
    Optional <Object> obtenerPersonDataById( Long id );

    @Query ("select p.name, p.programmingLanguague from Person p ")
    List <Object[]> obtenerPersonData ();

    @Query ("select p.name, p.programmingLanguague from Person p where p.name = :name")
    List <Object[]> obtenerPersonData (String name);

    @Query ("select p.name, p.programmingLanguague from Person p where p.programmingLanguague = :programmingLanguague and p.name = :name")
    List <Object[]> obtenerPersonData (String programmingLanguague, String name);

}
