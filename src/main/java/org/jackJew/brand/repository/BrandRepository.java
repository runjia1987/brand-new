package org.jackJew.brand.repository;

import org.jackJew.brand.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jack on 2017/3/19.
 */
@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    // inherit all methods

}
