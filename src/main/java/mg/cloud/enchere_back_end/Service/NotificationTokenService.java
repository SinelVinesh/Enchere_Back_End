package mg.cloud.enchere_back_end.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.firebase.messaging.FirebaseMessagingException;
import mg.cloud.enchere_back_end.Model.*;
import mg.cloud.enchere_back_end.Repository.AppUserTokenRepository;
import mg.cloud.enchere_back_end.Repository.AuctionWithStateRepository;
import mg.cloud.enchere_back_end.Repository.NotificationTokenRepository;
import mg.cloud.enchere_back_end.Repository.SentNotificationRepository;
import mg.cloud.enchere_back_end.exceptions.InvalidValueException;
import mg.cloud.enchere_back_end.request.Notification;
import mg.cloud.enchere_back_end.request.NotificationTokenInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
@Service
public class NotificationTokenService {
     @Autowired
     private FirebaseMessagingService firebaseMessagingService;
     @Autowired
     private AppUserTokenRepository appUserTokenRepository;
     @Autowired
     private NotificationTokenRepository notificationTokenRepository;
     @Autowired
     private SentNotificationRepository sentNotificationRepository;
     @Autowired
     private AuctionWithStateRepository auctionWithStateRepository;
     @Autowired
     private AuctionService auctionService;

     public void sendNotification(Notification notification) throws FirebaseMessagingException {
          firebaseMessagingService.sendNotification("Enchère", notification.getMessage(),
                    notification.getToken());
          SentNotification sent = new SentNotification();
          sent.setAuctionid(notification.getAuctionId());
          sent.setAppUserId(notification.getUserId());
          sent.setAuctionStateId(3L);
          sentNotificationRepository.save(sent);
     }
     public void sendAllNotificationBackground() throws Exception {
          List<SentNotification> sentNotifications = sentNotificationRepository.findAll();
          List<AuctionWithState> auctionWithStates = auctionWithStateRepository.findAllByEndDateIsAfter(Timestamp.valueOf(LocalDateTime.now().minus(7, ChronoUnit.DAYS)));
          List<Notification> toSend = new ArrayList<>();
          List<NotificationToken> liste = notificationTokenRepository.findAll();
          for(AuctionWithState auctionWithState : auctionWithStates) {
               for(NotificationToken element: liste ) {
                    boolean send = true;
                    for(SentNotification sent : sentNotifications) {
                         if (sent.getAuctionid().equals(auctionWithState.getId()) && element.getUserId().equals(auctionWithState.getAppUser().getId())) {
                              send = false;
                              break;
                         }
                    }
                    if(send) {
                         Notification ownerNotification = new Notification();
                         ownerNotification.setMessage(auctionWithState.getAppUser().getUsername() +", votre enchere s'est terminé");
                         ownerNotification.setAuctionId(auctionWithState.getId());
                         ownerNotification.setUserId(auctionWithState.getAppUser().getId());
                         ownerNotification.setToken(element.getToken());
                         toSend.add(ownerNotification);
                         Notification winnerNotification = new Notification();
                         auctionService.fillAcutions(auctionWithState);
                         winnerNotification.setMessage(auctionWithState.getTopBid().getAppUser().getUsername() +", Vous avez gagné l'enchere pour la somme de " + auctionWithState.getTopBid().getAmount());
                         winnerNotification.setAuctionId(auctionWithState.getId());
                         winnerNotification.setUserId(auctionWithState.getTopBid().getAppUser().getId());
                         winnerNotification.setToken(element.getToken());
                         toSend.add(winnerNotification);
                    }
               }
          }
          for (Notification n : toSend) {
               sendNotification(n);
          }
     }

     @Scheduled(fixedRate = 5, timeUnit = TimeUnit.MINUTES)
     public void checkAndSendNotifications() throws Exception {
          sendAllNotificationBackground();
     }

     public ResponseEntity<?> registerToken(NotificationTokenInput notificationTokenInput, String token) throws InvalidValueException {
          AppUserToken appUserToken = appUserTokenRepository.findByValue(token).orElseThrow(() -> new InvalidValueException("Token invalide"));
          AppUser appUser = appUserToken.getUser();
          NotificationToken notificationToken = new NotificationToken();
          notificationToken.setUserId(appUser.getId());
          notificationToken.setToken(notificationToken.getToken());
          return ResponseEntity.ok(notificationTokenRepository.save(notificationToken));
     }
}
