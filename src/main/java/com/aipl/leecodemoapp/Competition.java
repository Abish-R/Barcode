package com.aipl.leecodemoapp;
public class Competition {
        private String brand,comments;

    public Competition() {
    }

    public Competition(String brand, String comments) {
        this.brand = brand;
        this.comments = comments;
    }
        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

    }

