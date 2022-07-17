package com.itsol.recruit.service.jobregister;

import com.itsol.recruit.web.vm.SendJobRegisVm;
import org.springframework.stereotype.Service;

public interface SendJobRegisterService {
    public String sendMailJobRegis(Long id, SendJobRegisVm sendJobRegisVm);
}
