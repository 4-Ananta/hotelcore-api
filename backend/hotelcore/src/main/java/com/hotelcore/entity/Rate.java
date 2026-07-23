package com.hotelcore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rate")
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_type_id", nullable = false)
    private RoomType roomType;

    @Column(nullable = false)
    private String rateName;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = true, length = 255)
    private String description;

    @Column(nullable = false)
    private Boolean isActive = true;

    @Column(name = "start_date", insertable = true, updatable = true)
    private LocalDateTime startDate;

    @Column(name = "end_date", insertable = true, updatable = true)
    private LocalDateTime endDate;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;


}
