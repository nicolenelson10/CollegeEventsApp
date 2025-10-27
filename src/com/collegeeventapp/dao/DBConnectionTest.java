package com.collegeeventapp.dao;

import java.sql.Connection;

public class DBConnectionTest {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                System.out.println("✅ Database connected successfully!");
            } else {
                System.out.println("❌ Database connection failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
