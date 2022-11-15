package billtracker.billtracker.model.dto;

import java.math.BigDecimal;
public class BillDTO {
    private String roommatename;
    private BigDecimal amount;
    private long date;
    private String description;

    public BillDTO() {
    }

    public BillDTO(String roommatename, BigDecimal amount, long date, String description) {
        this.roommatename = roommatename;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public String getRoommatename() {
        return roommatename;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public long getDate() {
        return date;
    }

    public void setRoommatename(String roommatename) {
        this.roommatename = roommatename;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDate(long dateEpoch) {
        this.date = dateEpoch;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}