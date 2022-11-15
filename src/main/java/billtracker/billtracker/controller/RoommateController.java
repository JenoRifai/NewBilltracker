package billtracker.billtracker.controller;

import billtracker.billtracker.model.Roommate;
import billtracker.billtracker.Service.RoommateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Roommates")
public class RoommateController {

    @Autowired
    RoommateService roommateService;

    @PostMapping("addRoommate")
    public ResponseEntity addRoommate(@RequestBody Roommate mate) {
        Optional<Roommate> existingRoommate = roommateService.findByRoommatename(mate.getRoommatename());
        if (existingRoommate.isPresent()){
            mate.setBalance(existingRoommate.get().getBalance());
        }
        roommateService.addRoommate(mate);
        return ResponseEntity.ok().build();
    }

    @GetMapping("getBalance")
    public ResponseEntity getBalance(){
        Optional<List<Roommate>> mates = roommateService.getBalance();
        return mates.isPresent()
                ? ResponseEntity.ok(mates)
                : ResponseEntity.notFound().build();
    }
}