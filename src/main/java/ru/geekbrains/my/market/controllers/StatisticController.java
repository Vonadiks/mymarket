package ru.geekbrains.my.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.my.market.dto.StatisticDto;
import ru.geekbrains.my.market.utils.StatisticUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/statistic")
public class StatisticController {
    private final StatisticUtil statisticUtil;

    @GetMapping()
    public List<StatisticDto> getServiceStatistics() {
        return statisticUtil.findAll().stream().map(StatisticDto::new).collect(Collectors.toList());
    }



}
