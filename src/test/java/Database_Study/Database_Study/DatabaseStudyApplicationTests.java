package Database_Study.Database_Study;

import Database_Study.Database_Study.domain.user.Repository.UserRepository;
import Database_Study.Database_Study.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@SpringBootTest
@Slf4j
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
public class DatabaseStudyApplicationTests {

	private final UserRepository userRepository;

	public void init() {
		User user = User.builder()
				.create_date(new Date(250227))
				.age(15)
				.email("email@email.com")
				.name("name")
				.build();

		userRepository.save(user);
	}

	@Test @Transactional
	void saveTest() {
		User user = User.builder()
				.create_date(new Date(250227))
				.age(15)
				.email("email@email.com")
				.name("name")
				.build();

		log.info(user.toString());
	}
}
