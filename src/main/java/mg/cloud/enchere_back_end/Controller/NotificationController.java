package mg.cloud.enchere_back_end.Controller;

import mg.cloud.enchere_back_end.Service.NotificationTokenService;
import mg.cloud.enchere_back_end.exceptions.InvalidValueException;
import mg.cloud.enchere_back_end.request.NotificationTokenInput;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
     private final NotificationTokenService notificationTokenService;
     public NotificationController(NotificationTokenService notificationTokenService) {
          this.notificationTokenService = notificationTokenService;
     }


     @PostMapping("/notification")
     public ResponseEntity<?> notifications(
               @RequestHeader("Authorization") String token,
               @RequestBody NotificationTokenInput notificationTokenInput) throws InvalidValueException {
          return notificationTokenService.registerToken(notificationTokenInput, token);
     }
}
