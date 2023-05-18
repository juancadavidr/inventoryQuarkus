package org.inventory.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "buys")
public class Buy implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "buy_id")
  private Integer buyId;

  @Column
  private LocalDateTime date;

  @Column(name = "id_type")
  private String idType;

  @Column(name = "client_name")
  private String clientName;

  @OneToMany(cascade = CascadeType.ALL)
  private List<BuyDetail> products;

}
