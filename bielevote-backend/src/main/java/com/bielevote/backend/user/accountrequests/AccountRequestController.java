package com.bielevote.backend.user.accountrequests;

import com.bielevote.backend.project.Project;
import com.bielevote.backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/account-requests")
public class AccountRequestController {

    @Autowired
    private AccountRequestRepository accountRequestRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAccountRequests(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            PageRequest paging = PageRequest.of(page, size, Sort.by("dateRequested").ascending());
            Page<AccountRequest> pageAccountRequests = accountRequestRepository.findAll(paging);

            List<AccountRequest> accountRequests = pageAccountRequests.getContent();

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("accountRequests", accountRequests);
            responseBody.put("currentPage", pageAccountRequests.getNumber());
            responseBody.put("totalItems", pageAccountRequests.getTotalElements());
            responseBody.put("totalPages", pageAccountRequests.getTotalPages());

            return ResponseEntity.ok(responseBody);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Void> createAccountRequest(@Validated @RequestBody AccountRequestDto accountRequestDto) {
        try {
            var accountRequest = new AccountRequest();
            if (userRepository.findByUsername(accountRequest.getUsername()).isPresent()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (userRepository.findByLegalNameAndPhone(accountRequestDto.legalName, accountRequestDto.phone).isPresent()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            accountRequest.setUsername(accountRequestDto.username);
            accountRequest.setLegalName(accountRequestDto.legalName);
            accountRequest.setPhone(accountRequestDto.phone);
            accountRequest.setDateRequested(LocalDateTime.now());

            accountRequestRepository.save(accountRequest);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Project> deleteAccountRequestById(@PathVariable("id") long id) {
        try {
            if (accountRequestRepository.findById(id).isEmpty()) {
                throw new RuntimeException();
            }
            accountRequestRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public record AccountRequestDto(String username, String legalName, String phone) {
    }
}
