package cn.lacknb.pojo.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author: gitsilence
 * @description: series
 * @date: 2021/3/17 2:03 下午
 **/
@Getter
@Setter
@NoArgsConstructor
public class ChartYaxis implements Serializable {

    private String name;
    private String type;

    /*
    * stack 是 代表 堆叠 的折线图，这里用不到。
    * */

//    private String stack;
    private List<Object> data;

}
