package com.thecoalition.bankingApi.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_Id")
    @OrderBy
   private Long id;

    @NotBlank
    @Column(name = "first_name")
   private String firstName;

    @NotBlank
    @Column(name = "last_name")
   private String lastName;

    @NotBlank
   @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinColumn(name = "customer_Id")
   private Set<Address> addresses;


    public Customer() {
    }

    public Customer(Long id, String firstName, String lastName, Set<Address> addresses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addresses = addresses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
