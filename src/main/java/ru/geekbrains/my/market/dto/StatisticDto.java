package ru.geekbrains.my.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.my.market.model.Statistic;

@Data
@NoArgsConstructor
public class StatisticDto {
    private String serviceName;
    private Long durationTime;

    public StatisticDto(Statistic statistic) {
        this.serviceName = statistic.getService();
        this.durationTime = statistic.getDuration();
    }
}
