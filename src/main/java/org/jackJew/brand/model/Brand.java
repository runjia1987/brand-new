package org.jackJew.brand.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.jackJew.brand.utils.Java8TimeSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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
@ApiModel
@ToString
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @JsonSerialize(using = Java8TimeSerializer.class)
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @JsonSerialize(using = Java8TimeSerializer.class)
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "brand")
    @JsonManagedReference
    private List<Shop> shops;

}
