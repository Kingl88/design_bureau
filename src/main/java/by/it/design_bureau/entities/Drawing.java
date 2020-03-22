package by.it.design_bureau.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Drawing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String drawingName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @NotEmpty
    private Employee developed;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Employee checked;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Employee approved;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Product product;
}
