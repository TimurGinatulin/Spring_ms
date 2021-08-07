package ginatulin.models.entity;

import ginatulin.models.dto.DeliveryAddressCartDto;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "address")
@NoArgsConstructor
public class DeliveryAddress {
    @Id
    @Column(name = "id_delivery")
    private Long idDelivery;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @OneToMany(mappedBy = "address")
    private Set<DeliveryEntity> delivery;

    public DeliveryAddress(DeliveryAddressCartDto address, Long id) {
        this.idDelivery = id;
        this.address = address.getAddress();
        this.phone = address.getPhone();
    }

    public Long getIdDelivery() {
        return idDelivery;
    }

    public void setIdDelivery(Long idDelivery) {
        this.idDelivery = idDelivery;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
