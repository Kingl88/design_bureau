package by.it.design_bureau.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(exclude = {"employees","positions"})
@ToString(exclude = {"employees", "positions"})
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENT_ID", unique = true)
    private Long id;
    @NotEmpty
    private String departmentName;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "DEPARTMENT_POSITION",
            joinColumns = {@JoinColumn(name = "DEPARTMENT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "POSITION_ID")})
    private List<PositionInCompany> positions = new ArrayList<>();

}
