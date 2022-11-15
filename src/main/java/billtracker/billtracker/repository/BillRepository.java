package billtracker.billtracker.repository;

import billtracker.billtracker.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer>
{
    @Override
    List<Bill> findAll();

    List<Bill> findAllByOrderByDateDesc();
}