package com.minigame.mapper;

import com.minigame.entity.Todo;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface TodoMapper {
    // 新增待办
    int insertTodo(Todo todo);

    // 根据用户ID查询其所有待办
    List<Todo> selectByUserId(Integer userId);

    // 更新待办的完成状态
    int updateIsDone(Todo todo);

    // 根据ID删除待办
    int deleteById(Integer id);
}