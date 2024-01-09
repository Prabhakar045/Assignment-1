package com.miko.Assignment.impl;

import com.miko.Assignment.dto.MikoProductsDTO;

public interface Miko {

  public MikoProductsDTO productsDetails();

  void addProductDetailsToDB(MikoProductsDTO dto);

  void detabaseTableTotxtFile();

  void metadataTotxtFIle();

}
