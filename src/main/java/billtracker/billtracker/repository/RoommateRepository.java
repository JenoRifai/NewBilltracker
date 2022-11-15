package billtracker.billtracker.repository;

import billtracker.billtracker.model.Roommate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface RoommateRepository extends JpaRepository<Roommate, String> {
    @Override
    List<Roommate> findAll();
}

