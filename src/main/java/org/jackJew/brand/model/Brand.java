package org.jackJew.brand.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by Jack on 2017/3/18.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "brand")
@JsonInclude(JsonInclude.Include.NON_NULL)  // only serialize non-null field
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    private int id;

    @Column
    @NotNull
    private String name;

    @NotNull
    private String creator;

    @NotNull
    private String business;

    @Column(name = "head_quarter")
    private String headQuarter;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updateDate;

    @OneToMany(mappedBy = "brand")
    private List<Shop> shops;

}
