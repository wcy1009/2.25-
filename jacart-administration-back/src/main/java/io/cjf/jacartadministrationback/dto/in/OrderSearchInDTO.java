package io.cjf.jacartadministrationback.dto.in;

public class OrderSearchInDTO {
    private Long orderId;
    private String customerName;
    private Byte status;
    private Double totalPrice;
    private Long StartTimestamp;
    private Long EndTimestamp;

    public Long getStartTimestamp() {
        return StartTimestamp;
    }

    public void setStartTimestamp(Long startTimestamp) {
        StartTimestamp = startTimestamp;
    }

    public Long getEndTimestamp() {
        return EndTimestamp;
    }

    public void setEndTimestamp(Long endTimestamp) {
        EndTimestamp = endTimestamp;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
