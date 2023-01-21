package mg.cloud.enchere_back_end.Model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class V_recharge_request {
    private Date date;
    private ReloadRequest appUserRechargeRequest;
    private ReloadState rechargeState;
}
