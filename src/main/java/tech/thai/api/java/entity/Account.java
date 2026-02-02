package tech.thai.api.java.entity;
import jakarta.persistence.*;
import tech.thai.api.java.respository.BillingAdressRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_accounts")

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="account_id")
    private UUID accountId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

   @OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
   @PrimaryKeyJoinColumn
   private BillingAdress billingAdress;

   @OneToMany(mappedBy = "account")
    private List<AccountStock> accountStocks = new ArrayList<>();

    @Column(name= "description")
    private String description;





    public Account () {

}

    public Account(UUID account_id, User user, BillingAdress billingAdress, String description, List<AccountStock> accountStocks ) {
        this.accountId = account_id;
        this.user = user;
        this.billingAdress = billingAdress;
        this.accountStocks = accountStocks;
        this.description = description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public List<AccountStock> getAccountStocks() {
        return accountStocks;
    }

    public void setAccountStocks(List<AccountStock> accountStocks) {
        this.accountStocks = accountStocks;
    }
    public BillingAdress getBillingAdress() {
        return billingAdress;
    }

    public void setBillingAdress(BillingAdress billingAdress) {
        this.billingAdress = billingAdress;
    }
}
