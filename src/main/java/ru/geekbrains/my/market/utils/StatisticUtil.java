package ru.geekbrains.my.market.utils;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import ru.geekbrains.my.market.model.Statistic;
import ru.geekbrains.my.market.repositories.StatisticRepository;

import java.util.List;
import java.util.Optional;

@Aspect
@Service
@RequiredArgsConstructor
public class StatisticUtil {

    private final StatisticRepository statisticRepository;

    public List<Statistic> findAll() {
        return statisticRepository.findAll();
    }

    @Around("execution(public * ru.geekbrains.my.market.services.*.*(..))")
    public Object getAllServicesDurationTime(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = pjp.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        Statistic statistics = new Statistic();
        String serviceName = pjp.getSignature().getDeclaringType().getSimpleName();

        if (findByService(serviceName).isPresent()) {
            statistics = statisticRepository.findByService(serviceName).get();
            statistics.setDuration(statistics.getDuration() + duration);
        } else {
            statistics.setService(serviceName);
            statistics.setDuration(duration);
        }
        statisticRepository.save(statistics);

        return out;

    }

    public Optional<Statistic> findByService(String service) {
        return statisticRepository.findByService(service);
    }

}
