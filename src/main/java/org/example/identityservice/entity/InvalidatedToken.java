package org.example.identityservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "invalidated_tokens")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvalidatedToken { // entity này lưu các token đã bị logout

    @Id
    private String id;

    private Date expiryTime; // thời gian hết hạn, để có thể sau này có thể xóa đi tránh làm lớn CSDL
}
