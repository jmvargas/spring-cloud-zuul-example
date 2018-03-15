package com.privalia.finalproject.domain;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Id;
import org.springframework.data.annotation.Version;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@DynamicUpdate(value = true)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated student ID")
    private Integer id;

    @Version
    @ApiModelProperty(notes = "The auto generated version of product")
    private Integer version;

    @ApiModelProperty(notes = "The student name")
    @Size(min = 6, max = 400)
    private String name;

    @OneToMany
    private List<Address> address;

    public Student() {
    }

    public Student(String name, List<Address> address) {
        this.name = name;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
