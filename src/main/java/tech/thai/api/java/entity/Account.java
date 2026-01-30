package tech.thai.api.java.entity;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_accounts")

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="account_id")
    private UUID account_id;

   @OneToOne(mappedBy = "account")
   @PrimaryKeyJoinColumn
   private BillingAdress billingAdress;

   @OneToMany(mappedBy = "account")
    private List<AccountStock > accountStocks;

    @Column(name= "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public BillingAdress getBillingAdress() {
        return billingAdress;
    }

    public void setBillingAdress(BillingAdress billingAdress) {
        this.billingAdress = billingAdress;
    }

    public Account () {

}
    public Account(UUID account_id, String description) {
        this.account_id = account_id;
        this.description = description;
    }

    public UUID getAccount_id() {
        return account_id;
    }

    public void setAccount_id(UUID account_id) {
        this.account_id = account_id;
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
}
