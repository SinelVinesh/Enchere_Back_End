package mg.cloud.enchere_back_end.Controller;

import jakarta.servlet.http.HttpServletRequest;
import mg.cloud.enchere_back_end.Model.Auction;
import mg.cloud.enchere_back_end.Model.ReloadRequest;
import mg.cloud.enchere_back_end.Model.ReloadRequestStateHistory;
import mg.cloud.enchere_back_end.Model.ReloadState;
import mg.cloud.enchere_back_end.Repository.ReloadRequestRepository;
import mg.cloud.enchere_back_end.Service.CrudService;
import mg.cloud.enchere_back_end.response.Response;
import mg.cloud.enchere_back_end.Service.ReloadRequestService;
import mg.cloud.enchere_back_end.Service.ReloadRequestStateHistoryService;
import mg.cloud.enchere_back_end.Service.Recharge_StateService;
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

    public ReloadRequestController(
            ReloadRequestService reloadRequestService,
            ReloadRequestStateHistoryService reloadRequestStateHistoryService,
            Recharge_StateService recharge_stateService,
            CrudService<ReloadRequest, Long> reloadRequestCrudService,
            ReloadRequestRepository reloadRequestRepository
    ) {
        this.reloadRequestService = reloadRequestService;
        this.reloadRequestStateHistoryService = reloadRequestStateHistoryService;
        this.recharge_stateService = recharge_stateService;
        this.reloadRequestCrudService = reloadRequestCrudService;
        this.reloadRequestRepository = reloadRequestRepository;
    }

    @RequestMapping(value={"/reloads/request","/reloads/request/{id}","/reloads"})
    public ResponseEntity<Response> crudAuction(
            @PathVariable("id") Optional<Long> id,
            @RequestBody Optional<ReloadRequest> reloadRequest,
            HttpServletRequest request) {
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
