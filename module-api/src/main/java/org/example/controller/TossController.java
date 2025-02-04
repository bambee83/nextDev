package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller @Slf4j
@RequestMapping(value="/")
public class TossController {

    @GetMapping(value = "success")
    public String paymentResult(
            Model model,
            @RequestParam(value = "orderId") String orderId,
            @RequestParam(value = "amount") Integer amount,
            @RequestParam(value = "paymentKey") String paymentKey) throws Exception {

        log.info("Received orderId: {}", orderId);
        log.info("Received paymentKey: {}", paymentKey);
        log.info("Received amount: {}", amount);

        // 여기서 결제 요청은 하지 않고, 받은 값만 출력합니다.
        model.addAttribute("orderId", orderId);
        model.addAttribute("paymentKey", paymentKey);

        return "success";
    }

    @GetMapping(value = "fail")
    public String paymentResult(
            Model model,
            @RequestParam(value = "message") String message,
            @RequestParam(value = "code") Integer code
    ) throws Exception {

        model.addAttribute("code", code);
        model.addAttribute("message", message);

        return "fail";
    }

}