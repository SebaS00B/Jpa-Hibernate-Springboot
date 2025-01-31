package com.sebas.springboot.jpa.sprinboot_jpa;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.sebas.springboot.jpa.sprinboot_jpa.dto.PersonDto;
import com.sebas.springboot.jpa.sprinboot_jpa.entities.Person;
import com.sebas.springboot.jpa.sprinboot_jpa.repositories.PersonRepository;

@SpringBootApplication
public class SprinbootJpaApplication implements CommandLineRunner {
	@Autowired
	private PersonRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(SprinbootJpaApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		
		whereIn();

	}

	@Transactional(readOnly =  true)
	public void whereIn() {
		System.out.println( "============================================================================================");
		System.out.println( "------------------ Consulta Where in ------------------");
		System.out.println( "============================================================================================");
		List<Person> persons = repository.getPersonsByIds(Arrays.asList(1L,4L,6L));
		persons.forEach(System.out::println);

		}


	@Transactional(readOnly =  true)
	public void querySubQuery() {
		System.out.println( "============================================================================================");
		System.out.println( "------------------ Consulta Usuario con caracteres mas cortos ------------------");
        List <Object []> shoterName = repository.getShorterName();
		shoterName.forEach(s -> System.out.println("name: " + s[0] + " , Min: " + s[1]));
		System.out.println( "============================================================================================");
		System.out.println( "============================================================================================");
		System.out.println( "------------------ Consulta Usuario con caracteres mas largos------------------");
        List <Object []> longerName = repository.getLongerName();
		longerName.forEach(l -> System.out.println("name: " + l[0] + " , Max: " + l[1]));
		System.out.println( "============================================================================================");
		System.out.println( "------------------ Consulta Usuario el ultimo registro------------------");
        Optional <Person> person = repository.getLastRegistracion();
		person.ifPresent(System.out::println);
		System.out.println( "============================================================================================");
	
	}

	@Transactional(readOnly =  true)
	public void queryFuntionAgreggation() {
		System.out.println( "============================================================================================");
		System.out.println( "------------------ Consultas total de los usuarios------------------");
		Long count = repository.getTotalPerson();
		System.out.println("Count: "+count);
		System.out.println( "============================================================================================");

		System.out.println( "============================================================================================");
		System.out.println( "------------------ Consultas el valor maximo del Id------------------");
		count = repository.getMaxId();
		System.out.println("Count: "+count);
		System.out.println( "============================================================================================");
		System.out.println( "------------------ Consultas el valor minimo del Id------------------");
		count = repository.getMinId();
		System.out.println("Count: "+count);
		System.out.println( "============================================================================================");
		System.out.println( "============================================================================================");

		System.out.println( "------------------ Consultas el nombre y su largo------------------");
		List <Object []> reg = repository.getPersonNameLength();
		reg.forEach(r -> System.out.println("Nombre: "+r[0]+" Largo: "+r[1]));
		System.out.println( "============================================================================================");

		System.out.println( "------------------ Consultas el maximo length------------------");
		Integer maxName = repository.getMaxLengthName();
		System.out.println(maxName);
		System.out.println( "============================================================================================");
		System.out.println( "------------------ Consultas el maximo length------------------");
		Integer minName = repository.getMinLengthName();
		System.out.println(minName);
		System.out.println( "============================================================================================");

		System.out.println( "============================================================================================");
		System.out.println( "------------------ Consultas de resume de agregacion------------------");
		Object [] reg2 = (Object[]) repository.getResumeAgreggationFuntion();
		System.out.println("MinId: "+reg2[0]);
		System.out.println("MaxId: "+reg2[1]);
		System.out.println("Suma: "+reg2[2]);
		System.out.println("AVG: "+reg2[3]);
		System.out.println("countId: "+reg2[4]);
		System.out.println( "============================================================================================");
		System.out.println(minName);
		System.out.println( "============================================================================================");


		}


	@Transactional(readOnly =  true)
	public void personalizedQueryBetween() {
		System.out.println( "============================================================================================");
		System.out.println( "------------------ Consultas Por rangos------------------");
		List<Person> persons = repository.findByIdBetweenOrderByIdDesc( 6L, 10L);
		persons.forEach(System.out::println);

		System.out.println( "============================================================================================");
		System.out.println( "------------------ Consultas Por rangos DE NOMBRES------------------");
		persons = repository.findByNameBetweenOrderByNameAsc( "C", "O");
		persons.forEach(System.out::println);
		System.out.println( "============================================================================================");

		}


	@Transactional(readOnly =  true)
	public void personalizedQueryConcatUpperAndLowerCase() {
		System.out.println( "============================================================================================");
		System.out.println( "------------------ Consultas unicos de nombres de personas Concatenadas------------------");
		List<String> nombresCompletos = repository.findAllFullNameConcat();
		nombresCompletos.forEach(System.out::println);

		System.out.println( "============================================================================================");
		System.out.println( "------------------ Consultas unicos de nombres de personas Concatenadas UPPER------------------");
		List <String> nombresCompletosUpper = repository.findAllFullNameConcatUpper();
		nombresCompletosUpper.forEach(System.out::println);

		System.out.println( "============================================================================================");
		System.out.println( "------------------ Consultas unicos de nombres de personas Concatenadas LOWER------------------");
		List <String> nombresCompletosLower = repository.findAllFullNameConcatLower();
		nombresCompletosLower.forEach(System.out::println);

		System.out.println( "============================================================================================");
		System.out.println( "------------------ Consultas unicos de nombres de personas Concatenadas LOWER y UPPER------------------");
		List <Object []> nombresCompletosLowerUpper = repository.findAllPersonDataListCase();
		nombresCompletosLowerUpper.forEach(lis -> System.out.println("ID=  "+lis[0] + "  NOMBRE =  "+ lis[1]+"  APELLIDO =  "+lis[2]+ "  LENGUAJE DE PROGRAMACION:  " + lis[3]));
		System.out.println( "============================================================================================");

	}

	@Transactional(readOnly =  true)
	public void personalizedQueryDistinc() {
		System.out.println( "============================================================================================");
		System.out.println( "------------------ Consultas unicos de nombres de personas------------------");
		List<String> nombres = repository.findAllNames();
		nombres.forEach(System.out::println);

		System.out.println( "============================================================================================");
		System.out.println( "------------------ Consultas unicos de nombres de lenguaje de programacion------------------");
		List<String> ProgrammingDistinc = repository.findAllProgrammingDistinc();
		ProgrammingDistinc.forEach(System.out::println);
		System.out.println( "============================================================================================");
		System.out.println( "------------------ Consultas DEL TOTAL DE lenguaje de programacion------------------");

		Long counts = repository.findAllProgrammingDistincCount();
		System.out.println("TOTAL DE LENGUAJES DE PROGRAMACION : "+counts);
		System.out.println("============================================================================================");

	}

	@Transactional(readOnly =  true)
	public void personalizedQuery2() {
		System.out.println("===========CONSULTA por objeto persona y leguaje de programacion============= ");
		List<Object[]> persons = repository.findAllMixPerson();
		persons.forEach(p ->{
			System.out.println("programingLenguage:  " + p[1] + "  persona: " + p[0]); 
		} );

		System.out.println("Consulta que puebla y devuelve un objecto entity de una instancia personalizado ");
		List <Person> persons2 = repository.findAllObjectPersonPerzonalized();
		persons2.forEach(System.out::println);

		System.out.println(" Consulta que puebla y devuelve un objecto dto de una instancia personalizado ");
		List <PersonDto> persons3 = repository.findAllPersonDto();
		persons3.forEach(System.out::println);
		 }

	@Transactional(readOnly =  true)
	public void personalizedQuery() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("===========CONSULTA ID=============");
			System.out.println("Ingrese el id:");
			Long id = scanner.nextLong();
			scanner.close();

			System.out.println("  ===========CONSULTA SOLO MOSTRANDO SOLO EL NOMBRE=============");
			String name = repository.getNameById(id);
			System.out.println(name);

			System.out.println("  ===========CONSULTA SOLO MOSTRANDO EL ID=============");
			Long idDb = repository.getIdById(id);
			System.out.println(idDb);

			System.out.println("  ===========CONSULTA DEL NOMBRE COMPLETO CON CONCAT=============");
			String fullname = repository.getFullNameById(id);
			System.out.println(fullname);

			System.out.println("  ===========CONSULTA CAMPOS PERSONALIZADOS POR EL ID=============");
			Optional<Object> optionalReg  = repository.obtenerPersonDataById(id);
			if (optionalReg.isPresent()) {
				Object[] reg = (Object[]) optionalReg.orElseThrow();
				System.out.println("ID: " + reg[0]);
				System.out.println("Nombre: " + reg[1]);
				System.out.println("Apellido: " + reg[2]);
				System.out.println("Lenguaje de Programacion: " + reg[3]);
			}

			System.out.println("  ===========CONSULTA CAMPOS PERSONALIZADOS LISTA =============");
			List <Object[]> personalizadoList = repository.obtenerPersonDataList();
			personalizadoList.forEach(lis -> System.out.println("ID= "+lis[0] + "NOMBRE ="+ lis[1]+" APELLIDO = "+lis[2]+ "LENGUAJE DE PROGRAMACION" + lis[3]));
		}

	@Transactional 
	public void delete2() {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Ingrese el id a eliminar");
			Long id = scanner.nextLong();
			Optional <Person> person = repository.findById(id);
			person.ifPresentOrElse(p -> {
				repository.findAll().forEach(System.out::println);
				repository.delete(p);
				System.out.println("Persona eliminada con exito");
				repository.findAll().forEach(System.out::println);							
				scanner.close();
			}, () -> System.out.println("Persona no encontrada"));
			}catch (Exception e) {


				System.out.println("Error al utilizar el scanner");
				}
				
		}

	@Transactional 
	public void delete() {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Ingrese el id a eliminar");
			Long id = scanner.nextLong();
			Optional <Person> person = repository.findById(id);
			/*if (person.isPresent()) {
				repository.deleteById(id);
				System.out.println("Persona eliminada con exito");
				}else {
					System.out.println("Persona no encontrada");
					}*/
			person.ifPresent(p -> {
				repository.findAll().forEach(System.out::println);
				repository.delete(p);
				System.out.println("Persona eliminada con exito");
				repository.findAll().forEach(System.out::println);				
				
				scanner.close();
			});
			}catch (Exception e) {


				System.out.println("Error al utilizar el scanner");
				}
				
		}
			
 
		

	@Transactional 
	public void update() {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Ingrese el id de persona que desea actualizar");
			long id = scanner.nextLong();
			Optional <Person> optionalPerson = repository.findById(id);
			if (optionalPerson.isPresent()) {
				System.out.println("Ingrese el lenguaje de programacion");
				String lenguagueProgramming = scanner.next();
				optionalPerson.get().setProgrammingLanguague(lenguagueProgramming);
				repository.save(optionalPerson.get());
				System.out.println("Persona actualizada con exito");
				}else {
					System.out.println( "Persona no encontrada");
					}
		/*optionalPerson.ifPresent(p -> { 
				System.out.println(p);
				System.out.println("Ingrese el nuevo nombre");
				String name = scanner.next();
				p.setName(name);
				Person persondb = repository.save(p);
				System.out.println(persondb);	
			});*/
			

			scanner.close();
				
		}catch (Exception e) {


			System.out.println("Error al actualizar la persona, no he encontrada ");
			}
			

			}


			@Transactional 
	public void update2() {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Ingrese el id de persona que desea actualizar");
			long id = scanner.nextLong();
			Optional <Person> optionalPerson = repository.findById(id);
	
		optionalPerson.ifPresentOrElse(p -> { 
				System.out.println(p);
				System.out.println("Ingrese el nuevo nombre");
				String name = scanner.next();
				p.setName(name);
				Person persondb = repository.save(p);
				System.out.println(persondb);	
			}, () -> System.out.println("Persona no encontrada"));
			

			scanner.close();
				
		}catch (Exception e) {


			System.out.println("Error al actualizar la persona, no he encontrada ");
			}
			

			}
	@Transactional 
	public void create () {

		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Ingrese el nombre de la persona");
			String name = scanner.nextLine();
			System.out.println("Ingrese el apellido de la persona");
			String lastName = scanner.nextLine();
			System.out.println("Ingrese el lenguague Programacion de la persona");
			String lenguaguePrograming = scanner.nextLine();
			scanner.close();
			Person person = new Person(null,name ,lastName, lenguaguePrograming);
			Person personNew = repository.save(person);

			repository.findById(personNew.getId()).ifPresent( System.out::println);


			System.out.println( "Person created : " + personNew );
			} catch (Exception e) {
				System.out.println("Error al crear la persona");
				}
				
		}


	

	@Transactional ( readOnly = true)
	public void findOne () {
		/*Person person = null ;
		Optional <Person> optional = repository.findById(1L);
		if (optional.isPresent()) {
			person = optional.get();
			System.out.println(person);
			}else {
				System.out.println("No existe la persona");
				}*/
		repository.findByNameContaining("ali").ifPresent(System.out::println);
		}
		
		@Transactional ( readOnly = true)
		public void list ()	 
	{		
	//List <Person> persons = (List<Person>) repository.findAll();
	List <Person> persons = (List<Person>) repository.buscarByProgrammingLanguague("Java");
	persons.stream().forEach( p -> System.out.println(p));
		
	List <Object []> personsValue = repository.obtenerPersonData();
	personsValue.stream().forEach( p -> System.out.println(p[0] + " es experto en: " + p[1]));
	}
}
