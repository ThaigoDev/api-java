package tech.thai.api.java.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.thai.api.java.entity.BillingAdress;

import java.util.UUID;

public interface BillingAdressRepository extends JpaRepository<BillingAdress, UUID> {
}
