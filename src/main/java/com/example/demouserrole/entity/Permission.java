package com.example.demouserrole.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "permissions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Permission {
    @Id
    private String id;

    @Column(name = "name", length = 100)
    private String name;

    @ManyToMany(mappedBy = "permissions")
    private Collection<Role> roles;
}
