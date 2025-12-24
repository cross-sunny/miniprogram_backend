package com.minigame.service;

import com.minigame.entity.Todo;
import com.minigame.mapper.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoMapper todoMapper;

    // 新增任务
    public void addTodo(Todo todo) {
        todoMapper.insertTodo(todo);
    }

    // 根据用户ID查任务列表
    public List<Todo> getTodoByUserId(Integer userId) {
        return todoMapper.selectByUserId(userId);
    }

    // 更新任务完成状态
    public void updateTodoStatus(Integer id, Integer isDone) {
        Todo todo = new Todo();
        todo.setId(id);
        todo.setIsDone(isDone);
        todoMapper.updateIsDone(todo);
    }

    // 删除任务
    public void deleteTodo(Integer id) {
        todoMapper.deleteById(id);
    }
}