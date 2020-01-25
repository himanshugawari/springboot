package com.gawari._himanshu.springbootsimpleapi.jpa;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(UserCommandLineRunner.class);

	private UserRepository userRepository;

	@Autowired
	public UserCommandLineRunner(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		// repository.save(new User());

		// userRepository.save(new User("Ranga", "Admin"));
		userRepository.save(new User("Ranga", "Admin"));
		userRepository.save(new User("Ravi", "User"));
		userRepository.save(new User("Satish", "Admin"));
		userRepository.save(new User("Raghu", "User"));

		log.info("-------------------------------");
		log.info("Finding all users");
		log.info("-------------------------------");
		for (User user : userRepository.findAll()) {
			log.info(user.toString());
		}
		
		log.info("-------------------------------");
        log.info("Finding user with id 1");
        log.info("-------------------------------");
        Optional<User> user = userRepository.findById(1L);
        log.info(user.toString());

        log.info("-------------------------------");
        log.info("Finding all Admins");
        log.info("-------------------------------");
        for (User admin : userRepository.findByRole("Admin")) {
            log.info(admin.toString());
            // Do something...
        }
	}
}