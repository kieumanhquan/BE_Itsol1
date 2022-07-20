package com.itsol.recruit.web.admin.file;

import com.itsol.recruit.service.BrowserService;
import com.itsol.recruit.service.RefuseService;
import com.itsol.recruit.service.SuccessfullService;
import com.itsol.recruit.service.BookInterviewService;
import com.itsol.recruit.service.impl.BookInterviewServiceImpl;
import com.itsol.recruit.service.jobregister.SendJobRegisterService;
import com.itsol.recruit.web.vm.SendJobRegisVm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
//@RequestMapping(value = Constants.Api.Path.ADMIN)
public class BookInterviewController {
    @Autowired
    SuccessfullService successful;
    @Autowired
    RefuseService refuse;
    @Autowired
    BrowserService browser;
    @Autowired
    BookInterviewServiceImpl bookInterService;
    @Autowired
    SendJobRegisterService sendJobRegiter;

    @PostMapping("/api/bookinter")
    public ResponseEntity<?> BookInter(@RequestParam(value = "id") Long id) {
        return ResponseEntity.ok().body(Collections.singletonMap("data",bookInterService.BookInter(id)));
    }

    @PostMapping("/api/sendjobregis")
    public ResponseEntity<?> sendJobRegis(@RequestParam(value = "id") Long id, @RequestBody SendJobRegisVm sendJobRegisVm) {
        return ResponseEntity.ok().body(Collections.singletonMap("response",sendJobRegiter.sendMailJobRegis(id, sendJobRegisVm)));
    }

    @PutMapping("/api/browser")
    public ResponseEntity<?> changeBrowser(@RequestParam(value = "id") long id) {
        return ResponseEntity.ok().body(Collections.singletonMap("br",browser.browser(id)));
    }

    @PutMapping("/api/refuse")
    public ResponseEntity<?> changeRefuse(@RequestParam(value = "id") long id,@RequestParam(value = "reason") String reason) {
        return ResponseEntity.ok().body(Collections.singletonMap("rf",refuse.refuse(id,reason)));
    }

    @PutMapping("/api/successfull")
    public ResponseEntity<?> changeSuccessfull(@RequestParam(value = "id") long id) {
        return ResponseEntity.ok().body(Collections.singletonMap("sc",successful.successfull(id)));
    }
}
