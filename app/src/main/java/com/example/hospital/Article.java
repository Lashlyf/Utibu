package com.example.hospital;

public class Article {

        private int id;
        private String title;
        private String description;
        private String imageUrl;

        public Article(int id, String title, String description, String imageUrl) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.imageUrl = imageUrl;
        }

        // Getters and setters
        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getImageUrl() {
            return imageUrl;
        }
}
