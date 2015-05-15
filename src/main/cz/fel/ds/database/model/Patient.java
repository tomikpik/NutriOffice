package cz.fel.ds.database.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Tom on 15. 5. 2015.
 */

@Entity
@Table(name = "Patients")
public class Patient implements Serializable {

    private int patientId;
    private SimpleStringProperty firstName = new SimpleStringProperty();
    private SimpleStringProperty lastName = new SimpleStringProperty();
    private SimpleStringProperty gender = new SimpleStringProperty();
    private Date birthdate;
    private SimpleIntegerProperty nationalId = new SimpleIntegerProperty();
    private SimpleStringProperty email = new SimpleStringProperty();
    private SimpleStringProperty phone = new SimpleStringProperty();
    private Date dietStarted;
    private Diet diet;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "patient_id", unique = true, precision = 5, scale = 0)
    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId=patientId;
    }

    @Column(name = "first_name", length = 100)
    public String getFirstName() {
        return firstName.get();
    }
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    @Column(name = "last_name", length = 100)
    public String getLastName() {
        return lastName.get();
    }
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    @Column(name = "gender", length = 1)
    public String getGender() {
        return gender.get();
    }
    public void setGender(String gender) {
        this.gender.set(gender);
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate", length = 50)
    public Date getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Column(name = "national_id", length = 100)
    public int getNationalId() {
        return nationalId.get();
    }
    public void setNationalId(int nationalId) {
        this.nationalId.set(nationalId);
    }

    @Column(name = "email", length = 100)
    public String getEmail() {
        return email.get();
    }
    public void setEmail(String email) {
        this.email.set(email);
    }

    @Column(name = "phone", length = 100)
    public String getPhone() {
        return phone.get();
    }
    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "diet_started_date", length = 50)
    public Date getDietStarted() {
        return dietStarted;
    }
    public void setDietStarted(Date dietStarted) {
        this.dietStarted = dietStarted;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="diet_id")
    public Diet getDiet() {
        return diet;
    }
    public void setDiet(Diet diet) {
        this.diet = diet;
    }
}
