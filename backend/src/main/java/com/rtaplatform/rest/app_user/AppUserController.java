package com.rtaplatform.rest.app_user;

import com.rtaplatform.app_user.AppUserService;
import com.rtaplatform.rest.app_user.dto.AppUserRequest;
import com.rtaplatform.rest.app_user.dto.AppUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.rtaplatform.rest.RestConstants.*;
import static com.rtaplatform.rest.RestUtils.createPageRequest;

@RestController("app-users")
@RequestMapping(value = "/app-users")
public class AppUserController {
    private final AppUserService appUserService;

    @Autowired
    public AppUserController(final AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping()
    public ResponseEntity<Long> createAppUser(@RequestBody final AppUserRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(appUserService.create(request.convertToEntity()).getId());
    }

    @GetMapping("{id}")
    public ResponseEntity<AppUserResponse> getAppUser(@PathVariable("id") final Long id) {
        return appUserService.getAppUser(id)
                .map(AppUserResponse::convertToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping()
    public Page<AppUserResponse> getAppUsers(@RequestParam(defaultValue = DEFAULT_PAGE) final Integer page,
                                             @RequestParam(defaultValue = DEFAULT_SIZE) final Integer size,
                                             @RequestParam(defaultValue = DEFAULT_SORT_DIR) final String sortDir,
                                             @RequestParam(defaultValue = "firstName") final String sortBy) {
        return appUserService.getAppUsers(createPageRequest(page, size, sortDir, sortBy))
                .map(AppUserResponse::convertToDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<AppUserResponse> updateAppUser(@PathVariable("id") final Long id,
                                                         @RequestBody final AppUserRequest request) {
        return appUserService.updateAppUser(id, request.convertToEntity())
                .map(AppUserResponse::convertToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public void deleteAppUser(@PathVariable("id") final Long id) {
        appUserService.deleteAppUser(id);
    }
}
