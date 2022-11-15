package billtracker.billtracker.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Roommate {
    @Id
    private String roommatename;

    private String name;
    private String lastname;
    private BigDecimal balance;

    public Roommate() {
        this.balance = BigDecimal.ZERO;
    }

    public Roommate(String roommatename, String name, String lastname) {
        this.roommatename = roommatename;
        this.name = name;
        this.lastname = lastname;
        this.balance = BigDecimal.ZERO;
    }

    public String getRoommatename() {
        return roommatename;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roommate roommate = (Roommate) o;
        return name.equals(roommate.name) && lastname.equals(roommate.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname);
    }

    public static class Builder{
        private String roommatename;
        private String name;
        private String lastname;

        public Builder(){}

        public Roommate.Builder withRoommatename(String roommatename){
            this.roommatename = roommatename;
            return this;
        }

        public Roommate.Builder withName(String name){
            this.name = name;
            return this;
        }

        public Roommate.Builder withLastname(String lastname){
            this.lastname = lastname;
            return this;
        }

        public Roommate build(){
            return new Roommate(roommatename, name, lastname);
        }
    }
}