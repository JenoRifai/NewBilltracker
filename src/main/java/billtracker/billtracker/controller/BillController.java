package billtracker.billtracker.controller;

import billtracker.billtracker.model.dto.BillDTO;
import billtracker.billtracker.Service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("bills")
public class BillController {

    @Autowired
    BillService billService;

    @GetMapping("getAllbills")
    public ResponseEntity getBill() {
        Optional<List<BillDTO>> bills = billService.findAll();
        return bills.isPresent()
                ? ResponseEntity.ok(bills)
                : ResponseEntity.notFound().build();
    }

    @PostMapping("addBill")
    public ResponseEntity addBill(@RequestBody BillDTO billDTO) {
        billService.addBill(billDTO);
        return ResponseEntity.ok().build();
    }
}