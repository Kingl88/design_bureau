package by.it.design_bureau.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(exclude = {"departments"})
@ToString(exclude = {"departments"})
public class PositionInCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POSITION_ID", unique = true)
    private Long id;
    private String positionName;
    @OneToMany(mappedBy = "positionInCompany", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Department> departments = new HashSet<>();
}
