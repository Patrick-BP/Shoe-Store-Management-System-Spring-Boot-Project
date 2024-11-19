package com.shoestoremanagement.billing_service.DTO;

import com.shoestoremanagement.billing_service.entity.Billing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingDTO {

    private Long id;
    private Long orderNo;
    private String customerName;
    private String customerMobile;
    private Timestamp orderDate;
    private Long shoeId;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal total;

    // Static factory method for Entity to DTO conversion
    public static BillingDTO fromEntity(Billing billing) {
        if (billing == null) {
            return null;
        }

        BillingDTO billingDTO = new BillingDTO();
        billingDTO.setId(billing.getId());
        billingDTO.setOrderNo(billing.getOrderNo());
        billingDTO.setCustomerName(billing.getCustomerName());
        billingDTO.setCustomerMobile(billing.getCustomerMobile());
        billingDTO.setOrderDate(billing.getOrderDate());
        billingDTO.setShoeId(billing.getShoeId());
        billingDTO.setQuantity(billing.getQuantity());
        billingDTO.setUnitPrice(billing.getUnitPrice());
        billingDTO.setTotal(billing.getTotal());

        return billingDTO;
    }

    // Convert DTO to Entity
    public Billing toEntity() {
        Billing billing = new Billing();
        billing.setId(this.getId());
        billing.setOrderNo(this.getOrderNo());
        billing.setCustomerName(this.getCustomerName());
        billing.setCustomerMobile(this.getCustomerMobile());
        billing.setOrderDate(this.getOrderDate());
        billing.setShoeId(this.getShoeId());
        billing.setQuantity(this.getQuantity());
        billing.setUnitPrice(this.getUnitPrice());
        billing.setTotal(this.getTotal());

        return billing;
    }

    // Calculate total amount
    public void calculateTotal() {
        if (this.unitPrice != null && this.quantity > 0) {
            this.total = this.unitPrice.multiply(BigDecimal.valueOf(this.quantity));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillingDTO that = (BillingDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(orderNo, that.orderNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderNo);
    }
}
