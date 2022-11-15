package billtracker.billtracker.Service.implem;

import billtracker.billtracker.repository.BillRepository;
import billtracker.billtracker.model.Bill;
import billtracker.billtracker.model.Roommate;
import billtracker.billtracker.model.dto.BillDTO;
import billtracker.billtracker.Service.BillService;
import billtracker.billtracker.Service.RoommateService;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillServiceimplem implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    RoommateService roommateService;

    @Override
    public Optional<List<BillDTO>> findAll() {
        List<Bill> bills = billRepository.findAllByOrderByDateDesc();
        return Optional.ofNullable(bills.stream().map(this::convertToDTO).collect(Collectors.toList()));
    }

    @Override
    public void addBill(BillDTO billDTO) {
        Bill bill = convertToEntity(billDTO);
        if (!roommateService.roommateExists(bill.getPayer().getRoommatename())) return;

        billRepository.save(bill);
        roommateService.addPayment(bill.getPayer().getRoommatename(), bill.getAmount());
    }

    Converter<Long, Instant> toInstant = new AbstractConverter<>() {
        protected Instant convert(Long source) {
            return Instant.ofEpochMilli(source);
        }
    };
    Converter<Instant, Long> toEpoch = new AbstractConverter<>() {
        protected Long convert(Instant source) {
            return source == null ? null : source.getEpochSecond();
        }
    };

    private BillDTO convertToDTO(Bill bill) {
        modelMapper.addConverter(toEpoch);
        BillDTO billDTO = modelMapper.map(bill, BillDTO.class);
        billDTO.setRoommatename(bill.getPayer().getRoommatename());
        return billDTO;
    }

    private Bill convertToEntity(BillDTO billDTO) {
        modelMapper.addConverter(toInstant);
        Bill bill = modelMapper.map(billDTO, Bill.class);
//        bill.setDate(Instant.ofEpochSecond(billDTO.getDate()));
        bill.setPayer(new Roommate.Builder().withRoommatename(billDTO.getRoommatename()).build());
        return bill;
    }
}
