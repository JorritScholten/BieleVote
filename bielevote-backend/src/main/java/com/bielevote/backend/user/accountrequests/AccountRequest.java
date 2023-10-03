package com.bielevote.backend.user.accountrequests;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Jacksonized
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "account_requests")
public class AccountRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_REQUESTS_ID_SEQ")
    @SequenceGenerator(name = "ACCOUNT_REQUESTS_ID_SEQ", sequenceName = "ACCOUNT_REQUESTS_SEQ", initialValue = 100)
    private Long id;
    @NonNull
    private String username;
    @NonNull
    private String legalName;
    @NonNull
    private String phone;
    @Column(columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime dateRequested;
}
