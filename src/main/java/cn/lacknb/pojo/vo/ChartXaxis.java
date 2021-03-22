package cn.lacknb.pojo.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author: gitsilence
 * @description: x
 * @date: 2021/3/17 2:07 下午
 **/
@Getter
@Setter
@NoArgsConstructor
public class ChartXaxis implements Serializable {

    private String type;
    private boolean boundaryGap;
    private List<String> data;

}
