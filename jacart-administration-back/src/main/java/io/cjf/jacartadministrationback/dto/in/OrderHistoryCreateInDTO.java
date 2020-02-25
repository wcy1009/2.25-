package io.cjf.jacartadministrationback.dto.in;

public class OrderHistoryCreateInDTO {
    private Long orderId;
    private Byte ordereStatus;
    private String comment;
    private Boolean customerNotified;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Byte getOrdereStatus() {
        return ordereStatus;
    }

    public void setOrdereStatus(Byte ordereStatus) {
        this.ordereStatus = ordereStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getCustomerNotified() {
        return customerNotified;
    }

    public void setCustomerNotified(Boolean customerNotified) {
        this.customerNotified = customerNotified;
    }
}
