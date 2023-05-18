package org.inventory.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "buys_details")
public class BuyDetail implements Serializable {

  @Id
  @Column(name = "details_id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer detailsId;

  @ManyToOne(cascade = CascadeType.ALL)
  @Transient
  private Buy buy;

  @Column(name = "id_product")
  private Integer idProduct;

  @Column
  private Integer quantity;

}
