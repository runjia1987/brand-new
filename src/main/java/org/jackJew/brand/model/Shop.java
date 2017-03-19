package org.jackJew.brand.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

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
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Length(min = 1)
    private int id;

    @Column
    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private String manager;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;
}
