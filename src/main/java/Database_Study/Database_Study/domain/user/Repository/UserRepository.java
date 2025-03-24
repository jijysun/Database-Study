package Database_Study.Database_Study.domain.user.Repository;

import Database_Study.Database_Study.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findUserByEmail(String email);
}
