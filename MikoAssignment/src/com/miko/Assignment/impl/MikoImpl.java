package com.miko.Assignment.impl;

import com.miko.Assignment.dto.MikoProductsDTO;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class MikoImpl implements Miko{
    Scanner sc = new Scanner(System.in);

    @Override
    public MikoProductsDTO productsDetails() {
        MikoProductsDTO dto = new MikoProductsDTO();

        System.out.println("Enter the Product name: ");
        dto.setProductName(sc.nextLine());

        System.out.println("Product price: ");
        dto.setPrice(sc.nextDouble());

        sc.nextLine();

        System.out.println("Category: ");
        dto.setCategory(sc.nextLine());

        System.out.println("manufacturer: ");
        dto.setManufacturer(sc.nextLine());

        System.out.println("product Description: ");
        dto.setDescription(sc.nextLine());


        return dto;
    }

    @Override
    public void addProductDetailsToDB(MikoProductsDTO dto) {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/miko","root","Xworkzodc@123");
            stmt=con.prepareStatement("insert into miko_products(productName, price, category, manufacturer, description) values(?,?,?,?,?) ");
            stmt.setString(1, dto.getProductName());
            stmt.setDouble(2, dto.getPrice());
            stmt.setString(3, dto.getCategory());
            stmt.setString(4, dto.getManufacturer());
            stmt.setString(5, dto.getDescription());

            System.out.println("Product details to be inserted: " + dto);

            stmt.executeUpdate();
            System.out.println("Products details added " +dto);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {

            if(stmt != null){
                    try{
                        stmt.close();
                    }catch (SQLException e){
                        throw new RuntimeException();
                    }
            }

            if (con != null){
               try{
                   con.close();
               }catch (SQLException e){
                   throw  new RuntimeException();
               }
            }
        }
    }

    @Override
    public void detabaseTableTotxtFile() {
        try {
            String filename = "C:\\Users\\prabh\\Desktop\\miko_products.txt"; // Replace with your desired file path
            FileWriter writer = new FileWriter("miko_products.txt");

            Connection con = null;
            try {
                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/miko", "root", "Xworkzodc@123");
                String query = "SELECT * FROM miko_products";
                PreparedStatement st = con.prepareStatement(query);
                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String productName = rs.getString("productName");
                    double price = rs.getDouble("price");
                    String manufacturer= rs.getString("manufacturer");
                    String description = rs.getString("description");

                    String line = id + "\t" + productName + "\t" + price + "\t" + manufacturer + "\t" + description;
                    writer.write(line + "\n");
                }

                System.out.println("Data exported to text file successfully.");
            } catch (SQLException ex) {
                System.out.println("Database error");
                ex.printStackTrace();
            } finally {
                if (con != null) {
                    con.close();
                }
            }

            writer.close();
        } catch (IOException | SQLException ex) {
            System.out.println("Error writing to text file");
            ex.printStackTrace();
        }
    }

    @Override
    public void metadataTotxtFIle() {
        try {
            String metadataFilename = "C:\\Users\\prabh\\Desktop\\miko_metadata.txt";
            FileWriter metadataWriter = new FileWriter("miko_metadata.txt");

            Connection con = null;
            try {
                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/miko", "root", "Xworkzodc@123");
                DatabaseMetaData metaData = con.getMetaData();

                String tableName = "miko_products";
                ResultSet columns = metaData.getColumns(null, null, tableName, null);

                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    String columnType = columns.getString("TYPE_NAME");
                    int columnSize = columns.getInt("COLUMN_SIZE");
                    boolean isNullable = (columns.getInt("NULLABLE") == DatabaseMetaData.columnNullable);
                    boolean isAutoIncrement = columns.getBoolean("IS_AUTOINCREMENT");

                    String line = "Column Name: " + columnName + ", Column Type: " + columnType +
                            ", Size: " + columnSize + ", Nullable: " + isNullable +
                            ", AutoIncrement: " + isAutoIncrement;

                    metadataWriter.write(line + "\n");
                }

                System.out.println("Metadata exported to text file successfully.");
            } catch (SQLException ex) {
                System.out.println("Database error");
                ex.printStackTrace();
            } finally {
                if (con != null) {
                    con.close();
                }
            }

            metadataWriter.close();
        } catch (IOException | SQLException ex) {
            System.out.println("Error writing metadata to text file");
            ex.printStackTrace();
        }
    }


}
