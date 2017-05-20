package com.demo.bank.logging;

import com.demo.bank.data.model.BankingUser;
import com.demo.bank.data.repository.BankingUserRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
@Aspect
@Component
public class ActionLogger {

    @Autowired
    BankingUserRepository bankingUserRepository;


    @Autowired
    LogPublisher logPublisher;

    @Pointcut("execution(* com.demo.bank.service.BusinessService.*(..))")
    public void controller() {
    }


    @Around("controller()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null) {
            User user = (User) auth.getPrincipal();
            BankingUser dbUser = bankingUserRepository.findByUsername(user.getUsername());
            LogMessage logMessage = new LogMessage(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME), Long.toString(dbUser.getBankingUserId()), pjp.getSignature().getName());
            logPublisher.send(logMessage);
        }

        return pjp.proceed();
    }
}