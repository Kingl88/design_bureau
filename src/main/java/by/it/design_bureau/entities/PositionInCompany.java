package by.it.design_bureau.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(exclude = {"departments", "employees"})
@ToString(exclude = {"departments", "employees"})
public class PositionInCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String positionName;
    @ManyToMany(mappedBy = "positions")
    private Set<Department> departments;
    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<>();
}
