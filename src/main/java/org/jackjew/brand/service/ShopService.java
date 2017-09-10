package org.jackjew.brand.service;

import org.jackjew.brand.model.Shop;
import org.jackjew.brand.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jack on 2017/3/19.
 */
@Service
@Transactional
public class ShopService {

  @Autowired
  private ShopRepository shopRepository;

  public void save(Shop shop) {
    shopRepository.save(shop);
  }

  @Transactional(readOnly = true)
  public Shop queryById(int shopId) {
    return shopRepository.findOne(shopId);
  }
}
