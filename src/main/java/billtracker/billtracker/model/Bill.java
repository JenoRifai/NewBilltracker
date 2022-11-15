package billtracker.billtracker.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="payer_id")
    private Roommate payer;
    private BigDecimal amount;
    private Instant date;
    private String description;

    public Bill(){}

    public Bill(Roommate payer, BigDecimal amount, Instant date, String description) {
        this.payer = payer;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public Roommate getPayer() {
        return payer;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Instant getDate() {
        return date;
    }

    public void setPayer(Roommate payer){
        this.payer = payer;
    }

    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }

    public void setDate(Instant date){
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class Builder {
        private Roommate payer;
        private BigDecimal amount = BigDecimal.ZERO;
        private Instant date;
        private String description;

        public Builder(){}

        public Builder withPayer(Roommate payer){
            this.payer = payer;
            return this;
        }

        public Builder withAmount(BigDecimal amount){
            this.amount = amount;
            return this;
        }

        public Builder withDate(Instant date){
            this.date = date;
            return this;
        }

        public Builder withDescription(String description){
            this.description = description;
            return this;
        }

        public Bill build(){
            return new Bill(payer, amount, date, description);
        }
    }
}