package vn.iotstar.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name="otp_tokens",indexes=@Index(name="idx_otp_email_type",columnList="email,type"))
public class OtpToken {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,length=150) private String email;
    @Column(nullable=false,length=100) private String otpHash;
    @Column(nullable=false,length=30) private String type;
    @Column(nullable=false) private LocalDateTime expiresAt;
    @Column(nullable=false) private int attempts;
    @Column(nullable=false) private boolean used=false;
    @Column(nullable=false) private LocalDateTime createdAt;
    public OtpToken() {}
    public OtpToken(Long id,String email,String otpHash,String type,LocalDateTime expiresAt,int attempts,boolean used,LocalDateTime createdAt){this.id=id;this.email=email;this.otpHash=otpHash;this.type=type;this.expiresAt=expiresAt;this.attempts=attempts;this.used=used;this.createdAt=createdAt;}
    public Long getId(){return id;} public void setId(Long v){id=v;} public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getOtpHash(){return otpHash;} public void setOtpHash(String v){otpHash=v;} public String getType(){return type;} public void setType(String v){type=v;}
    public LocalDateTime getExpiresAt(){return expiresAt;} public void setExpiresAt(LocalDateTime v){expiresAt=v;} public int getAttempts(){return attempts;} public void setAttempts(int v){attempts=v;}
    public boolean isUsed(){return used;} public void setUsed(boolean v){used=v;} public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
}
