package com.sena;

import com.sena.repository.CustomerRepository;
import com.sena.repository.entity.Customer;
import com.sena.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Runner {
    public static void main(String[] args) {

        //saveHibarnate();

        Customer customerById = new CustomerRepository().findById(7L, new Customer());
        System.out.println("Customer with id 7: " + customerById);

        List<Customer> customerByName = new CustomerRepository().findByColumn("name", "L", false, new Customer());
        System.out.println("Customers with the letter L in their name: ");
        customerByName.forEach(customer -> {
            System.out.println(customer.toString());
        });

        System.out.println("All customers: ");
        for (Customer customer : new CustomerRepository().findAll(new Customer())) {
            System.out.println(customer.toString());
        }


    }


    //dummy datas
    private static void saveHibarnate() {

        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = new Customer("Larry", "Micon", "Dubai");
        session.save(customer);
        Customer customer2 = new Customer("Nikkie", "Bossons", "Banbalah");
        session.save(customer2);
        Customer customer3 = new Customer("Shaine", "Baford", "Mojokerto");
        session.save(customer3);
        Customer customer4 = new Customer("Adolphus", "Lamming", "Amparafaravola");
        session.save(customer4);
        Customer customer5 = new Customer("Odelle", "Jeggo", "Pontalina");
        session.save(customer5);
        //or
        new CustomerRepository().save(new Customer("Judy", "Londesborough", "Ad Dilam"));
        new CustomerRepository().save(new Customer("Dinah", "Perett", "Tiflet"));
        new CustomerRepository().save(new Customer("Jolie","Stopher", "Xincheng"));
        new CustomerRepository().save(new Customer("Annette","Mildner","Pamiątkowo"));
        new CustomerRepository().save(new Customer("Con","Scaife","Sanhe"));

        transaction.commit();
        session.close();
    }
}