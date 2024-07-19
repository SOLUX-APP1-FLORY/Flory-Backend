<<<<<<<< HEAD:src/main/java/flory/FloryServer/domain/common/BaseEntity.java
package flory.FloryServer.domain.common;
========
package flory.FloryServer.domain.base;
>>>>>>>> dbca207f12e8cb0a661181084bd16bb4013b93b5:src/main/java/flory/FloryServer/domain/base/BaseEntity.java

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity {
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
