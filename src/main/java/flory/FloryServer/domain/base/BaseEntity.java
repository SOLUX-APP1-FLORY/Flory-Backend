package flory.FloryServer.domain.base;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        createdAt = ZonedDateTime.now(seoulZoneId).toLocalDateTime();
    }

    @PreUpdate
    protected void onUpdate() {
        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        updatedAt = ZonedDateTime.now(seoulZoneId).toLocalDateTime();
    }

    // Getter and Setter for createdAt and updatedAt
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
