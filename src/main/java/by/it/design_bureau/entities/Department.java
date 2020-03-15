package by.it.design_bureau.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DEPARTAMENT")
@EqualsAndHashCode(exclude = {"employees","positionInCompany"})
@ToString(exclude = {"employees", "positionInCompany"})
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENT_ID", unique = true)
    private Long id;
    @Column(name = "NAME")
    String departmentName;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSITION_ID")
    private PositionInCompany positionInCompany;

}
