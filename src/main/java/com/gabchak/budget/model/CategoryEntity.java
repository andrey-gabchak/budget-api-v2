package com.gabchak.budget.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@EqualsAndHashCode(of = "name")
@Table(name = "category")
@Entity
public class CategoryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;
  @Column(nullable = false, unique = true)
  String name;
  @Column(nullable = false)
  Integer sortOrder;
  @Column(nullable = false)
  Boolean income;
  @ManyToOne
  @JoinColumn(name = "parent_id")
  CategoryEntity parentEntity;
  @OneToMany(mappedBy = "parentEntity")
  Set<CategoryEntity> childEntities;
  @OneToMany(mappedBy = "categoryEntity")
  Set<RecordEntity> recordEntities;
}
