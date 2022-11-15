package billtracker.billtracker.Service.implem;

import billtracker.billtracker.BilltrackerservicesApplication;
import billtracker.billtracker.repository.RoommateRepository;
import billtracker.billtracker.model.Roommate;
import billtracker.billtracker.Service.RoommateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class RoommateServiceimplem implements RoommateService {

    @Autowired
    private RoommateRepository roommateRepository;

    static final Logger log = LoggerFactory.getLogger(BilltrackerservicesApplication.class);

    @Override
    public void addRoommate(Roommate roommate) {
        roommateRepository.save(roommate);
    }

    @Override
    public void addPayment(String roommatename, BigDecimal amount) {
        Optional<List<Roommate>> balance = getBalance();
        log.info(String.format("payer:%s - %.2f between %d",roommatename, amount, balance.map(List::size).orElse(0)));
        balance.ifPresent(list -> list.stream()
                .forEach(roommate -> {
                    log.info(String.format("BEFORE \t%s: %.2f",roommate.getRoommatename(), roommate.getBalance()));
                    BigDecimal needToPay = amount.divide(BigDecimal.valueOf(list.size()), RoundingMode.HALF_UP);
                    if (roommate.getRoommatename().equals(roommatename)){
                        needToPay = needToPay.subtract(amount);
                    }
                    roommate.setBalance(
                            roommate.getBalance()
                                    .add(needToPay));
                    roommateRepository.save(roommate);
                    log.info(String.format("AFTER \t%s: %.2f",roommate.getRoommatename(), roommate.getBalance()));
                }));
    }

    @Override
    public Optional<List<Roommate>> getBalance() {
        return Optional.ofNullable(roommateRepository.findAll());
    }

    @Override
    public boolean roommateExists(String roommatename) {
        return roommateRepository.existsById(roommatename);
    }

    @Override
    public Optional<Roommate> findByRoommatename(String roommatename) {
        return roommateRepository.findById(roommatename);
    }
}
