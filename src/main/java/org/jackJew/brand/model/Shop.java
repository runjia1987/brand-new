package org.jackJew.brand.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.jackJew.brand.utils.Java8TimeSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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
    private Brand brand;
}
