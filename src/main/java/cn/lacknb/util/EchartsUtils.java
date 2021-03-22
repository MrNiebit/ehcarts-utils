package cn.lacknb.util;

import cn.lacknb.pojo.vo.BaseChartLineVO;
import cn.lacknb.pojo.vo.ChartLegend;
import cn.lacknb.pojo.vo.ChartXaxis;
import cn.lacknb.pojo.vo.ChartYaxis;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: gitsilence
 * @description: dao层查询的时候，可以直接返回List<Map<String, Object>> 的形式
 *  sql查询的时候，将指定字段 其 对应的 别名即可
 *
 * @date: 2021/3/22 3:48 下午
 **/
public class EchartsUtils {

    /**
     * 折线图 公共转换
     * ps: Map 中  key 是写死的。
     *      key  ->  desc
     *      LINEDATE  x轴的日期
     *      LINENAME  每条 折线的名字
     *      LINECOUNT  数量
     * @param abnormalLogs List<Map<string, object>>  list中的map 类似于 一个实体类。
     * @return 返回 echarts格式的 数据
     */
    public static BaseChartLineVO getCommonLineChartData (List<Map<String, Object>> abnormalLogs) {
        BaseChartLineVO baseChartLineVO = new BaseChartLineVO();
        // 填充xAxis
        ChartXaxis chartXaxis = new ChartXaxis();
        // 按日期 对 map 排下序
        abnormalLogs = abnormalLogs.stream().sorted(Comparator.comparing(o -> o.get("LINEDATE").toString())).collect(Collectors.toList());
        // 获取日期
        List<String> lineDate = abnormalLogs.stream().map(stringObjectMap -> stringObjectMap.get("LINEDATE").toString())
                .collect(Collectors.toList());
        // 去重
        lineDate = lineDate.stream().distinct().collect(Collectors.toList());
        chartXaxis.setData(lineDate);
        chartXaxis.setType("category");
        chartXaxis.setBoundaryGap(false);
        baseChartLineVO.setXAxis(chartXaxis);
        // 填充 legend
        ChartLegend chartLegend = new ChartLegend();
        Set<Object> logTypeName = abnormalLogs.stream().map(map -> map.get("LINENAME")).collect(Collectors.toSet());
        chartLegend.setData(logTypeName);
        baseChartLineVO.setLegend(chartLegend);
        // 填充 yAxis
        List<ChartYaxis> chartYaxisList = new ArrayList<>();
        // name -> map
        Map<String, List<Map<String, Object>>> mapList = abnormalLogs.stream().collect(Collectors.groupingBy(map -> map.get("LINENAME").toString(), Collectors.toList()));
        mapList.forEach((k, v) -> {
            ChartYaxis chartYaxis = new ChartYaxis();
            chartYaxis.setType("line");
            chartYaxis.setName(k);
            chartYaxis.setData(v.stream().map(map -> map.get("LINECOUNT")).collect(Collectors.toList()));
            chartYaxisList.add(chartYaxis);
        });
        baseChartLineVO.setSeries(chartYaxisList);
        return baseChartLineVO;
    }


    /**
     * 柱状图 公共转换，和上面的区别：x轴 是名字。
     * @param abnormalLogs
     * @return
     */
    public static BaseChartLineVO getCommonBarChartData (List<Map<String, Object>> abnormalLogs) {
        BaseChartLineVO baseChartLineVO = new BaseChartLineVO();
        // 填充xAxis
        ChartXaxis chartXaxis = new ChartXaxis();
        List<String> xNames = abnormalLogs.stream().map(stringObjectMap -> stringObjectMap.get("LINENAME").toString()).collect(Collectors.toList());
        chartXaxis.setData(xNames);
        chartXaxis.setType("category");
        // 坐标轴两端空白策略  https://blog.csdn.net/weixin_45677987/article/details/114323079
        chartXaxis.setBoundaryGap(true);
        baseChartLineVO.setXAxis(chartXaxis);
        // 填充 yAxis
        List<ChartYaxis> chartYaxisList = new ArrayList<>();
        // series => [{data: [1, 2, 3, 4], type: 'bar}]
        // name -> map
//        Map<String, List<Map<String, Object>>> mapList = abnormalLogs.stream().collect(Collectors.groupingBy(map -> map.get("LINENAME").toString(), Collectors.toList()));
        ChartYaxis barYData = new ChartYaxis();
        barYData.setType("bar");
        ArrayList<Object> counts = new ArrayList<>();
        abnormalLogs.forEach(k -> {
            counts.add(k.get("LINECOUNT"));
        });
        barYData.setData(counts);
        chartYaxisList.add(barYData);
        baseChartLineVO.setSeries(chartYaxisList);
        return baseChartLineVO;
    }

}
