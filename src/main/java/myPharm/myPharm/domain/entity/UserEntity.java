package myPharm.myPharm.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "user_name")
    private String userName;

    private String gender;

    private String age;

    private String birth;

    @Column(name = "outh_id")
    private Long outhId;

    //나중에 provider로 뺄거임
    private String refreshToken;

}
