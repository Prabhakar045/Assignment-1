package com.miko.Assignment;

import com.miko.Assignment.dto.MikoProductsDTO;
import com.miko.Assignment.impl.Miko;
import com.miko.Assignment.impl.MikoImpl;

public class MikoExecutor {

    public static void main(String[] args) {
        Miko miko = new MikoImpl();

        // Get product details
        MikoProductsDTO productDTO = miko.productsDetails();

        // insert product details into the db table
        miko.addProductDetailsToDB(productDTO);

        // export database table to text file
        miko.detabaseTableTotxtFile();

        // export metadata to text file
        miko.metadataTotxtFIle();
    }


}
