package org.jackJew.brand.service;

import org.jackJew.brand.model.Brand;
import org.jackJew.brand.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jack on 2017/3/19.
 */
@Service
@Transactional
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public void save(Brand brand) {
        brandRepository.save(brand);
    }

    @Transactional(readOnly = true)
    public Brand queryById(int brandId) {
        return brandRepository.findOne(brandId);
    }
}
