package com.accounting.app.models;

import com.accounting.app.models.enums.TypeOfCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String code;
    private TypeOfCategory typeOfCategory;
    @ManyToOne
    @JoinColumn(name = "CREATOR_USER")
    private User user;
    @CreationTimestamp
    private LocalDateTime createdAt;
    public Category(String name , String code , TypeOfCategory typeOfCategory, User user){
        this.name=name;
        this.code= code;
        this.typeOfCategory=typeOfCategory;
        this.user = user;
    }


}
