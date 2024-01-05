package com.carlos.curso.springboot.jpa.springbootjparelations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.carlos.curso.springboot.jpa.springbootjparelations.entities.ClientDetails;
import com.carlos.curso.springboot.jpa.springbootjparelations.entities.Cliente;
import com.carlos.curso.springboot.jpa.springbootjparelations.entities.Course;
import com.carlos.curso.springboot.jpa.springbootjparelations.entities.Direccion;
import com.carlos.curso.springboot.jpa.springbootjparelations.entities.Factura;
import com.carlos.curso.springboot.jpa.springbootjparelations.entities.Student;
import com.carlos.curso.springboot.jpa.springbootjparelations.repositories.ClienteDetailsRepository;
import com.carlos.curso.springboot.jpa.springbootjparelations.repositories.ClienteRepository;
import com.carlos.curso.springboot.jpa.springbootjparelations.repositories.CourseRepository;
import com.carlos.curso.springboot.jpa.springbootjparelations.repositories.FacturaRepository;
import com.carlos.curso.springboot.jpa.springbootjparelations.repositories.StudentRepository;

@SpringBootApplication
public class SpringbootJpaRelationsApplication implements CommandLineRunner {

	//es una clase componente
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private FacturaRepository FacturasRepository;

	@Autowired
	private ClienteDetailsRepository clienteDetailsRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;
	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		manyToManyBidireccionalRemove();
	}

	@Transactional
	public void manyToManyBidireccionalRemove(){

		Student student1 = new Student("Jano", "Perez");
		Student student2 = new Student("Ramon","Cuecenabos");

		Course course = new Course("Curso de java","El porras");
		Course course2 = new Course("Curso de papiroflexia", "El mazas");

		//student1.setCourses(Set.of(course,course2));
		//student2.setCourses(Set.of(course2));;

		//Se han comentado las lineas anteriores porque hemos creado un metodo addcourse en el cosntructor
		student1.addCourse(course2);
		student1.addCourse(course);
		student2.addCourse(course2);

		studentRepository.saveAll(Set.of(student1,student2));


		System.out.println("Buscamos a ramon");
		Optional<Student> buscandoapepe = studentRepository.findOneWithCourses(3L);
		System.out.println(buscandoapepe);

		Optional<Student> optionalstudentdb = studentRepository.findOneWithCourses(3L);
		if(optionalstudentdb.isPresent()){
			Student studentdb = optionalstudentdb.get();
			Optional<Course> optionalcoursedb = courseRepository.findOneWithStudents(3L);

			if(optionalcoursedb.isPresent()){
				Course coursedb = optionalcoursedb.get();
				studentdb.removeCourse(coursedb);

				studentRepository.save(studentdb);

				System.out.println(studentdb);
			}
		}

	}

	@Transactional
	public void manyToManyBidireccional(){

		Student student1 = new Student("Jano", "Perez");
		Student student2 = new Student("Ramon","Cuecenabos");

		Course course = new Course("Curso de java","El porras");
		Course course2 = new Course("Curso de papiroflexia", "El mazas");

		//student1.setCourses(Set.of(course,course2));
		//student2.setCourses(Set.of(course2));;

		//Se han comentado las lineas anteriores porque hemos creado un metodo addcourse en el cosntructor
		student1.addCourse(course2);
		student1.addCourse(course);
		student2.addCourse(course2);

		studentRepository.saveAll(Set.of(student1,student2));

		System.out.println(student1);
		System.out.println(student2);

	}


	@Transactional
	public void deleteManyToManyFindById(){

		Optional<Student> optionalstudent = studentRepository.findById(1L);
		Optional<Student> optionalstudent2 = studentRepository.findById(2L);

		Student student1 = optionalstudent.get();
		Student student2 = optionalstudent2.get();

		Course course = courseRepository.findById(1L).get();
		Course course2 = courseRepository.findById(2L).get();

		student1.setCourses(Set.of(course,course2));
		student2.setCourses(Set.of(course2));;

		studentRepository.saveAll(Set.of(student1,student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> optionalstudentdb = studentRepository.findOneWithCourses(1L);
		if(optionalstudentdb.isPresent()){
			Student studentdb = optionalstudentdb.get();
			Optional<Course> optionalcoursedb = courseRepository.findById(2L);

			if(optionalcoursedb.isPresent()){
				Course coursedb = optionalcoursedb.get();

				//hay que añadir el hashcode en la clase de course para que lo pueda encontrar
				studentdb.getCourses().remove(coursedb);

				studentRepository.save(studentdb);

				System.out.println(studentdb);
			}
		}

	}

	@Transactional
	public void manyToManyFindById(){

		Optional<Student> optionalstudent = studentRepository.findById(1L);
		Optional<Student> optionalstudent2 = studentRepository.findById(2L);

		Student student1 = optionalstudent.get();
		Student student2 = optionalstudent2.get();

		Course course = courseRepository.findById(1L).get();
		Course course2 = courseRepository.findById(2L).get();

		student1.setCourses(Set.of(course,course2));
		student2.setCourses(Set.of(course2));;

		studentRepository.saveAll(Set.of(student1,student2));

		System.out.println(student1);
		System.out.println(student2);

	}

	@Transactional
	public void manyToMany(){

		Student student1 = new Student("Jano", "Perez");
		Student student2 = new Student("Ramon","Cuecenabos");

		Course course = new Course("Curso de java","El porras");
		Course course2 = new Course("Curso de papiroflexia", "El mazas");

		student1.setCourses(Set.of(course,course2));
		student2.setCourses(Set.of(course2));;

		studentRepository.saveAll(Set.of(student1,student2));

		System.out.println(student1);
		System.out.println(student2);

	}

	@Transactional
	public void oneToOneBidireccionalFindById(){

		Optional<Cliente> optionalcliente = clienteRepository.findOne(2L);
		optionalcliente.ifPresent(cliente -> {
			ClientDetails clientDetails = new ClientDetails(true,5000);
			cliente.setClientDetails(clientDetails);
			clienteRepository.save(cliente);
			System.out.println("Aqui se imprime el cliente");
			System.out.println(cliente);


		} );
		
	}



		@Transactional
		public void oneToOneBidireccional(){

		Cliente cliente = new Cliente("Erba","Pura");

		ClientDetails clientDetails = new ClientDetails(true,5000);

		//se asignan mutuamente porque es bidireccional y cascade hace que se guarde solo guardando cliente
		cliente.setClientDetails(clientDetails);
		//se puede omitir esta linea de codigo añadiendola al setClienteDetails
		//clientDetails.setCliente(cliente);

		clienteRepository.save(cliente);
		System.out.println(clientDetails);
	}

	@Transactional
	public void oneToOne(){
		Cliente cliente = new Cliente("Erba","Pura");
		clienteRepository.save(cliente);

		ClientDetails clientDetails = new ClientDetails(true,5000);
		clientDetails.setCliente(cliente);
		clienteDetailsRepository.save(clientDetails);

		System.out.println(clientDetails);


	}

	@Transactional
	public void removeFacturasBidireccionalFindById(){

		Optional<Cliente> optionalcliente = clienteRepository.findOne(1L);
		optionalcliente.ifPresent(cliente ->{

		Factura factura1 = new  Factura("compras de la casa",2000L);
		Factura factura2 = new  Factura("compras de oficina",8000L);

			Set<Factura> facturas = new HashSet<>();
			facturas.add(factura1);
			facturas.add(factura2);
			cliente.setFacturas(facturas);
			
		factura1.setClient(cliente);
		factura2.setClient(cliente);

		clienteRepository.save(cliente);

		System.out.println(cliente);

		});
		Optional<Cliente> optionalclientedb = clienteRepository.findOne(1L);

		optionalclientedb.ifPresent(client ->{
			Optional<Factura> facturaoptional = FacturasRepository.findById(2L);
			facturaoptional.ifPresent(factura -> {
				//al ser bidireccional tenemos que remover la factura del cliente
				client.getFacturas().remove(factura);
				//y el cliente de la factura
				factura.setClient(null);
				//Se guarda el cliente solo porque tiene todas las relaciones y tenemso la propiedad cascada
				clienteRepository.save(client);
				System.out.println(client);
			});

		});

		
	}

	@Transactional
	public void oneToManyFacturasBidireccionalFindById(){

		Optional<Cliente> optionalcliente = clienteRepository.findOne(1L);
		optionalcliente.ifPresent(cliente ->{

		Factura factura1 = new  Factura("compras de la casa",2000L);
		Factura factura2 = new  Factura("compras de oficina",8000L);

			Set<Factura> facturas = new HashSet<>();
			facturas.add(factura1);
			facturas.add(factura2);
			cliente.setFacturas(facturas);
			
		

		//le pasamos las facturas al cliente porque es una relacion inversa manyToOne
		factura1.setClient(cliente);
		factura2.setClient(cliente);

		//se guarda todo seguido de una sola vez por la propiedad cascada de oneToMany
		clienteRepository.save(cliente);

		System.out.println(cliente);

		});
		
	}

	@Transactional
	public void oneToManyFacturasBidireccional(){
		Cliente cliente = new Cliente("Fran","Moras");

		Factura factura1 = new  Factura("compras de la casa",2000L);
		Factura factura2 = new  Factura("compras de oficina",8000L);

		Set<Factura> facturas = new HashSet<>();
		facturas.add(factura2);
		facturas.add(factura1);
		cliente.setFacturas(facturas);

		//le pasamos las facturas al cliente porque es una relacion inversa manyToOne
		factura1.setClient(cliente);
		factura2.setClient(cliente);

		//se guarda todo seguido de una sola vez por la propiedad cascada de oneToMany
		clienteRepository.save(cliente);

		System.out.println(cliente);

	}

	@Transactional
	public void removeDireccionFindById(){
			Optional<Cliente> optionalcliente = clienteRepository.findById(2L);
			optionalcliente.ifPresent(cliente ->{
			Direccion direccion = new Direccion("Viva",7);
			Direccion direccion2 = new Direccion("España", 5);

			Set<Direccion> direcciones = new HashSet<>();
			direcciones.add(direccion2);
			direcciones.add(direccion);
			cliente.setDirecciones(direcciones);
			clienteRepository.save(cliente);

			System.out.println(cliente);

			Optional<Cliente> optionalcliente2 = clienteRepository.findOneWithDirecciones(2L);
			//se comenta esta linea por lo que se ha escrito debajo
			//Optional<Cliente> optionalcliente2 = clienteRepository.findById(2L);
			optionalcliente2.ifPresent(c -> {
			//al hacer la busqueda con el metodo getdirecciones la base de datos ya esta cerrada
			//el tipo de fetch que se hace es "lazy" y puede generar error si se esta trabajando en consola
			//para evitarlo hay 3 opciones:
			//1 se puede añadir esta propiedad spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true no recomendable
			//en la relacion oneToMany se puede asignar el tipo de busqueda "fetch" a eager (el default es lazy) no es recomendable porque tambien busca las direcciones (mucha carga)
			//se puede hacer una busqueda personalizada (reocmendada) para ello se cambia findById por la busqueda personalziada que hemos creado
			c.getDirecciones().remove(direccion);
			clienteRepository.save(c);
			System.out.println("se muestra con uina direccion menos");
			System.out.println(c);

		});

		});
		
	}

	@Transactional
	public void removeOneToMany(){
		Cliente cliente = new Cliente("Fran","Moras");
		Direccion direccion = new Direccion("Viva",7);
		Direccion direccion2 = new Direccion("España", 5);

		cliente.getDirecciones().add(direccion2);
		cliente.getDirecciones().add(direccion);

		clienteRepository.save(cliente);

		System.out.println(cliente);

		Optional<Cliente> optionalcliente = clienteRepository.findById(3L);
		//el objeto "c" no es el mismo objeto que "cliente" de arriba
		//esto hace que no tengan la misma referencia y no se ejecuta el remove
		//amenos que en direccion hagas que en el id (se esta buscando por id) se generen hashcode() y equals con id
		//esto cambia el criterio de busqueda/comparacion al ser distintos objetos tienen instancias distintas
		optionalcliente.ifPresent(c -> {
			c.getDirecciones().remove(direccion);
			clienteRepository.save(c);
			System.out.println("se muestra con uina direccion menos");
			System.out.println(c);

		});
	}


	@Transactional
	public void oneToManyFindById(){
		Optional<Cliente> optionalcliente = clienteRepository.findById(2L);
		optionalcliente.ifPresent(cliente ->{
			Direccion direccion = new Direccion("Viva",7);
			Direccion direccion2 = new Direccion("España", 5);

			Set<Direccion> direcciones = new HashSet<>();
			direcciones.add(direccion2);
			direcciones.add(direccion);
			cliente.setDirecciones(direcciones);
			clienteRepository.save(cliente);			clienteRepository.save(cliente);

			System.out.println(cliente);

		});
		
	}

	@Transactional
	public void oneToMany(){
		Cliente cliente = new Cliente("Fran","Moras");
		Direccion direccion = new Direccion("Viva",7);
		Direccion direccion2 = new Direccion("España", 5);

		cliente.getDirecciones().add(direccion2);
		cliente.getDirecciones().add(direccion);

		clienteRepository.save(cliente);

		System.out.println(cliente);
	}

	@Transactional
	public void ManyToOne(){

		Cliente client = new Cliente("John","Doe");
		//clientrepository guarda el cliente
		clienteRepository.save(client);

		Factura factura = new Factura("compras de oficina", 2000L);
		factura.setClient(client);
		//facturarepository guarda la factura
		Factura facturaDB = FacturasRepository.save(factura);
		System.out.println(facturaDB);

	}

	@Transactional
	public void ManyToOneFindById(){

		Optional<Cliente> optionalCliente = clienteRepository.findById(1L);
		if (optionalCliente.isPresent()) {
			Cliente cliente = optionalCliente.orElseThrow();
			
			Factura factura = new Factura("compras de oficina", 2000L);
			System.out.println("Se asocia la factura con el id del cliente");
			factura.setClient(cliente);
			System.out.println("Se guarda la factura en la base de datos");
			Factura facturaDB = FacturasRepository.save(factura);
			System.out.println("Se imprime la factura guardada");
			System.out.println(facturaDB);
		}

		

	}
}
