package by.it.design_bureau.entities;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity @Table(name = "EMPLOYEE")
@EqualsAndHashCode(exclude = {"user", "department"})
@ToString(exclude = {"user", "department"})
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
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;
}
