package com.ng.springboot.jpa.mapping;

import java.util.Optional;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.ng.springboot.jpa.mapping.entities.Address;
import com.ng.springboot.jpa.mapping.entities.Comment;
import com.ng.springboot.jpa.mapping.entities.Employee;
import com.ng.springboot.jpa.mapping.entities.Post;
import com.ng.springboot.jpa.mapping.entities.Product;
import com.ng.springboot.jpa.mapping.entities.ProductDetails;
import com.ng.springboot.jpa.mapping.entities.Project;
import com.ng.springboot.jpa.mapping.entities.User;
import com.ng.springboot.jpa.mapping.repositories.EmployeeRepository;
import com.ng.springboot.jpa.mapping.repositories.PostRepository;
import com.ng.springboot.jpa.mapping.repositories.ProductRepository;
import com.ng.springboot.jpa.mapping.repositories.UserRepository;

@SpringBootApplication
public class SpringBootMappingDataJpaApplication {

	public static void main(String[] args) {

		// 'Bidirectional @OneToMany' associations are way better than unidirectional
		// ones because they rely on the @ManyToOne relationship, which is always
		// efficient in terms of generated SQL statements.
		// But then, even if they are very convenient, you don’t always have to use
		// collections. The @ManyToOne association is the most natural and also
		// efficient way of mapping a one-to-many database relationship.

		ApplicationContext context = SpringApplication.run(SpringBootMappingDataJpaApplication.class, args);

		if (true) {

			EmployeeRepository employeeRepo = null;

			JPAmappingTestManyToMany(employeeRepo = context.getBean(EmployeeRepository.class));

			Optional<Employee> retrivedEmployee = employeeRepo.findById(1l);

			System.out.println(retrivedEmployee.get().getProjects());

			System.out.println(retrivedEmployee.get());

		}

		if (false) {

			JPAmappingTestOneToMany(context);

		}

		if (false) {

			JPAmappingTestOneToOne(context);

		}

	}

	// we need to take care while setting child attribute, we need to prvide parent
	// setter as this else mapping wont be present
	public static void JPAmappingTestOneToOne(ApplicationContext context) {

		ProductRepository productRepo = context.getBean(ProductRepository.class);

		Product product = new Product("Laptop", new ProductDetails("Dell Laptop with i5 processor"));

		productRepo.save(product);

	}

	public static void JPAmappingTestManyToMany(EmployeeRepository employeeRepo) {

		Employee employee = new Employee();
		employee.setName("Nirmal Gurjar");

		Project project1 = new Project();
		project1.setProject("TCS iON Energy");

		Project project2 = new Project();
		project2.setProject("TCS iON UCP");

		employee.setProjects(Set.of(project1, project2));

		employeeRepo.save(employee);

	}

	public static void JPAmappingTestOneToMany(ApplicationContext context) {

		Address address = new Address("Ghati Mohalla", "Mokdi");

		User user = new User("Nirmal@" + System.currentTimeMillis(), address);

		UserRepository userRepository = context.getBean(UserRepository.class);
		userRepository.save(user);

		Comment comment = new Comment();
		comment.setComment("I am Nirmal Commentin on my post @" + System.currentTimeMillis());
		comment.setCommenter(user);

		// Comment comment = new Comment("I am Nirmal Commentin on my post @" +
		// System.currentTimeMillis(), user, null);
		Post post = new Post();

		post.setContent("Hello World ! I am posting from Facebook @" + System.currentTimeMillis());
		post.setUser(user);
		post.addComment(comment);

		// Post post = new Post("Hello World ! I am posting from Facebook @" +
		// System.currentTimeMillis(), comment, user);

		PostRepository postRepository = context.getBean(PostRepository.class);
		post = postRepository.save(post);

		User commenter = new User("Charan@" + System.currentTimeMillis(), address);
		userRepository.save(commenter);

		// CommentRepository commentRepository =
		// context.getBean(CommentRepository.class);

		System.out.println(post.getId());

		comment = new Comment();
		comment.setComment("Welcome Nirmal bhaiya @" + System.currentTimeMillis());
		comment.setCommenter(commenter);

		post.addComment(comment);

		post = postRepository.save(post);

		comment = new Comment();
		comment.setComment("We Hope, you will enjoy here, Nirmal bhaiya @" + System.currentTimeMillis());
		comment.setCommenter(commenter);

		post.addComment(comment);

		post = postRepository.save(post);

	}

}
