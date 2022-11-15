package billtracker.billtracker.Service;

import billtracker.billtracker.model.dto.BillDTO;

import java.util.List;
import java.util.Optional;
public interface BillService {
    void addBill(BillDTO billDTO);
    Optional<List<BillDTO>> findAll();
}