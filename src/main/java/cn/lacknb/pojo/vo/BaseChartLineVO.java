package cn.lacknb.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: gitsilence
 * @description:
 * @date: 2021/3/19 4:03 下午
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class BaseChartLineVO {

    private ChartXaxis xAxis;
    private List<ChartYaxis> series;
    private ChartLegend legend;

}
