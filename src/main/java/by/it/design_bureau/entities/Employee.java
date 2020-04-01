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
@EqualsAndHashCode(exclude = {"user", "department", "position","drawingsDevelop", "drawingsCheck", "drawingsApprove"})
@ToString(exclude = {"user", "department", "position","drawingsDevelop", "drawingsCheck", "drawingsApprove"})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "employee"
            , cascade = CascadeType.ALL)
    @Access(AccessType.PROPERTY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Department department;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private PositionInCompany position;
    @OneToMany(mappedBy = "developed", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Drawing> drawingsDevelop = new HashSet<>();
    @OneToMany(mappedBy = "checked", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Drawing> drawingsCheck = new HashSet<>();
    @OneToMany(mappedBy = "approved", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Drawing> drawingsApprove = new HashSet<>();

}
