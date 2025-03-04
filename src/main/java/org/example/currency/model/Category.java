package org.example.currency.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    @EqualsAndHashCode.Exclude
    private List<Product> products;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "parent_id")
    @EqualsAndHashCode.Exclude
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    private List<Category> subcategories;
}
