package com.minigame.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Todo {
    // 核心字段：仅保留必要属性
    private Integer id;         // 任务ID
    private Integer userId;     // 关联用户ID
    private String content;     // 任务内容
    private Date deadline;      // 截止时间
    private Integer isDone;     // 完成状态：0=未完成，1=已完成
    private Date createTime;    // 创建时间
}