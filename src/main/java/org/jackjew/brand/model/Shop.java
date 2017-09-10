package org.jackjew.brand.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.jackjew.brand.utils.Java8TimeSerializer;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by Jack on 2017/3/18.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shop")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel
@ToString
public class Shop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column
  @NotNull
  private String name;

  @NotNull
  private String address;

  @NotNull
  private String manager;

  @JsonSerialize(using = Java8TimeSerializer.class)
  @Column(name = "create_date")
  private LocalDateTime createDate;

  @JsonSerialize(using = Java8TimeSerializer.class)
  @Column(name = "update_date")
  private LocalDateTime updateDate;

  @ManyToOne
  @JoinColumn(name = "brand_id", nullable = false)
  @JsonBackReference
  private Brand brand;
}
