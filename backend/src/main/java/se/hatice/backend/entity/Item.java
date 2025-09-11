package se.hatice.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Namn får inte vara tomt")
    @Column(nullable = false)
    private String name;

    @Min(value = 0, message = "Antal får inte vara negativt")
    @Column(nullable = false)
    private Integer quantity;

    @NotBlank(message = "Enhet får inte vara tom")
    @Column(nullable = false)
    private String unit;
}