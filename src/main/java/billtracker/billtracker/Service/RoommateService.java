package billtracker.billtracker.Service;

import billtracker.billtracker.model.Roommate;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
public interface RoommateService {

    void addRoommate(Roommate roommate);
    void addPayment(String roommatename, BigDecimal amount);
    Optional<List<Roommate>> getBalance();
    boolean roommateExists(String fullName);
    Optional<Roommate> findByRoommatename(String roommatename);
}