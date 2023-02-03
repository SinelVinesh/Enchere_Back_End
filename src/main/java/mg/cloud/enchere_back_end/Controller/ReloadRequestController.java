package mg.cloud.enchere_back_end.Controller;

import jakarta.servlet.http.HttpServletRequest;
import mg.cloud.enchere_back_end.Model.*;
import mg.cloud.enchere_back_end.Repository.AppUserTokenRepository;
import mg.cloud.enchere_back_end.Repository.ReloadRequestRepository;
import mg.cloud.enchere_back_end.Service.*;
import mg.cloud.enchere_back_end.exceptions.InvalidValueException;
import mg.cloud.enchere_back_end.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

@RestController
public class ReloadRequestController {
    private final ReloadRequestService reloadRequestService;
    private final ReloadRequestStateHistoryService reloadRequestStateHistoryService;
    private final Recharge_StateService recharge_stateService;
    private final CrudService<ReloadRequest, Long> reloadRequestCrudService;
    private final ReloadRequestRepository reloadRequestRepository;
    private final AppUserTokenRepository appUserTokenRepository;

    public ReloadRequestController(
            ReloadRequestService reloadRequestService,
            ReloadRequestStateHistoryService reloadRequestStateHistoryService,
            Recharge_StateService recharge_stateService,
            CrudService<ReloadRequest, Long> reloadRequestCrudService,
            ReloadRequestRepository reloadRequestRepository,
            AppUserTokenRepository appUserTokenRepository
    ) {
        this.reloadRequestService = reloadRequestService;
        this.reloadRequestStateHistoryService = reloadRequestStateHistoryService;
        this.recharge_stateService = recharge_stateService;
        this.reloadRequestCrudService = reloadRequestCrudService;
        this.reloadRequestRepository = reloadRequestRepository;
        this.appUserTokenRepository =appUserTokenRepository;
    }

    @RequestMapping(value={"/reloads/request","/reloads/request/{id}","/reloads"})
    public ResponseEntity<Response> crudAuction(
            @PathVariable("id") Optional<Long> id,
            @RequestBody Optional<ReloadRequest> reloadRequest,
            HttpServletRequest request,
            @RequestHeader("Authorization") String token) throws InvalidValueException {
        if(request.getMethod().equals("POST") && reloadRequest.isPresent()) {
            AppUserToken appUserToken = appUserTokenRepository.findByValue(token.split(" ")[1]).orElseThrow(() -> new InvalidValueException("Token invalide"));
            if(!appUserToken.getUser().getId().equals(reloadRequest.get().getUser().getId())) {
                throw new InvalidValueException("Vous n'avez pas l'authorization d'effectuer cette operation ");
            }
            if(reloadRequest.get().getAmount() < 0) {
                throw new InvalidValueException("Le montant ne peut pas etre negatif");
            }
        }
        ReloadRequest data = reloadRequest.orElse(null);
        ResponseEntity<Response> response= reloadRequestCrudService.handle(request.getMethod(), reloadRequestRepository, id, data);
        if(request.getMethod().equals("POST") && response.getBody().getData() != null) {
            ReloadRequest saved = (ReloadRequest) response.getBody().getData();
            ReloadRequestStateHistory reloadRequestStateHistory = new ReloadRequestStateHistory();
            reloadRequestStateHistory.setDate(saved.getDate());
            reloadRequestStateHistory.setReloadRequest(saved);
            ReloadState rechargeState = recharge_stateService.findById(1L);
            reloadRequestStateHistory.setReloadState(rechargeState);
            reloadRequestStateHistoryService.saveReloadRequestHistory(reloadRequestStateHistory);
        }
        if(request.getMethod().equals("GET") && response.getBody() != null) {
            reloadRequestService.fillReloadRequest(response.getBody().getData());
        }
        return response;
    }

    @PostMapping("/reloads/validations")
    public ResponseEntity<Response> validateReloadRequest(@RequestBody ReloadRequest data) {
        ReloadRequest reloadRequest = reloadRequestService.findById(data.getId());
        ReloadRequestStateHistory reloadRequestStateHistory = new ReloadRequestStateHistory();
        reloadRequestStateHistory.setDate(LocalDateTime.now());
        reloadRequestStateHistory.setReloadRequest(reloadRequest);
        ReloadState rechargeState = recharge_stateService.findById(data.getCurrentState().getId());
        reloadRequestStateHistory.setReloadState(rechargeState);
        reloadRequestStateHistoryService.saveReloadRequestHistory(reloadRequestStateHistory);
        return new ResponseEntity<>(new Response("success", reloadRequest), HttpStatus.OK);
    }
}
