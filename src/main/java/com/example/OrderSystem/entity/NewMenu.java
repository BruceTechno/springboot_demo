package com.example.OrderSystem.entity;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "new_menu")
public class NewMenu {





        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "seq")
        private  int seq;

        @Column(name = "category")
        private String category;

        @Column(name = "item")
        private String item;

        @Column(name = "price")
        private int price;
        @Column(name = "uuid")
        @Type(type = "uuid-char")
        private UUID uuid = UUID.randomUUID();
//---------------------------------------------------------------------------------------------------





//---------------------------------------------------------------------------------------------------

        public int getSeq() {
            return seq;
        }

        public void setSeq(int seq) {
            this.seq = seq;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public UUID getUuid() {
            return uuid;
        }

        public void setUuid(UUID uuid) {
            this.uuid = uuid;
        }
    }
